/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iss.workshop02.app;

/**
 *
 * @author issuser
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.json.Json;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;
import static javax.servlet.http.HttpServletResponse.SC_OK;
import javax.sql.DataSource;

@WebServlet(urlPatterns = {"/customer-sql"})
public class CustomerSqlServlet extends HttpServlet {

   
    @Resource(lookup ="jdbc/sample")
    private DataSource ds;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // super.doGet(req, resp); //To change body of generated methods, choose Tools | Templates.
        
        Integer cust_id = Integer.parseInt(req.getParameter("cust_id"));
        
        // TypedQuery<Customer> query = em.createQuery(deleteQuery);
        
        String html = "";
        
        try (Connection conn = ds.getConnection())
        {
            String str_sql = "select * from customer where customer_id = ?";
            
            PreparedStatement stmt = conn.prepareStatement(str_sql);
            
            stmt.setInt(1, cust_id);
            
            ResultSet results = stmt.executeQuery();
            
            if (results.next() == false)
            {
                html = "Customer ID " + cust_id + " NOT found !";
                
                resp.setStatus(SC_NOT_FOUND);
            }
            else
            {
                resp.setStatus(SC_OK);
                
                html += Json.createObjectBuilder()
                        .add("id", results.getInt("CUSTOMER_ID"))
                        .add("name", results.getString("NAME"))
                        .add("addr_line_1", results.getString("ADDRESSLINE1"))
                        .add("phone", results.getString("phone"))
                        .add("email", results.getString("email"))
                        .build().toString();
            }
            
            try (PrintWriter pw = resp.getWriter())
            {
                pw.print(html);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CustomerSqlServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
