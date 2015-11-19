package com.TennisApp.java;

import com.TennisApp.java.entity.Player;
import com.TennisApp.java.persistance.PlayerDao;
import org.apache.log4j.Logger;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URI;
import java.util.Map;



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

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String ntrpLevel = request.getParameter("ntrpLevel");
        String phoneNumber = request.getParameter("phoneNumber");

        int playerIdAdded = 0;  // default will add new player as 0, else set upon player add
//        session.setAttribute("playerIdAdded", playerIdAdded);

        // These session properties will persist between validations, in the JSP, input tag, text value="${EL item}".
        session.setAttribute("firstName", firstName);
        session.setAttribute("lastName", lastName);
        session.setAttribute("email", email);
        session.setAttribute("gender", gender);
        session.setAttribute("ntrpLevel", ntrpLevel);
        session.setAttribute("phoneNumber", phoneNumber);

        PlayerDao playerDao = new PlayerDao();

        // associate the Message with the request, and clear it before forwarding to JSP page     
        String AddMessage = "";
        boolean firstNameErr = false;
        boolean lastNameErr = false;
        boolean emailErr = false;
        boolean genderErr = false;
        boolean ntrpLevelErr = false;
        boolean phoneNumberErr = false;


        // Validate that all fields have valid data, prior to .Add()
        WebServiceEmailValidation webServiceEmailValidation = new WebServiceEmailValidation();

        if (firstName == null || firstName.equals("") ) {
            AddMessage = "Please enter missing First Name.";
            firstNameErr = true;
        } else if (lastName == null || lastName.equals("")) {
            AddMessage = "Please enter missing Last Name.";
            lastNameErr = true;
        } else if (email == null || email.equals("")) {
            AddMessage = "Please enter missing email.";
            emailErr = true;
            //Now validate using WebService call, since email has passed as Not NULL or blank, call method passing email string
//        } else if ( testEmailWebService(email) == false ) {
        } else if ( !webServiceEmailValidation.isValidEmail(email) ) {
            AddMessage = "Please enter valid email.";
            emailErr = true;
        } else if (gender == null || gender.equals("")) {
            AddMessage = "Please enter missing Gender.";
            genderErr = true;
        } else if (ntrpLevel == null || ntrpLevel.equals("")) {
            AddMessage = "Please enter missing NTRP Level.";
            ntrpLevelErr = true;
        } else if (phoneNumber == null || phoneNumber.equals("")) {
            AddMessage = "Please enter missing Phone Number.";
            phoneNumberErr = true;
        } else {
                Player player = new Player(playerIdAdded, firstName, lastName, email, gender, ntrpLevel, phoneNumber);
                playerIdAdded = playerDao.addPlayer(player);
                session.setAttribute("playerIdAdded", playerIdAdded);
                AddMessage = "Player added. Id: " + playerIdAdded ;
            // player_id should be an attribute set after insert, to allow decision to update existing player, else set 0
//            if ( session.getAttribute("playerIdAdded") == "0") {
//                Player player = new Player(playerIdAdded, firstName, lastName, email, gender, ntrpLevel, phoneNumber);
//                playerIdAdded = playerDao.addPlayer(player);
//                session.setAttribute("playerIdAdded", playerIdAdded);  // override the original 0
//                AddMessage = "Player added.";
//            } else {
//                Player player = new Player(playerIdAdded, firstName, lastName, email, gender, ntrpLevel, phoneNumber);
//                playerDao.addOrUpdatePlayer(player);
//                AddMessage = "Player updated.";
//            }
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
