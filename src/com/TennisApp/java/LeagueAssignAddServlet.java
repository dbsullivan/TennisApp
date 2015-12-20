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
 * Created by Dave on 12/20/2015.
 *  This servlet will add a League_Assignment record.
 *  Edits will need to be performed, such as if the league has any League_Assign existing records (players in the League);
 *
 *@author    Dave Sullivan
 */
@WebServlet(
        name = "LeagueAssignAddAction",
        urlPatterns = { "/league-assign-add-action" }
)
public class LeagueAssignAddServlet extends HttpServlet {

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

        logger.info("In LeagueAssignAddServlet...Add validations applied here.");
        String url = null;

//TODO all of the add here, return to same playerToLeagueAssignment.jsp  Similar coding sample see LeagueUpdateServlet.java
//  get session and request attributes from the dropdown list parameter "newSelectedLeague", for leagueID selected
// for playerID.  Will determine next Slot in the Add Dao.

//        String leagueAssignID = (String) session.getAttribute("leagueAssignID");
//        Integer leagueAssignIDInteger = Integer.parseInt(leagueAssignID);


//        String leagueID = request.getParameter("selectedLeague");
//        Integer leagueIDInteger = Integer.parseInt(leagueID);

        String playerID = (String) session.getAttribute("playerID");
        Integer playerIDInteger = Integer.parseInt(playerID);


        Object leagueAssignStatusSearch = session.getAttribute("leagueAssignStatusSearch");

        String leagueAssignmentMessage = "";
        String ErrorType = "";
        boolean leagueHasLeagueAssignment = false;

        //TODO , See LeagueAddServlet.java for similar processing logic

        if(request.getParameter("newSelectedLeague") != null) {
            // get values from form parameters
            String leagueID = request.getParameter("selectedLeague");
            Integer leagueIDInteger = Integer.parseInt(leagueID);

            request.setAttribute("selectedLeague", leagueIDInteger ); // set the new League Id selected from dropdown.
           // edits are here if league is full, slots, etc. return to show added assignment in playerToLeagueAssignment.jsp
        } else {
            leagueAssignmentMessage = "You must select from the drop down, a new League for player Id: " + playerID;
            session.setAttribute("leagueAssignmentMessage", leagueAssignmentMessage);
        }

        leagueAssignmentMessage = "Not ready yet.";
        session.setAttribute("leagueAssignmentMessage", leagueAssignmentMessage);

        url = "/playerToLeagueAssignment.jsp";

        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}





