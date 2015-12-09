package com.TennisApp.java;


import com.TennisApp.java.persistance.LeagueDao;
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
 * Created by Dave on 12/9/2015.
 *  This servlet will delete a League record.
 *  Edits will need to be performed, such as if the league has any League_Assign records (players in the League);
 *  then we wouldn't want to break that data integrity, and require that players be removed from the League_Assign before deletion.
 *
 *@author    Dave Sullivan
 */
@WebServlet(
        name = "LeagueDeleteAction",
        urlPatterns = { "/league-delete-action" }
)
public class LeagueDeleteServlet extends HttpServlet {

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

        logger.info("In LeagueDeleteServlet...Delete validations applied here.");
        String url = null;

        String leagueID = (String) session.getAttribute("leagueID");
        Integer leagueIDInteger = Integer.parseInt(leagueID);

        String leagueMaintMessage = "";
        String ErrorType = "";
        boolean leagueHasLeagueAssignment = false;

        /**
         *  Edits will need to be performed, such as if the league has any League_Assign records of players in this league;
         *  then we wouldn't want to break that data integrity, and require that they be removed from the League_Assign before deletion.
         */


        // TODO add this Dao and look for exists. Eventually, add the list of league_assignments to the leagueMaintenance.jsp as well.
        // create a method that checks for existance in any league_assign for this league
        // LeagueAssignDao leagueAssignDao = new LeagueAssignDao();

        // You've passed the audition to delete a League if you return without an ErrorType here
//            if (ErrorType == "") {
        LeagueDao leagueDao = new LeagueDao();
        leagueDao.deleteLeague(leagueIDInteger);
        session.setAttribute("leagueIdDeleted", leagueID);
        leagueMaintMessage = "League deleted. Id: " + leagueID;
//        }

        // Forward to a JSP page named leagueMaintenance.jsp.

        session.setAttribute("leagueMaintenanceMessage", leagueMaintMessage);
//        session.setAttribute("leagueID", leagueID);
        session.setAttribute("leagueName", "");
        session.setAttribute("leaguePlayerSlots", "");
        session.setAttribute("leagueCourtsNeeded", "");
        session.setAttribute("leagueEvents", "");
        session.setAttribute("leagueTypeSnglDbls", "");
        session.setAttribute("leagueNTRPLevel", "");
        session.setAttribute("leagueStartDate", "");
        session.setAttribute("leagueEndDate", "");
        session.setAttribute("leagueStatus", "");
        url = "/leagueMaintenance.jsp";

        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}



