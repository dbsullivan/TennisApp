package com.TennisApp.java;

import com.TennisApp.java.entity.League;
import com.TennisApp.java.persistance.LeagueDao;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *  This class gets the entry form parameters and upon submit will Insert a new League record.
 *  Input data getParameters from the leagueAdd.jsp HTML Form, action to the annotation (league-add-action) to this Servlet.
 *  following SQL Insert, this servlet is redirected back to the page leagueAdd.jsp.
 *
 *@author    Dave Sullivan
 */
@WebServlet(
        name = "LeagueAddAction",
        urlPatterns = { "/league-add-action" }
)
public class LeagueAddServlet extends HttpServlet {

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

        logger.info("In AddServlet...get form parms of League, set into INSERT sql, return to add.");
        String url = null;

        String leagueName = request.getParameter("leagueName");
        String leaguePlayerSlots = request.getParameter("leaguePlayerSlots");
        String leagueCourtsNeeded = request.getParameter("leagueCourtsNeeded");
        String leagueEvents = request.getParameter("leagueEvents");

        String ntrpLevel = request.getParameter("ntrpLevel");
        String phoneNumber = request.getParameter("phoneNumber");

        int leagueIdAdded = 0;  // default will add new league as 0, else set upon league add
//        session.setAttribute("leagueIdAdded", leagueIdAdded);

        // These session properties will persist between validations, in the JSP, input tag, text value="${EL item}".
        session.setAttribute("leagueName", leagueName);
        session.setAttribute("leaguePlayerSlots", leaguePlayerSlots);
        session.setAttribute("leagueCourtsNeeded", leagueCourtsNeeded);
        session.setAttribute("leagueEvents", leagueEvents);

        session.setAttribute("ntrpLevel", ntrpLevel);
        session.setAttribute("phoneNumber", phoneNumber);

        LeagueDao leagueDao = new LeagueDao();

        // associate the Message with the request, and clear it before forwarding to JSP page
        String AddMessage = "";
        boolean leagueNameErr = false;
        boolean leaguePlayerSlotsErr = false;
        boolean leagueCourtsNeededErr = false;
        boolean leagueEventsErr = false;
        boolean ntrpLevelErr = false;
        boolean phoneNumberErr = false;


        // Validate that all fields have valid data, prior to .Add()
//        WebServiceEmailValidation webServiceEmailValidation = new WebServiceEmailValidation();

        if (leagueName == null || leagueName.equals("") ) {
            AddMessage = "Please enter missing League Name.";
            leagueNameErr = true;
        } else if (leaguePlayerSlots == null || leaguePlayerSlots.equals("")) {
            AddMessage = "Please enter missing Number of League Player Slots.";
            leaguePlayerSlotsErr = true;
        } else if (leagueCourtsNeeded == null || leagueCourtsNeeded.equals("")) {
            AddMessage = "Please enter missing Number of League Courts Needed.";
            leagueCourtsNeededErr = true;
// derive the number of events?  If dropdown values Singles (only for now), players = 8, courts = 4, 3 events needed
            // min of 4 players singles, min of 8 players doubles, max TBD.
            // call object LeagueSetup with methods, to use, NOT NULL, NON-ZERO type=S,players=8,courts=4
            // based on players, calculates if Singles, courts = 1/2 number of players, (later if doubles = 1/4 number of players
            // and based on these, make the calculation
        } else if (leagueEvents == null || leagueEvents.equals("")) {
            AddMessage = "Please enter missing Number of League Events.";
            leagueEventsErr = true;
        } else if (ntrpLevel == null || ntrpLevel.equals("")) {
            AddMessage = "Please enter missing NTRP Level.";
            ntrpLevelErr = true;
        } else if (phoneNumber == null || phoneNumber.equals("")) {
            AddMessage = "Please enter missing Phone Number.";
            phoneNumberErr = true;
        } else {
//            League league = new League(leagueIdAdded, firstName, lastName, email, gender, ntrpLevel, phoneNumber);
//            leagueIdAdded = leagueDao.addLeague(league);
            session.setAttribute("leagueIdAdded", leagueIdAdded);
            AddMessage = "League added. Id: " + leagueIdAdded ;
        }

        session.setAttribute("leagueAddMessage", AddMessage);
        session.setAttribute("leagueNameErr", leagueNameErr);
        session.setAttribute("leaguePlayerSlotsErr", leaguePlayerSlotsErr);
        session.setAttribute("leagueCourtsNeededErr", leagueCourtsNeededErr);
        session.setAttribute("leagueEventsErr", leagueEventsErr);

        session.setAttribute("leaguentrpLevelErr", ntrpLevelErr);
        session.setAttribute("leaguephoneNumberErr", phoneNumberErr);

        url = "/leagueAdd.jsp";  // do a redirect using url back to LeagueAdd.jsp page.
        response.sendRedirect(url);
    }

}
