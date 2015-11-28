package com.TennisApp.java;

import com.TennisApp.java.entity.League;
import com.TennisApp.java.persistance.LeagueDao;
import com.TennisApp.java.utilities.DateValidator;
import com.TennisApp.java.utilities.UserInputTypeCheck;
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
 *@author    Dave Sullivann
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

        // get values from form parameters
        String leagueName = request.getParameter("leagueName");
        String leaguePlayerSlots = request.getParameter("leaguePlayerSlots");
        String leagueCourtsNeeded = request.getParameter("leagueCourtsNeeded");
        String leagueEvents = request.getParameter("leagueEvents");
        String leagueTypeSnglDbls = request.getParameter("leagueTypeSnglDbls");
        String leagueNTRPLevel = request.getParameter("leagueNTRPLevel");
        String leagueStartDate = request.getParameter("leagueStartDate");
        String leagueEndDate = request.getParameter("leagueEndDate");
        String leagueStatus = request.getParameter("leagueStatus");

        // database equivalent data types validated as String, need conversion to db type here.
        int leaguePlayerSlotsInteger = 0;
        int leagueCourtsNeededInteger = 0;
        int leagueEventsInteger = 0;
        Date leagueStartDateDATE = null;
        Date leagueEndDateDATE = null;

        int leagueIdAdded = 0;  // default will add new league as 0, else set upon league add

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


        // associate the Message with the request, and clear it before forwarding to JSP page
        String AddMessage = "";
        String ErrorType = "";
        boolean leagueNameErr = false;
        boolean leaguePlayerSlotsErr = false;
        boolean leagueCourtsNeededErr = false;
        boolean leagueEventsErr = false;
        boolean leagueTypeSnglDblsErr = false;
        boolean leagueNTRPLevelErr = false;
        boolean leagueStartDateErr = false;
        boolean leagueEndDateErr = false;
        boolean leagueStatusErr = false;

        /** Validation logic for JUNIT testing is contained in LeagueValidate.java object
         * send in the Strings, test or any AddMessage <> "", then we can't create a Player object of validated data and types.
         */
        LeagueValidation leagueValidation = new LeagueValidation();
        leagueValidation.performValidations( leagueName,  leagueTypeSnglDbls,  leaguePlayerSlots,  leagueCourtsNeeded,
                 leagueEvents,  leagueNTRPLevel,  leagueStartDate,  leagueEndDate,  leagueStatus);
        AddMessage = leagueValidation.getErrorMessage();
        ErrorType = leagueValidation.getErrorType();

        // Validate that all fields have valid data, prior to .Add()
        if (ErrorType == "leagueNameErr") {
            leagueNameErr = true;
        } else if (ErrorType == "leagueTypeSnglDblsErr") {
            leagueTypeSnglDblsErr = true;
        } else if (ErrorType == "leaguePlayerSlotsErr") {
            leaguePlayerSlotsErr = true;
        } else if (ErrorType == "leagueCourtsNeededErr") {
            leagueCourtsNeededErr = true;
        } else if (ErrorType == "leagueEventsErr") {
            leagueEventsErr = true;
        } else if (ErrorType == "leagueNTRPLevelErr") {
            leagueNTRPLevelErr = true;
        } else if (ErrorType == "leagueStartDateErr") {
            leagueStartDateErr = true;
        } else if (ErrorType == "leagueEndDateErr") {
            leagueEndDateErr = true;
        } else if (ErrorType == "leagueStatusErr") {
            leagueStatusErr = true;
        // You've passed the audition to be a League if you return without an ErrorType here
        } else if (ErrorType == "") {
            // convert any String form inputs to their db types here, i.e.) INT or DATE
            try {
                leaguePlayerSlotsInteger = Integer.parseInt(leaguePlayerSlots);
                leagueCourtsNeededInteger = Integer.parseInt(leagueCourtsNeeded);
                leagueEventsInteger = Integer.parseInt(leagueEvents);
            } catch (Exception e) {
                logger.error("Exception: ", e);
            }

            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                dateFormat.setLenient(false);
                leagueStartDateDATE = dateFormat.parse(leagueStartDate.trim());
                leagueEndDateDATE = dateFormat.parse(leagueEndDate.trim());
            } catch (ParseException pe) {
                logger.error("This date could not be parsed, not fatal error", pe);
            }

            LeagueDao leagueDao = new LeagueDao();
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
