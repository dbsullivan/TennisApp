package com.TennisApp.java;

import com.TennisApp.java.entity.League;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Dave on 12/7/2015.
 *  This servlet will update a League record
 *
 *@author    Dave Sullivan
 */
@WebServlet(
        name = "LeagueUpdateAction",
        urlPatterns = { "/league-update-action" }
)
public class LeagueUpdateServlet extends HttpServlet {

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

        logger.info("In LeagueUpdateServlet...Update validations applied here.");
        String url = null;

        String leagueID = (String) session.getAttribute("leagueID");
        Integer leagueIDInteger = Integer.parseInt(leagueID);

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

        League leagueToUpdate = new League();
        leagueToUpdate.setLeagueId(leagueIDInteger);
        leagueToUpdate.setLeagueName(leagueName);
        leagueToUpdate.setNumPlayerSlots(leaguePlayerSlotsInteger);
        leagueToUpdate.setNumCourtsNeeded(leagueCourtsNeededInteger);
        leagueToUpdate.setNumEvents(leagueEventsInteger);
        leagueToUpdate.setTypeSinglesDoubles(leagueTypeSnglDbls);
        leagueToUpdate.setLevel(leagueNTRPLevel);
        leagueToUpdate.setStartDate(leagueStartDateDATE);
        leagueToUpdate.setEndDate(leagueEndDateDATE);
        leagueToUpdate.setStatus(leagueStatus);

        // associate the Message with the session, and clear it before forwarding to JSP page
        String leagueMaintMessage = "";
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
         * send in the Strings, test or any UpdateMessage <> "", then we can't update a League object of validated data and types.
         */
        LeagueValidation leagueValidation = new LeagueValidation();
        leagueValidation.performValidations( leagueName,  leagueTypeSnglDbls,  leaguePlayerSlots,  leagueCourtsNeeded,
                leagueEvents,  leagueNTRPLevel,  leagueStartDate,  leagueEndDate,  leagueStatus);
        leagueMaintMessage = leagueValidation.getErrorMessage();
        ErrorType = leagueValidation.getErrorType();

        // Validate that all fields have valid data, prior to .Update()
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
            leagueDao.addOrUpdateLeague(leagueToUpdate);
            session.setAttribute("leagueIdUpdated", leagueID);
            leagueMaintMessage = "League updated. Id: " + leagueID;
        }

        session.setAttribute("leagueName", leagueName);
        session.setAttribute("leaguePlayerSlots", leaguePlayerSlots);
        session.setAttribute("leagueCourtsNeeded", leagueCourtsNeeded);
        session.setAttribute("leagueEvents", leagueEvents);
        session.setAttribute("leagueTypeSnglDbls", leagueTypeSnglDbls);
        session.setAttribute("leagueNTRPLevel", leagueNTRPLevel);
        session.setAttribute("leagueStartDate", leagueStartDate);
        session.setAttribute("leagueEndDate", leagueEndDate);
        session.setAttribute("leagueStatus", leagueStatus);

        session.setAttribute("leagueMaintenanceMessage", leagueMaintMessage);
//        session.setAttribute("leagueAddMessage", AddMessage);
        session.setAttribute("leagueNameErr", leagueNameErr);
        session.setAttribute("leaguePlayerSlotsErr", leaguePlayerSlotsErr);
        session.setAttribute("leagueCourtsNeededErr", leagueCourtsNeededErr);
        session.setAttribute("leagueEventsErr", leagueEventsErr);
        session.setAttribute("leagueTypeSnglDblsErr", leagueTypeSnglDblsErr);
        session.setAttribute("leagueNTRPLevelErr", leagueNTRPLevelErr);
        session.setAttribute("leagueStartDateErr", leagueStartDateErr);
        session.setAttribute("leagueEndDateErr", leagueEndDateErr);
        session.setAttribute("leagueStatusErr", leagueStatusErr);

        // Forward to a JSP page named leagueMaintenance.jsp.
        url = "/leagueMaintenance.jsp";

        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}


