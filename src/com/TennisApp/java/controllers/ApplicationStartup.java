package com.TennisApp.java.controllers;

import org.apache.log4j.Logger;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


/**
 * @author Dave Sullivan
 */

@WebServlet(
        name = "applicationStartup",
        urlPatterns = { "/application-startup" }
        ,loadOnStartup = 1
)
public class ApplicationStartup extends HttpServlet {
    /**
     *  Initialize this servlet
     *
     *@exception  ServletException  if there is  Servlet failure
     */
    private Properties  properties;
    private final Logger logger = Logger.getLogger(ApplicationStartup.class);

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
            //Dave changed for Intellij properties in resources\ folder
//            properties.load (this.getClass().getResourceAsStream(propertiesFilePath));
            properties.load(this.getClass().getClassLoader().getResourceAsStream(propertiesFilePath));
        } catch (IOException e) {
            logger.error("Can't load the properties file: ", e);
        } catch (Exception e) {
            logger.error("Problem: ", e);
        }
    }
}
