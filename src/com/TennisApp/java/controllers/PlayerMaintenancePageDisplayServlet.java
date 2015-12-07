package com.TennisApp.java.controllers;


import com.TennisApp.java.entity.Player;
import com.TennisApp.java.persistance.PlayerDao;
import org.apache.log4j.Logger;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


/**
 *  This class provides a forward to a JSP for Player Maintenance entry (inside playerMaintenance.jsp).
 *
 *@author    Dave Sullivan
 */
@WebServlet(
        name = "PlayerMaintenance",
        urlPatterns = { "/player-maintenance" }
)
public class PlayerMaintenancePageDisplayServlet extends HttpServlet {

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

        logger.info("In PlayerMaintenancePageDisplayServlet servlet, will forward to playerMaintenance.jsp");

        response.setContentType("text/html");
        HttpSession session = request.getSession();

        String playerID = request.getParameter("playerID");  // from the jsp playerSearchResults.jsp
        Integer playerIDInteger = Integer.parseInt(playerID);

        PlayerDao playerDao = new PlayerDao();
        Player player = new Player();
        player = playerDao.getPlayer(playerIDInteger);

        // get values from DaoPlayer lookup to populate PlayerMaintenance.jsp form and setting the attributes

        // These session properties will persist between validations, in the JSP, input tag, text value="${EL item}".
        session.setAttribute("playerID", playerID);
        session.setAttribute("firstName", player.getFirstName());
        session.setAttribute("lastName", player.getLastName());
        session.setAttribute("email",  player.getEmail());
        session.setAttribute("gender", player.getGender());
        session.setAttribute("ntrpLevel", player.getNTRPlevel());
        session.setAttribute("phoneNumber", player.getPhone());

        // associate the Message with the request, and clear it before forwarding to JSP page
        String playerMaintMessage = "For " + player.getFirstName() + " " + player.getLastName()  + " you can Update, Delete, or Assign League";
        session.setAttribute("playerMaintenanceMessage", playerMaintMessage);

        // associate the Message with the request, and clear it before forwarding to JSP page
//        String playerMaintMessage = "";
        String ErrorType = "";
        boolean firstNameErr = false;
        boolean lastNameErr = false;
        boolean emailErr = false;
        boolean genderErr = false;
        boolean ntrpLevelErr = false;
        boolean phoneNumberErr = false;

        // Forward to a JSP page named playerMaintenance.jsp.
        String url = "/playerMaintenance.jsp";

        RequestDispatcher  dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

}

