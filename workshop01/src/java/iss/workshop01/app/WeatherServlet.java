package iss.workshop01.app;

import java.io.IOException;
import java.io.PrintWriter;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author issuser
 */
@WebServlet(urlPatterns = { "/weather" })
public class WeatherServlet extends HttpServlet {

    private static final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather";
    private static final String APPID = "307b34d0a0eb2cdbaf7c8e6485b72233";

    private Client client;

    //Initialize the client
    @Override
    public void init() throws ServletException {
        client = ClientBuilder.newClient();
    }
    
    //Cleanup 
    @Override
    public void destroy() {
        client.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // super.doGet(req, resp); //To change body of generated methods, choose Tools | Templates.

        String city_name = req.getParameter("cityName");
        
        //If cityName is not provided, then default to Singapore
        if ((city_name == null) || (city_name.trim()      .length() <= 0)) {
            city_name = "Singapore";
        }
        
        // Create Target
        
        WebTarget target = client.target(WEATHER_URL);
        
        target = target.queryParam("q", city_name);
        target = target.queryParam("appid", APPID);
        target = target.queryParam("units", "metric");
        
        Invocation.Builder inv = target.request(MediaType.APPLICATION_JSON);
        JsonObject results = inv.get(JsonObject.class);
        
        JsonArray weatherDetails = results.getJsonArray("weather");
        
        log("Results: " + results);
        
        resp.setStatus(HttpServletResponse.SC_OK);

        resp.setContentType(MediaType.TEXT_HTML);

        try (PrintWriter out = resp.getWriter())
        {
            out.format("<h2>The weather for %s</h2>", city_name);
            // out.print(results);
            
            for (int i=0; i < weatherDetails.size(); i++)
            {
                JsonObject wd = weatherDetails.getJsonObject(i);
                
                String main = wd.getString("main");
                String description = wd.getString("description");
                String icon = wd.getString("icon");
                
                out.print("<div>");
                out.print(main + " &dash; " + description);
                out.printf("<img src=\"http://openweathermap.org/img/w/%s.png\">", icon);
                out.print("</div>");
                
            }
        }
    }
}
