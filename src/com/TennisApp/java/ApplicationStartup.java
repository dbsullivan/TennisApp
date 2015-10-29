package com.TennisApp.java;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import org.apache.log4j.Logger;

/**
 * @author Dave Sullivan
 */

@WebServlet(
        name = "applicationStartup",
        urlPatterns = { "/application-startup" } ,
        loadOnStartup = 1
)
public class ApplicationStartup extends HttpServlet {

    private Properties  properties;
    private final Logger logger = Logger.getLogger(ApplicationStartup.class);

    /**
     *  Initialize this servlet
     *
     *@exception  ServletException  if there is  Servlet failure
     */
    public void init() throws ServletException {

        logger.info("ApplicationStartup.init()...begin");

        ServletContext  context = getServletContext();

        String propsPath = "tennisApp.properties";

        loadProperties(propsPath);

        context.setAttribute("appProperties", properties); // load in context so any servlet can get this

        logger.info("ApplicationStartup.init()" + "Uname: " + properties.getProperty("username") +
                "  Pswd: " + properties.getProperty("password") +
                "  Props URL: " + properties.getProperty("url"));

        logger.info("ApplicationStartup.init()...end" );
    }

    public void loadProperties(String propertiesFilePath) {
        properties = new Properties();
        try {
            properties.load (this.getClass().getResourceAsStream(propertiesFilePath));
        } catch (IOException e) {
            logger.error("Can't load the properties file: ", e);
        } catch (Exception e) {
            logger.error("Problem: ", e);
        }
    }
}
