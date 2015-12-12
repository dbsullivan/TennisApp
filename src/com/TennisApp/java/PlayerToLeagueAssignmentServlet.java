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

        logger.info("In PlayerToLeagueAssignmentServlet... to maintain AssignLeague.");
        String url = null;

        // Arrive here, from PlayerMaintenanceServlet, where
        // that Servlet was called from playerSearch results list, selected a player link.
        // Here we will determine if the user has selected Update, Delete, or AssignLeague for a player, and forward to the correct Servlet.
        // Forward to an action Servlet selected in  playerMaintenance.jsp.

        String playerID = (String) session.getAttribute("playerID");
        Integer playerIDInteger = Integer.parseInt(playerID);

        // get values from form parameters
//        String firstName = request.getParameter("firstName");  // drop down of available leagues to assign to player

        // associate the Message with the session, and clear it before forwarding to JSP page
        String leagueAssignmentMessage = "";
        String ErrorType = "";
//        boolean firstNameErr = false;
        // todo - validate logic separte module start here, see PlayerUpdateServlet and

        /** Validation logic for JUNIT testing is contained in PlayerValidate.java object
         * send in the Strings, test or any UpdateMessage <> "", then we can't update a Player object of validated data and types.
         */
        PlayerAssignToLeagueValidation playerAssignToLeagueValidation = new PlayerAssignToLeagueValidation();
        playerAssignToLeagueValidation.performValidations(firstName, lastName, email, gender, ntrpLevel, phoneNumber); // have playerId in session object? nothing to pass
        leagueAssignmentMessage = playerAssignToLeagueValidation.getErrorMessage();
        ErrorType = playerAssignToLeagueValidation.getErrorType();




        url = "/leagueAssignment.jsp";  // do a redirect using url back to leagueAssignment.jsp page.
        response.sendRedirect(url);

//        url = "/player-assignLeague-action";
//        RequestDispatcher dispatcher =
//                getServletContext().getRequestDispatcher(url);
//        dispatcher.forward(request, response);
    }
}

