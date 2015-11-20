package com.TennisApp.java;

import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Dave on 11/19/2015.
 */

/**
 *  This class provides a forward to a JSP for League Add entry (inside LeagueAdd.jsp).
 *
 *@author    Dave Sullivan
 */
@WebServlet(
        name = "LeagueAdd",
        urlPatterns = { "/league-add" }
)
public class LeagueAddPageDisplayServlet extends HttpServlet {

    /**
     *  Handles HTTP GET requests.
     *
     *@param  request               Description of the Parameter
     *@param  response              Description of the Parameter
     *@exception ServletException  if there is a Servlet failure
     *@exception IOException       if there is an IO failure
     */
    private final Logger logger = Logger.getLogger(this.getClass());

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {

        logger.info("In LeagueAddPageDisplayServlet servlet, will forward to LeagueAdd.jsp");

        // Forward to a JSP page named LeagueAdd.jsp.
        String url = "/leagueAdd.jsp";

        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

}
