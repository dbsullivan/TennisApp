package com.TennisApp.java;

import com.TennisApp.java.entity.Player;
import com.TennisApp.java.persistance.PlayerDao;
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
 * Created by Dave on 12/6/2015.
 *  This class gets the entry form parameters and upon submit will either update a Player record, delete a Player record, or make a league assignment to a Player
 *  Input data getParameters from the playerMaintenance.jsp HTML Form, action to the annotation (player-maintenance-action) to this Servlet.
 *
 *
 *@author    Dave Sullivan
 */
@WebServlet(
        name = "PlayerMaintenanceAction",
        urlPatterns = { "/player-maintenance-action" }
)
public class PlayerMaintenanceServlet extends HttpServlet {

    /**
     * Handles HTTP POST requests.
     *
     * @param request               Description of the Parameter
     * @param response              Description of the Parameter
     * @exception ServletException  if there is a Servlet failure
     * @exception IOException       if there is an IO failure
     */
    private final Logger logger = Logger.getLogger(this.getClass());

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        HttpSession session = request.getSession();

        logger.info("In PlayerMaintenanceServlet...get form parms of Player, forward to either Update, Delete, or AssignLeague.");
        String url = null;

        // Arrive here, from PlayerMaintenancePageDisplaySerlvet, where
        // that Servlet was called from playerSearch results list, selected a player link.
        // Here we will determine if the user has selected Update, Delete, or AssignLeague for a player, and forward to the correct Servlet.
        // Forward to an action Servlet selected in  playerMaintenance.jsp.

        if (request.getParameter("updatePlayerBtn") != null) {
            url = "/player-update-action";
        } else if (request.getParameter("deletePlayerBtn") != null) {
            url = "/player-delete-action";
//TODO
//        } else if (request.getParameter("assignLeagueBtn") != null) {
//            url = "/player-assignLeague-action";
        }

        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
