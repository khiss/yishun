/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iss.workshop.rest;

import iss.workshop04.model.Customer;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.core.Response;

/**
 *
 * @author issuser
 */
public class FindByCustomerIdfRunnable implements Runnable {
    
    private Integer custId;
    private CustomerBean customerBean;
    private AsyncResponse asyncResp;
    
    public FindByCustomerIdfRunnable(Integer cid, CustomerBean cb, AsyncResponse asr)
    {
        custId = cid;
        customerBean = cb;
        asyncResp = asr;
    }

    @Override
    public void run() {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        System.out.println(">>> FindByCustomerIdfRunnable->run()");
        
        Optional<Customer> opt = null;
        
        try
        {
            opt = customerBean.findByCustomerId(custId);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerResource.class.getName()).log(Level.SEVERE, null, ex);
            
            JsonObject error = Json.createObjectBuilder()
                    .add("error", ex.getMessage())
                    .build();
            
            // Error 406, 500
            asyncResp.resume(Response.serverError().entity(error).build());
            
            return;
        }
        
        if (!opt.isPresent())
        {
            asyncResp.resume(Response.status(Response.Status.NOT_FOUND).entity(Json.createObjectBuilder().add("error", "Customer Not found").build()).build());
            
            return;
        }
        
        asyncResp.resume(Response.status(Response.Status.OK).entity(opt.get().toJson()).build());
    }
}
