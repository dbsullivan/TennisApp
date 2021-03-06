package com.TennisApp.java.controllers;

import org.apache.log4j.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * Created by Dave on 10/26/2015.
 *
 * This servlet is the entry-point for the site (in the web.xml   <welcome-file>Welcome</welcome-file> )
 * this servlet will forward to index.jsp, to provide the main menu and welcome (custom tag user greeting).
 */

@WebServlet(
        name = "Welcome",
        urlPatterns = { "/welcome" }
)
public class Welcome  extends HttpServlet {
    /**
     * Handles HTTP GET requests.
     *
     * @param request               the HttpServletRequest object
     * @param response              the HttpServletResponse object
     * @exception ServletException  if there is a Servlet failure
     * @exception IOException       if there is an IO failure
     */
    private final Logger logger = Logger.getLogger(Welcome.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        logger.info("Welcome...begin");

//        response.setContentType("text/html");  // this was missing originally.
        HttpSession session = request.getSession();

//        String  j_username  = String.valueOf(session.getAttribute("username"));  //session Attribute
//        session.setAttribute("username", j_username);
//        logger.info("User Logged in is: " + j_username);
//
//        String  j_username2  = request.getParameter("j_username");  // request parameter Attribute
//        session.setAttribute("username",j_username2);
//        logger.info("User Logged in is: " + j_username2);

        // try another approach, since no parameter seems to be available above // see also GenericPrincipal[] name attribute
//        Principal principal = request.getUserPrincipal(); // is null
////        if (principal != null) {
//            String j_username3= principal.getName(); // Find User by j_username.
//            request.setAttribute("username",j_username3);
//            logger.info("User Logged in is: " + j_username3);
//        }

        // User bean to hold the user name logged in. Set bean here, or using EL in login.jsp??? ASK
//        User user = new User();
//        user.setName(j_username);

        String url;
        url = "/index.jsp";

        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

        logger.info("Welcome...end");

    }

    // Method to handle POST method request.
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}

