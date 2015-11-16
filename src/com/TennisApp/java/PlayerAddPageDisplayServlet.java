package com.TennisApp.java;

import com.TennisApp.java.entity.Player;
import org.apache.log4j.Logger;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


/**
 *  This class provides a forward to a JSP for Player Add entry (inside PlayerAdd.jsp).
 *
 *@author    Dave Sullivan
 */
@WebServlet(
        name = "PlayerAdd",
        urlPatterns = { "/player-add" }
)
public class PlayerAddPageDisplayServlet extends HttpServlet {

    /**
     *  Handles HTTP GET requests.
     *
     *@param  request               Description of the Parameter
     *@param  response              Description of the Parameter
     *@exception  ServletException  if there is a Servlet failure
     *@exception  IOException       if there is an IO failure
     */
    private final Logger logger = Logger.getLogger(this.getClass());

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {

        logger.info("In PlayerAddPageDisplayServlet servlet, will forward to PlayerAdd.jsp");

        // Forward to a JSP page named PlayerAdd.jsp.
        String url = "/playerAdd.jsp";

        RequestDispatcher  dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

}
