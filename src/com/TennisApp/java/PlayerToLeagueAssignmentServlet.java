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
 *  This class gets the League_Assignment records to populate the LeagueAssignSearch attributes for a player selected earlier,
 *  from PlayerMaintenanceServlet, where, action to the annotation (/player-to-league-assign-action) to this Servlet.
 *  That Servlet was called from playerSearch results list, selected a player link.
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

        String playerID = (String) session.getAttribute("playerID");
        Integer playerIDInteger = Integer.parseInt(playerID);

        // associate the Message with the request, and clear it before forwarding to JSP page
        String leagueAssignmentMessage = "";
        leagueAssignmentMessage = "You can Remove or Add League Assignments for player Id: " + playerID;
        session.setAttribute("leagueAssignmentMessage", leagueAssignmentMessage);

        // create a search object LeagueAssignSearch , setting leagueAssignSearch.getSearchType() = "assign player to league"
        LeagueAssignSearch leagueAssignSearch = new LeagueAssignSearch();
        leagueAssignSearch.setSearchType("assign player to league");
        leagueAssignSearch.setSearchTerm(playerID);

        League_AssignmentDao league_assignmentDao= new League_AssignmentDao();
        leagueAssignSearch = league_assignmentDao.searchForLeagueAssign(leagueAssignSearch);
        session.setAttribute("leagueAssignStatusSearch", leagueAssignSearch);

        logger.info("leagueAssignStatusSearch: " + session.getAttribute("leagueAssignStatusSearch")) ;

        if ( !leagueAssignSearch.isFound() ) {
            leagueAssignmentMessage = "No League Assignments found.";
            session.setAttribute("leagueSearchMessage", leagueAssignmentMessage);
        }

        logger.info("forward to playerToLeagueAssignment.jsp for assign player to league ") ;

        url = "/playerToLeagueAssignment.jsp";
        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}

