package com.TennisApp.java.controllers;


import com.TennisApp.java.entity.League;
import com.TennisApp.java.persistance.LeagueDao;
import org.apache.log4j.Logger;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


/**
 *  This class provides a forward to a JSP for League Maintenance entry (inside leagueMaintenance.jsp).
 *
 *@author    Dave Sullivan
 */
@WebServlet(
        name = "LeagueMaintenance",
        urlPatterns = { "/league-maintenance" }
)
public class LeagueMaintenancePageDisplayServlet extends HttpServlet {

    /**
     *  Handles HTTP GET requests.
     *
     *@param  request               Description of the Parameter
     *@param  response              Description of the Parameter
     *@exception  ServletException  if there is a Servlet failure
     *@exception  IOException       if there is an IO failure
     */
    private final Logger logger = Logger.getLogger(this.getClass());

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {

        logger.info("In LeagueMaintenancePageDisplayServlet servlet, will forward to leagueMaintenance.jsp");

        response.setContentType("text/html");
        HttpSession session = request.getSession();

        String leagueID = request.getParameter("leagueID");  // from the jsp leagueSearchResults.jsp
        Integer leagueIDInteger = Integer.parseInt(leagueID);

        LeagueDao leagueDao = new LeagueDao();
        League league = new League();
        league = leagueDao.getLeague(leagueIDInteger);

        // get values from DaoLeague lookup to populate LeagueMaintenance.jsp form and setting the attributes

        //TODO  set the date upon retrieval using simpledate format, to display mm-dd-yy

        // do league getters, alter dates and integers, then do session sets from these  ?
        // database equivalent data types validated as String, need conversion to db type here.
//        int leaguePlayerSlotsInteger = 0;
//        int leagueCourtsNeededInteger = 0;
//        int leagueEventsInteger = 0;
//        Date leagueStartDateDATE = null;
//        Date leagueEndDateDATE = null;
//
//        try {
//            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
//            dateFormat.setLenient(false);
//            leagueStartDateDATE = dateFormat.parse(league.getStartDate());
//            leagueEndDateDATE = dateFormat.parse( league.getEndDate());
//        } catch (ParseException pe) {
//            logger.error("This date could not be parsed, not fatal error", pe);
//        }


        // These session properties will persist between validations, in the JSP, input tag, text value="${EL item}".
        session.setAttribute("leagueID", leagueID);
        session.setAttribute("leagueName", league.getLeagueName());
        session.setAttribute("leaguePlayerSlots", league.getNumPlayerSlots());
        session.setAttribute("leagueCourtsNeeded", league.getNumCourtsNeeded());
        session.setAttribute("leagueEvents", league.getNumEvents());
        session.setAttribute("leagueTypeSnglDbls", league.getTypeSinglesDoubles());
        session.setAttribute("leagueNTRPLevel", league.getLevel());
        session.setAttribute("leagueStartDate", league.getStartDate());
        session.setAttribute("leagueEndDate", league.getEndDate());
        session.setAttribute("leagueStatus", league.getStatus());

        // associate the Message with the request, and clear it before forwarding to JSP page
        String leagueMaintMessage = "For " + league.getLeagueName() + " you can Update, Delete, or Assign Player";
        session.setAttribute("leagueMaintenanceMessage", leagueMaintMessage);

        // associate the Message with the session, and clear it before forwarding to JSP page
//        String leagueMaintMessage = "";
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

        // Forward to a JSP page named leagueMaintenance.jsp.
        String url = "/leagueMaintenance.jsp";

        RequestDispatcher  dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

}


