/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iss.workshop03.app;

import iss.workshop03.business.CustomerBean;
import iss.workshop03.business.CustomerException;
import iss.workshop03.model.Customer;
import iss.workshop03.model.DiscountCode;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author issuser
 */

@WebServlet(urlPatterns = {"/customer"})
public class CustonmerServlet extends HttpServlet {
    
    @EJB private CustomerBean customerBean;
    
    private Customer createCustomer(HttpServletRequest req)
    {
        Customer customer = new Customer();
        
        customer.setCustomerId(Integer.parseInt(req.getParameter("CustomerId")));
        customer.setName(req.getParameter("name"));
        customer.setAddressline1(req.getParameter("addressline1"));
        customer.setAddressline2(req.getParameter("addressline2"));
        customer.setCity(req.getParameter("city"));
        customer.setState(req.getParameter("state"));
        customer.setZip(req.getParameter("zip"));
        customer.setPhone(req.getParameter("phone"));
        customer.setFax(req.getParameter("fax"));
        customer.setEmail(req.getParameter("email"));
        // customer.setDiscountCode(req.getParameter("email"));
        
        DiscountCode dc = new DiscountCode();
        dc.setDiscountCode(DiscountCode.Code.valueOf(req.getParameter("discountCode")));
        customer.setDiscountCode(dc);
        
        customer.setCreditLimt(Integer.parseInt(req.getParameter("creditLimt")));
        
        return customer;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // super.doPost(req, resp); //To change body of generated methods, choose Tools | Templates.
        
        String html = "";
                
        Integer customerId = Integer.parseInt(req.getParameter("CustomerId"));
        
        Optional<Customer> opt = customerBean.findByCustomerId(customerId);
        
        if (opt.isPresent())
        {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.setContentType("text/plain");
        
            try (PrintWriter pw = resp.getWriter())
            {
               html += "CustomerId: " + customerId + " exists.";

               pw.print(html);
            }
           
            return;
        }
        
        Customer customer = createCustomer(req);
        
        try {
            customerBean.addNewCustomer(customer);
            
            // customerBean.addNewCustomer(c);
        } catch (CustomerException ex) {
            Logger.getLogger(CustonmerServlet.class.getName()).log(Level.SEVERE, null, ex);
            
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.setContentType("text/plain");

            try (PrintWriter pw = resp.getWriter())
            {
               pw.print("Failed");
            }
        }
        
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("text/plain");
        
        try (PrintWriter pw = resp.getWriter())
        {
           pw.print("Added");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // super.doGet(req, resp); //To change body of generated methods, choose Tools | Templates.
        
        String html = "";
                
        Integer customerId = Integer.parseInt(req.getParameter("CustomerId"));
        
        Optional<Customer> opt = customerBean.findByCustomerId(customerId);
        
        if (!opt.isPresent())
        {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.setContentType("text/plain");
        
            try (PrintWriter pw = resp.getWriter())
            {
               html += "CustomerId: " + customerId + " does not exists.";

               pw.print(html);
            }
           
            return;
        }
        
        Customer customer = opt.get();
        
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("application/json");
        // html += "OK";
        
        try (PrintWriter pw = resp.getWriter())
        {
           html += customer.toJson();
           
           pw.print(html);
        }
    }
}
