package com.TennisApp.java;

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
        urlPatterns = { "/application-startup" } ,
        loadOnStartup = 1
)
public class ApplicationStartup extends HttpServlet {

    private Properties  properties;
//    private EmployeeDirectory  employees;

    /**
     *  Initialize this servlet
     *
     *@exception  ServletException  Description of the Exception
     */
    public void init() throws ServletException {
        System.out.println("ApplicationStartup.init()...begin" );  // in Catalina, we want to see it ran

        ServletContext  context = getServletContext();

        String propsPath = "tennisApp.properties";

        loadProperties(propsPath);

        context.setAttribute("appProperties", properties); // load in context so any servlet can get this

        System.out.println("ApplicationStartup.init()" + "Uname: " + properties.getProperty("username") +
                "  Pswd: " + properties.getProperty("password") +
                "  Props URL: " + properties.getProperty("url")); // test

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
        System.out.println("Application Startup.doGet()...begin");

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

        RequestDispatcher  dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
        // ASK - forward doesn't behave right, browser stays with login.jsp ???

        System.out.println("Application Startup.doGet()...end");

    }

    // may need the do Post to call the doGet since FormBasedAuth uses method=post
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
        doGet(request, response);
    }

    public void loadProperties(String propertiesFilePath) {
        properties = new Properties();
        try {
            properties.load (this.getClass().getResourceAsStream(propertiesFilePath));
        } catch (IOException ioe) {
            System.out.println("Can't load the properties file");
            ioe.printStackTrace();
        } catch (Exception e) {
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
