/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iss.workshop05.app;

import io.jsonwebtoken.Jwts;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author issuser
 */
@WebServlet(urlPatterns = {"/login"} )
public class LoginServlet extends HttpServlet {
    
    @Inject private KeyManager keyMgr;
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // super.doPost(req, resp); //To change body of generated methods, choose Tools | Templates.

        String username =req.getParameter("username");
        String password =req.getParameter("password");
        
        try
        {
            req.login(username, password);
        } catch (ServletException ex)
        {
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
            resp.setContentType((MediaType.TEXT_PLAIN));
            
            try (PrintWriter pw = resp.getWriter())
            {
                pw.println("Incorrect Login");
            }
            
            return;
        }

        // We have successfully login.
        // Generate token.
        
        // Create our custom claims
        
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", req.getRemoteUser());
        claims.put("role", "authenticated");
        
        long exp = System.currentTimeMillis() + (1000 * 60 * 30);
        
        String token = Jwts.builder()
                .setAudience("workshop05")
                .setExpiration(new Date(exp))
                .setIssuer("workshop05")
                .addClaims(claims)
                .signWith(keyMgr.getKey())
                .compact();
        
        JsonObject result = Json.createObjectBuilder()
                .add("token_type", "bearer")
                .add("token", token)
                .build();
         
        HttpSession sess = req.getSession();
        sess.invalidate();
        
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType(MediaType.APPLICATION_JSON);
        
        
        // Return the token.
        try (PrintWriter pw = resp.getWriter())
        {
            pw.print(result.toString());
        }
    }
}