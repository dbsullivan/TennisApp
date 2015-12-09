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

/**
 * Created by Dave on 12/9/2015.
 *  This class gets the entry form parameters and upon submit will either update a League record, delete a League record, or make a player assignment to a League
 *  Input data getParameters from the leagueMaintenance.jsp HTML Form, action to the annotation (league-maintenance-action) to this Servlet.
 *
 *
 *@author    Dave Sullivan
 */
@WebServlet(
        name = "LeagueMaintenanceAction",
        urlPatterns = { "/league-maintenance-action" }
)
public class LeagueMaintenanceServlet extends HttpServlet {

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

        logger.info("In LeagueMaintenanceServlet...get form parms of League, forward to either Update, Delete, or AssignPlayer.");
        String url = null;

        // Arrive here, from LeagueMaintenancePageDisplaySerlvet, where
        // that Servlet was called from leagueSearch results list, selected a league link.
        // Here we will determine if the user has selected Update, Delete, or AssignPlayer for a league, and forward to the correct Servlet.
        // Forward to an action Servlet selected in leagueMaintenance.jsp.

        if (request.getParameter("updateLeagueBtn") != null) {
            url = "/league-update-action";
        } else if (request.getParameter("deleteLeagueBtn") != null) {
            url = "/league-delete-action";
//TODO
//        } else if (request.getParameter("assignPlayerBtn") != null) {
//            url = "/league-assignPlayer-action";
        }

        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}

