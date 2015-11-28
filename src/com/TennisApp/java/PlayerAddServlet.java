package com.TennisApp.java;

import com.TennisApp.java.entity.Player;
import com.TennisApp.java.persistance.PlayerDao;
import org.apache.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 *  This class gets the entry form parameters and upon submit will Insert a new Player record.
 *  Input data getParameters from the playerAdd.jsp HTML Form, action to the annotation (player-add-action) to this Servlet.
 *  following SQL Insert, this servlet is redirected back to the page playerAdd.jsp.
 *
 *@author    Dave Sullivan
 */
@WebServlet(
        name = "PlayerAddAction",
        urlPatterns = { "/player-add-action" }
)
public class PlayerAddServlet extends HttpServlet {

    /**
     *  Handles HTTP POST requests.
     *
     *@param  request               Description of the Parameter
     *@param  response              Description of the Parameter
     *@exception ServletException  if there is a Servlet failure
     *@exception IOException       if there is an IO failure
     */
    private final Logger logger = Logger.getLogger(this.getClass());    
    
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        HttpSession session = request.getSession();

        logger.info("In AddServlet...get form parms of Player, set into INSERT sql, return to add.");
        String url = null;

        // get values from form parameters
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String ntrpLevel = request.getParameter("ntrpLevel");
        String phoneNumber = request.getParameter("phoneNumber");

        int playerIdAdded = 0;  // default will add new player as 0, else set upon player add

        // These session properties will persist between validations, in the JSP, input tag, text value="${EL item}".
        session.setAttribute("firstName", firstName);
        session.setAttribute("lastName", lastName);
        session.setAttribute("email", email);
        session.setAttribute("gender", gender);
        session.setAttribute("ntrpLevel", ntrpLevel);
        session.setAttribute("phoneNumber", phoneNumber);


        // associate the Message with the request, and clear it before forwarding to JSP page     
        String AddMessage = "";
        String ErrorType = "";
        boolean firstNameErr = false;
        boolean lastNameErr = false;
        boolean emailErr = false;
        boolean genderErr = false;
        boolean ntrpLevelErr = false;
        boolean phoneNumberErr = false;

        /** Validation logic for JUNIT testing is contained in PlayerValidate.java object
         * send in the Strings, test or any AddMessage <> "", then we can't create a Player object of validated data and types.
         */
        PlayerValidation playerValidation = new PlayerValidation();
        playerValidation.performValidations(firstName, lastName, email, gender, ntrpLevel, phoneNumber);
        AddMessage = playerValidation.getErrorMessage();
        ErrorType = playerValidation.getErrorType();

        // Validate that all fields have valid data, prior to .Add()

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
            // You've passed the audition to be a Player if you return without an ErrorType here
        } else if (ErrorType == "") {
            PlayerDao playerDao = new PlayerDao();
            Player player = new Player(playerIdAdded, firstName, lastName, email, gender, ntrpLevel, phoneNumber);
            playerIdAdded = playerDao.addPlayer(player);
            session.setAttribute("playerIdAdded", playerIdAdded);
            AddMessage = "Player added. Id: " + playerIdAdded ;
        }


        session.setAttribute("playerAddMessage", AddMessage);
        session.setAttribute("playerfirstNameErr", firstNameErr);
        session.setAttribute("playerlastNameErr", lastNameErr);
        session.setAttribute("playerEmailErr", emailErr);
        session.setAttribute("playergenderErr", genderErr);
        session.setAttribute("playerntrpLevelErr", ntrpLevelErr);
        session.setAttribute("playerphoneNumberErr", phoneNumberErr);

        url = "/playerAdd.jsp";  // do a redirect using url back to PlayerAdd.jsp page.
        response.sendRedirect(url);
    }

}    
