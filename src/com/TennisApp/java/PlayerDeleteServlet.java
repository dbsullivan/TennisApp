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
 * Created by Dave on 12/7/2015.
 *  This servlet will delete a Player record.
 *  Edits will need to be performed, such as if the player has any League_Assign records;
 *  then we wouldn't want to break that data integrity, and require that they be removed from the League_Assign before deletion.
 *
 *@author    Dave Sullivan
 */
@WebServlet(
        name = "PlayerDeleteAction",
        urlPatterns = { "/player-delete-action" }
)
public class PlayerDeleteServlet extends HttpServlet {

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

        logger.info("In PlayerDeleteServlet...Delete validations applied here.");
        String url = null;

        String playerID = (String) session.getAttribute("playerID");
        Integer playerIDInteger = Integer.parseInt(playerID);

        String playerMaintMessage = "";
        String ErrorType = "";
        boolean playerHasLeagueAssignment = false;

        /**
         *  Edits will need to be performed, such as if the player has any League_Assign records;
         *  then we wouldn't want to break that data integrity, and require that they be removed from the League_Assign before deletion.
         */


        // TODO add this Dao and look for exists. Eventually, add the list of league_assignments to the playerMaintenance.jsp as well.
        // create a method that checks for existance in any league_assign for this player
        // LeagueAssignDao leagueAssignDao = new LeagueAssignDao();

            // You've passed the audition to delete a Player if you return without an ErrorType here
//            if (ErrorType == "") {
            PlayerDao playerDao = new PlayerDao();
            playerDao.deletePlayer(playerIDInteger);
            session.setAttribute("playerIdDeleted", playerID);
            playerMaintMessage = "Player deleted. Id: " + playerID;
//        }

        // Forward to a JSP page named playerMaintenance.jsp.

        session.setAttribute("playerMaintenanceMessage", playerMaintMessage);
//        session.setAttribute("playerID", playerID);
        session.setAttribute("firstName", "");
        session.setAttribute("lastName", "");
        session.setAttribute("email", "");
        session.setAttribute("gender", "");
        session.setAttribute("ntrpLevel","");
        session.setAttribute("phoneNumber", "");
        url = "/playerMaintenance.jsp";

        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}


