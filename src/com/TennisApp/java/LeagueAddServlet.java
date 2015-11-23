package com.TennisApp.java;

import com.TennisApp.java.entity.League;
import com.TennisApp.java.persistance.LeagueDao;
import javafx.util.converter.DateStringConverter;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        String leagueTypeSnglDbls = request.getParameter("leagueTypeSnglDbls");
        String leagueNTRPLevel = request.getParameter("leagueNTRPLevel");
        String leagueStartDate = request.getParameter("leagueStartDate");
        String leagueEndDate = request.getParameter("leagueEndDate");
        String leagueStatus = request.getParameter("leagueStatus");
        int leaguePlayerSlotsInteger = 0;
        int leagueCourtsNeededInteger = 0;
        int leagueEventsInteger = 0;
        Date leagueStartDateDATE = null;
        Date leagueEndDateDATE = null;

        int leagueIdAdded = 0;  // default will add new league as 0, else set upon league add
//        session.setAttribute("leagueIdAdded", leagueIdAdded);

        // These session properties will persist between validations, in the JSP, input tag, text value="${EL item}".
        session.setAttribute("leagueName", leagueName);
        session.setAttribute("leaguePlayerSlots", leaguePlayerSlots);
        session.setAttribute("leagueCourtsNeeded", leagueCourtsNeeded);
        session.setAttribute("leagueEvents", leagueEvents);
        session.setAttribute("leagueTypeSnglDbls", leagueTypeSnglDbls);
        session.setAttribute("leagueNTRPLevel", leagueNTRPLevel);
        session.setAttribute("leagueStartDate", leagueStartDate);
        session.setAttribute("leagueEndDate", leagueEndDate);
        session.setAttribute("leagueStatus", leagueStatus);

        LeagueDao leagueDao = new LeagueDao();

        // associate the Message with the request, and clear it before forwarding to JSP page
        String AddMessage = "";
        boolean leagueNameErr = false;
        boolean leaguePlayerSlotsErr = false;
        boolean leagueCourtsNeededErr = false;
        boolean leagueEventsErr = false;
        boolean leagueTypeSnglDblsErr = false;
        boolean leagueNTRPLevelErr = false;
        boolean leagueStartDateErr = false;
        boolean leagueEndDateErr = false;
        boolean leagueStatusErr = false;


        // Validate that all fields have valid data, prior to .Add()
        UserInputTypeCheck userInputTypeCheck = new UserInputTypeCheck();
        DateValidator dateValidator = new DateValidator();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        dateFormat.setLenient(false);

        if (leagueName == null || leagueName.equals("") ) {
            AddMessage = "Please enter missing League Name.";
            leagueNameErr = true;
        } else if (leagueTypeSnglDbls == null || leagueTypeSnglDbls.equals("")) {
            AddMessage = "Please enter missing League Type Singles or Doubles.";
            leagueTypeSnglDblsErr = true;
        } else if (leaguePlayerSlots == null || leaguePlayerSlots.equals("")) {
            AddMessage = "Please enter missing Number of League Player Slots.";
            leaguePlayerSlotsErr = true;
        } else if ( !userInputTypeCheck.isValidInt(leaguePlayerSlots) ) {
            AddMessage = "Please enter valid number.";
            leaguePlayerSlotsErr = true;
        } else if (leagueCourtsNeeded == null || leagueCourtsNeeded.equals("")) {
            AddMessage = "Please enter missing Number of League Courts Needed.";
            leagueCourtsNeededErr = true;
        } else if ( !userInputTypeCheck.isValidInt(leagueCourtsNeeded) ) {
            AddMessage = "Please enter valid number.";
            leagueCourtsNeededErr = true;
        } else if (leagueEvents == null || leagueEvents.equals("")) {
            AddMessage = "Please enter missing Number of League Events.";
            leagueEventsErr = true;
        } else if ( !userInputTypeCheck.isValidInt(leagueEvents) ) {
            AddMessage = "Please enter valid number.";
            leagueEventsErr = true;
        } else if (leagueNTRPLevel == null || leagueNTRPLevel.equals("")) {
            AddMessage = "Please enter missing League NTRP Level.";
            leagueNTRPLevelErr = true;
        } else if (leagueStartDate == null || leagueStartDate.equals("")) {
            AddMessage = "Please enter missing League Start Date.";
            leagueStartDateErr = true;
        } else if ( !dateValidator.isDateValid(leagueStartDate, "MM/dd/yyyy") ) {
            AddMessage = "Please enter valid date.";
            leagueStartDateErr = true;
        } else if (leagueEndDate == null || leagueEndDate.equals("")) {
            AddMessage = "Please enter missing League End Date.";
            leagueEndDateErr = true;
        } else if ( !dateValidator.isDateValid(leagueEndDate, "MM/dd/yyyy") ) {
            AddMessage = "Please enter valid date.";
            leagueEndDateErr = true;
        } else if (leagueStatus == null || leagueStatus.equals("")) {
            AddMessage = "Please enter missing League Status.";
            leagueStatusErr = true;
        } else {
            // convert any String form inputs to their db types here, i.e.) INT or DATE
            try {
                leaguePlayerSlotsInteger = Integer.parseInt(leaguePlayerSlots);
                leagueCourtsNeededInteger = Integer.parseInt(leagueCourtsNeeded);
                leagueEventsInteger = Integer.parseInt(leagueEvents);
            } catch (Exception e) {
                logger.error("Exception: ", e);
            }

            try {
                leagueStartDateDATE = dateFormat.parse(leagueStartDate.trim());
                leagueEndDateDATE = dateFormat.parse(leagueEndDate.trim());
            } catch (ParseException pe) {
                logger.error("This date could not be parsed, not fatal error", pe);
            }


            League league = new League(leagueIdAdded, leagueName, leaguePlayerSlotsInteger,
                    leagueCourtsNeededInteger, leagueEventsInteger,
                    leagueTypeSnglDbls, leagueNTRPLevel, leagueStartDateDATE, leagueEndDateDATE, leagueStatus);
            leagueIdAdded = leagueDao.addLeague(league);
            session.setAttribute("leagueIdAdded", leagueIdAdded);
            AddMessage = "League added. Id: " + leagueIdAdded ;
        }

        session.setAttribute("leagueAddMessage", AddMessage);
        session.setAttribute("leagueNameErr", leagueNameErr);
        session.setAttribute("leaguePlayerSlotsErr", leaguePlayerSlotsErr);
        session.setAttribute("leagueCourtsNeededErr", leagueCourtsNeededErr);
        session.setAttribute("leagueEventsErr", leagueEventsErr);
        session.setAttribute("leagueTypeSnglDblsErr", leagueTypeSnglDblsErr);
        session.setAttribute("leagueNTRPLevelErr", leagueNTRPLevelErr);
        session.setAttribute("leagueStartDateErr", leagueStartDateErr);
        session.setAttribute("leagueEndDateErr", leagueEndDateErr);
        session.setAttribute("leagueStatusErr", leagueStatusErr);

        url = "/leagueAdd.jsp";  // do a redirect using url back to LeagueAdd.jsp page.
        response.sendRedirect(url);

        // TODO maybe
        // derive the number of events?  If dropdown values Singles (only for now), players = 8, courts = 4, 3 events needed
        // min of 4 players singles, min of 8 players doubles, max TBD.
        // call object LeagueSetup with methods, to use, NOT NULL, NON-ZERO type=S,players=8,courts=4
        // based on players, calculates if Singles, courts = 1/2 number of players, (later if doubles = 1/4 number of players
        // and based on these, make the calculation
    }

}
