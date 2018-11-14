/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iss.workshop.ws02;

import iss.workshop.model.PurchaseOrder;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author issuser
 */
@WebServlet(urlPatterns={"/purchaseOrder"})
public class PurchaseOrderServlet extends HttpServlet {
    
    @PersistenceContext EntityManager em;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // super.doGet(req, resp); //To change body of generated methods, choose Tools | Templates.
        
        Integer cust_id = Integer.parseInt(req.getParameter("custId"));
        
        TypedQuery<PurchaseOrder> query = em.createNamedQuery(
                "PurchaseOrder.findByCustomerId", PurchaseOrder.class);
        
        query.setParameter("custId", cust_id);
        
        List<PurchaseOrder> result = query.getResultList();
        
        JsonArrayBuilder builder = Json.createArrayBuilder();
        
        for (PurchaseOrder po: result)
        {
            builder.add(po.toJson());
        }
        
        try (PrintWriter pw = resp.getWriter())
        {
            pw.print(builder.build());
        }
    }
}
