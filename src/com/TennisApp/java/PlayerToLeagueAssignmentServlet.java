package com.TennisApp.java;

import com.TennisApp.java.entity.Player;
import com.TennisApp.java.persistance.League_AssignmentDao;
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
        name = "PlayerToLeagueAssignAction",
        urlPatterns = { "/player-to-league-assign-action" }
)
public class PlayerToLeagueAssignmentServlet extends HttpServlet {

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

        logger.info("In PlayerToLeagueAssignmentServlet... to maintain League Assignments.");
        String url = null;

        // Arrive here, from PlayerMaintenanceServlet, where
        // that Servlet was called from playerSearch results list, selected a player link.
        // Here we will determine if the user has selected Update, Delete, or AssignLeague for a player, and forward to the correct Servlet.
        // Forward to an action Servlet selected in  playerMaintenance.jsp.


        String playerID = (String) session.getAttribute("playerID");
        Integer playerIDInteger = Integer.parseInt(playerID);

        // associate the Message with the request, and clear it before forwarding to JSP page
        String leagueAssignmentMessage = "";
        leagueAssignmentMessage = "You can Remove or Add League Assignments for Id: " + playerID;
        session.setAttribute("leagueAssignmentMessage", leagueAssignmentMessage);

        League_AssignmentDao league_assignmentDao= new League_AssignmentDao();

        // option 1.to create a search object LeagueAssignSearch leagueAssignList, setting leagueAssignSearch.getSearchType() = "assign player to league"
        LeagueAssignSearch leagueAssignSearch = new LeagueAssignSearch();
        leagueAssignSearch.setSearchType("assign player to league");

        leagueAssignSearch = league_assignmentDao.searchForLeagueAssign(leagueAssignSearch);
        session.setAttribute("leagueAssignStatusSearch", leagueAssignSearch);

        if ( !leagueAssignSearch.isFound() ) {
            leagueAssignmentMessage = "No League Assignments found.";
            session.setAttribute("leagueSearchMessage", leagueAssignmentMessage);
        }


        //option 2. directly using method in Dao, without LeagueAssignSearch object:
//        league_assignmentDao.getCurrentLeagueAssignmentsForPlayerId(playerIDInteger);
//        session.setAttribute("playerIdUpdated", playerID);



        logger.info("forward to playerToLeagueAssignment.jsp for assign player to league ") ;

//        url = "/playerToLeagueAssignment.jsp";  // do a redirect using url back to leagueAssignment.jsp page.
//        response.sendRedirect(url);

        url = "/playerToLeagueAssignment.jsp";
        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}

