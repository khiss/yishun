/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshop01web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author issuser
 */
@WebServlet(urlPatterns = { "/weather" } )
public class WeatherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // super.doGet(req, resp); //To change body of generated methods, choose Tools | Templates.

        String city_name = req.getParameter("cityName");
        
        city_name = city_name.trim();
        
        if (city_name.isEmpty()) city_name = "Singapore";
        
        city_name = city_name.toUpperCase();
        
        resp.setStatus(HttpServletResponse.SC_OK);

        resp.setContentType(MediaType.TEXT_HTML);

        try (PrintWriter out = resp.getWriter())
        {
            out.format("<h2>The weather for %s</h2>", city_name);
        }
    }
}
