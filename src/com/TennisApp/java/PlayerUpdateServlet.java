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
 *  This servlet will update a Player record
 *
 *@author    Dave Sullivan
 */
@WebServlet(
        name = "PlayerUpdateAction",
        urlPatterns = { "/player-update-action" }
)
public class PlayerUpdateServlet extends HttpServlet {

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

        logger.info("In PlayerUpdateServlet...Update validations applied here.");
        String url = null;

        String playerID = (String) session.getAttribute("playerID");
        Integer playerIDInteger = Integer.parseInt(playerID);

        // get values from form parameters
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String ntrpLevel = request.getParameter("ntrpLevel");
        String phoneNumber = request.getParameter("phoneNumber");

        // associate the Message with the session, and clear it before forwarding to JSP page
        String playerMaintMessage = "";
        String ErrorType = "";
        boolean firstNameErr = false;
        boolean lastNameErr = false;
        boolean emailErr = false;
        boolean genderErr = false;
        boolean ntrpLevelErr = false;
        boolean phoneNumberErr = false;

        /** Validation logic for JUNIT testing is contained in PlayerValidate.java object
         * send in the Strings, test or any UpdateMessage <> "", then we can't update a Player object of validated data and types.
         */
        PlayerValidation playerValidation = new PlayerValidation();
        playerValidation.performValidations(firstName, lastName, email, gender, ntrpLevel, phoneNumber);
        playerMaintMessage = playerValidation.getErrorMessage();
        ErrorType = playerValidation.getErrorType();

        // Validate that all fields have valid data, prior to update()

        if (ErrorType == "firstNameErr") {
            firstNameErr = true;
        } else if (ErrorType == "lastNameErr") {
            lastNameErr = true;
        } else if (ErrorType == "emailErr") {
            emailErr = true;
        } else if (ErrorType == "genderErr") {
            genderErr = true;
        } else if (ErrorType == "ntrpLevelErr") {
            ntrpLevelErr = true;
        } else if (ErrorType == "phoneNumberErr") {
            phoneNumberErr = true;
            // You've passed the audition to update a Player if you return without an ErrorType here
        } else if (ErrorType == "") {

            Player playerToUpdate = new Player();
            playerToUpdate.setPlayerId(playerIDInteger);
            playerToUpdate.setFirstName(firstName);
            playerToUpdate.setLastName(lastName);
            playerToUpdate.setEmail(email);
            playerToUpdate.setGender(gender);
            playerToUpdate.setNTRPlevel(ntrpLevel);
            playerToUpdate.setPhone(phoneNumber);

            PlayerDao playerDao = new PlayerDao();
            playerDao.addOrUpdatePlayer(playerToUpdate);
            session.setAttribute("playerIdUpdated", playerID);
            playerMaintMessage = "Player updated. Id: " + playerID;
        }


        session.setAttribute("playerID", playerID);
        session.setAttribute("firstName", firstName);
        session.setAttribute("lastName", lastName);
        session.setAttribute("email", email);
        session.setAttribute("gender", gender);
        session.setAttribute("ntrpLevel", ntrpLevel);
        session.setAttribute("phoneNumber", phoneNumber);

        session.setAttribute("playerMaintenanceMessage", playerMaintMessage);
        session.setAttribute("playerfirstNameErr", firstNameErr);
        session.setAttribute("playerlastNameErr", lastNameErr);
        session.setAttribute("playerEmailErr", emailErr);
        session.setAttribute("playergenderErr", genderErr);
        session.setAttribute("playerntrpLevelErr", ntrpLevelErr);
        session.setAttribute("playerphoneNumberErr", phoneNumberErr);

        // Forward to a JSP page named playerMaintenance.jsp.
        url = "/playerMaintenance.jsp";

        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}

