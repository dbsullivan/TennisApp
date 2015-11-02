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

        HttpSession session = request.getSession();

        String userLoggedIn = (String) session.getAttribute("userLoggedIn"); // initially empty, will get from login.jsp, else to to index.jsp with name
        String url;

        // create userLoggedInBean store - id for now, present in the welcome servlet, and available if needed.

//        if (userLoggedIn == null || userLoggedIn.equals("")) {
//            session.setAttribute("userLoggedIn", "");
//            url = "/login.jsp";
//        } else {
//            session.setAttribute("userLoggedIn", request.getParameter("j_username"));
//            url = "/index.jsp";
//        }
        url = "/index.jsp";

        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

        logger.info("Welcome.doGet()...end");

    }
}


