/*
{

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iss.workshop05.app;

import iss.workshop05.model.Customer;
import java.util.Optional;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author issuser
 */
@RequestScoped
@Path("/customer")
public class CustomerResource {
    
    @EJB CustomerBean customerBean;
    
    @GET
    @Path("{custId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("custId") Integer custId)
    {
        Optional<Customer> opt = customerBean.findByCustomerId(custId);
        
        if (opt.isPresent() == false)
        {
            return Response.status(Response.Status.NOT_FOUND).entity(Json.createObjectBuilder().add("error", "Customer Not found").build()).build();
            // return (Response.status(Response.Status.NOT_FOUND).build());
        }
        
        return (Response.ok(opt.get().toJson()).build());
    }
}
