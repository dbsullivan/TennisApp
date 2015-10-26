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
        System.out.println("ApplicationStartup.init()...begin" );  // in Catalina, we want to see it ran

        ServletContext  context = getServletContext();

        String propsPath = "tennisApp.properties";

        loadProperties(propsPath);

        context.setAttribute("appProperties", properties); // load in context so any servlet can get this

        logger.info("ApplicationStartup.init()" + "Uname: " + properties.getProperty("username") +
                "  Pswd: " + properties.getProperty("password") +
                "  Props URL: " + properties.getProperty("url"));

        System.out.println("ApplicationStartup.init()" + "Uname: " + properties.getProperty("username") +
                "  Pswd: " + properties.getProperty("password") +
                "  Props URL: " + properties.getProperty("url")); // test

        logger.info("ApplicationStartup.init()...end" );
        System.out.println("ApplicationStartup.init()...end" );  // in Catalina, we want to see it ran


    }

    /**
     *  Handles HTTP GET requests.
     *
     *@param request               the HttpServletRequest object
     *@param response              the HttpServletResponse object
     *@exception ServletException  if there is a Servlet failure
     *@exception IOException       if there is an IO failure
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        logger.info("Application Startup.doGet()...begin");
      System.out.println("Application Startup.doGet()...begin");

        // Ask, we never get here. POST Action overrides to FBA, how can I come back here to go from login.jsp to index.jsp?

        HttpSession session = request.getSession();

        session.setAttribute("appProperties", properties);  // load in context so any servlet can get this

        String userLoggedIn = (String) session.getAttribute("userLoggedIn"); // initially empty, will get from login.jsp, else to to index.jsp with name
        String url;

        if (userLoggedIn == null || userLoggedIn.equals("")) {
            session.setAttribute("userLoggedIn", "");
            url = "/login.jsp";
        } else {
            session.setAttribute("userLoggedIn", request.getParameter("j_username"));
            url = "/index.jsp";
        }

        // ASK - Instead, use EL in template_main_content.jsp to chose between login.jsp
//        String url =  "/login.jsp";

        RequestDispatcher  dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
        // ASK - forward doesn't work as expected, browser stays with login.jsp, and not seeing doGet (doPost) execute ???

        logger.info("Application Startup.doGet()...end");
        System.out.println("Application Startup.doGet()...end");

    }

//    //ASK - POST not handled here either, may need the do Post to call the doGet since FormBasedAuth uses method=post
//    public void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws IOException, ServletException
//    {
//        doGet(request, response);
//    }

    public void loadProperties(String propertiesFilePath) {
        properties = new Properties();
        try {
            properties.load (this.getClass().getResourceAsStream(propertiesFilePath));
        } catch (IOException e) {
            logger.error("Can't load the properties file: ", e);
            System.out.println("Can't load the properties file");
            e.printStackTrace();
        } catch (Exception e) {
            logger.error("Problem: ", e);
            System.out.println("Problem: " + e);
            e.printStackTrace();
        }
    }

/**
 *  The main program for the ApplicationStartup class
 *
 *@param  args  The command line arguments
 *
 */
//    public static void main(String[] args) {
//        ApplicationStartup startApp = new ApplicationStartup();
//
//    }


}
