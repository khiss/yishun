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
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author issuser
 */
@RequestScoped
@Path("/customer")
public class CustomerResource {
    
    @EJB private CustomerBean customerBean;
    
    // Please create thread pool at payara admin console.
    @Resource(lookup = "concurrent/mythreadpool")
    private ManagedScheduledExecutorService threadPool;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{custId}")
    public Response findByCustomerId    (@PathParam("custId") Integer custId)
    {
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
            return Response.serverError().entity(error).build();
        }
        
        if (!opt.isPresent())
        {
            return Response.status(Response.Status.NOT_FOUND).entity(Json.createObjectBuilder().add("error", "Customer Not found").build()).build();
        }
        
        return Response.status(Response.Status.OK).entity(opt.get().toJson()).build();
    }
    
    // GET / customer/async/1
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("async/{custId}")
    public void findByAsyncCustomerId(@PathParam("custId") Integer custId, @Suspended AsyncResponse asyncResp)
    {
        FindByCustomerIdfRunnable runnable = new FindByCustomerIdfRunnable(custId, customerBean, asyncResp);
        
        // Execute the runnable in the threadpool
        
        threadPool.execute(runnable);
        System.out.println (">>> Exiting findByAsyncCustomerId");
    }
}
