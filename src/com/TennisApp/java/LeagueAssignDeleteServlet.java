package com.TennisApp.java;


import com.TennisApp.java.persistance.LeagueDao;
import com.TennisApp.java.persistance.League_AssignmentDao;
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
 * Created by Dave on 12/13/2015.
 *  This servlet will delete a League_Assignment record.
 *  Edits will need to be performed, such as if the league has any League_Assign records (players in the League);
 *  then we wouldn't want to break that data integrity, and require that players be removed from the League_Assign before deletion.
 *
 *@author    Dave Sullivan
 */
@WebServlet(
        name = "LeagueAssignDeleteAction",
        urlPatterns = { "/league-assign-delete-action" }
)
public class LeagueAssignDeleteServlet extends HttpServlet {

    /**
     * Handles HTTP GET requests.
     *
     * @param request               Description of the Parameter
     * @param response              Description of the Parameter
     * @exception ServletException  if there is a Servlet failure
     * @exception IOException       if there is an IO failure
     */
    private final Logger logger = Logger.getLogger(this.getClass());

    public void doGet(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        HttpSession session = request.getSession();

        logger.info("In LeagueAssignDeleteServlet...Delete validations applied here.");
        String url = null;

//        String leagueAssignId = (String) session.getAttribute("leagueAssignId");
        String leagueAssignId = (String) request.getParameter("leagueAssignId");
        Integer leagueAssignIDInteger = Integer.parseInt(leagueAssignId);

        String leagueAssignmentMessage = "";
        String ErrorType = "";
        boolean leagueHasLeagueAssignment = false;


        League_AssignmentDao league_assignmentDao = new League_AssignmentDao();
        league_assignmentDao.deleteLeagueAssign(leagueAssignIDInteger);
        session.setAttribute("leagueAssignIdDeleted", leagueAssignId);
        leagueAssignmentMessage = "League Assignment deleted. Id: " + leagueAssignId;

       // Forward to a JSP page named playerToLeagueAssignment.jsp.

        session.setAttribute("leagueAssignmentMessage", leagueAssignmentMessage);

        url = "/playerToLeagueAssignment.jsp";

        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}




