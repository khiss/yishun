/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iss.workshop05.app;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import java.io.PrintWriter;
import javax.inject.Inject;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author issuser
 */
@WebFilter(urlPatterns = { "/api/*"},
    dispatcherTypes = DispatcherType.REQUEST)
public class TokenFilter implements Filter {

    @Inject private KeyManager keyMgr;
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;
        
        // Check if the request has the Autorization header.
        
        String authorization = req.getHeader("Authorization");
        
        if (null == authorization)
        {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.setContentType(MediaType.TEXT_PLAIN);
            
            try (PrintWriter pw = resp.getWriter())
            {
               pw.println("Unauthorised !!!");
            }
            
            return;
        }
            
        // Extract and verify token
        String token = authorization.substring("Bearer ".length());
        Jws<Claims> claims;

        try
        {
            JwtParser parser = Jwts.parser().setSigningKey(keyMgr.getKey());
            claims = parser.parseClaimsJws(token);
        }
        catch (Exception ex)
        {
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
            resp.setContentType(MediaType.TEXT_PLAIN);
            
            try (PrintWriter pw = resp.getWriter())
            {
                
               pw.println("Forbidden !!!" + ex.getMessage());
               pw.println("Forbidden !!!" + token);
            }

            return;
        }
        
        System.out.print("Headers: " + claims.getHeader());
        System.out.print("Claims: " + claims.getBody());
        
        chain.doFilter(request, response);
        
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
