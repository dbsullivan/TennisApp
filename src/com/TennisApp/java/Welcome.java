package com.TennisApp.java;

import org.apache.log4j.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;


/**
 * Created by Student on 10/26/2015.
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

        logger.info("Welcome.doGet()...begin");

        HttpSession session = request.getSession(true);

        // just want the user name to include in custom tag for an am_pm_greeting:

//        String  j_username  = String.valueOf(session.getAttribute("username"));
//        session.setAttribute("parameterUserNameValue", j_username);
        String  j_username  = request.getParameter("j_username");
        request.setAttribute("parameterUserNameValue",j_username);
        System.out.println("User Logged in is: " + j_username);

        // try another approach, since no parameter seems to be available above
        Principal principal = request.getUserPrincipal(); // is null
//        if (principal != null) {
            String username= principal.getName(); // Find User by j_username.
            request.setAttribute("parameterUserNameValue",username);
            System.out.println("User Logged in is: " + username);
//        }

        String url;
        url = "/index.jsp";

        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

        logger.info("Welcome.doGet()...end");

    }

    // Method to handle POST method request.
//    public void doPost(HttpServletRequest request,
//                       HttpServletResponse response)
//            throws ServletException, IOException {
//        doGet(request, response);
//    }
}

