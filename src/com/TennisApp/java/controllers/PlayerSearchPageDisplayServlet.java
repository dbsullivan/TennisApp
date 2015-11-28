package com.TennisApp.java.controllers;

import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  This class provides a forward to a JSP for Player search (inside PlayerSearch.jsp).
 *
 *@author    Dave Sullivan
 */

@WebServlet(
        name = "PlayerSearch",
        urlPatterns = { "/player-search" }
)
public class PlayerSearchPageDisplayServlet extends HttpServlet {

    /**
     *  Handles HTTP GET requests.
     *
     *@param  request               Description of the Parameter
     *@param  response              Description of the Parameter
     *@exception ServletException  if there is a Servlet failure
     *@exception IOException       if there is an IO failure
     */
    private final Logger logger = Logger.getLogger(PlayerSearchPageDisplayServlet.class);

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {

        logger.info("In PlayerSearchPageDisplayServlet servlet, will forward to playerSearch.jsp");

        // Forward to a JSP page named playerSearch.jsp.
        String url = "/playerSearch.jsp";

        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

}
