
package com.TennisApp.java;

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
 *  This class provides controller following submit of League search- form action in leagueSearch.jsp)
 *  Here, LeagueSearchServlet.java (league-search-results)- decides if League found using Search object boolean search.isFound()
 *  if found, display League.jsp, else return with message to leagueSearch.jsp
 *
 *@author    Dave Sullivan
 */

@WebServlet(
        name = "LeagueSearchResults",
        urlPatterns = { "/league-search-results" }
)
public class LeagueSearchServlet extends HttpServlet {

    /**
     *  Handles HTTP GET requests.
     *
     *@param  request               Description of the Parameter
     *@param  response              Description of the Parameter
     *@exception ServletException  if there is a Servlet failure
     *@exception IOException       if there is an IO failure
     */
    private final Logger logger = Logger.getLogger(LeagueSearchServlet.class);

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        logger.info("In LeagueSearchServlet...get form parms Term/Type set into search object, forward or return");
        String url = null;

        String searchTerm = request.getParameter("searchTerm");
        String searchType = request.getParameter("searchType");

        LeagueDao leagueDao = new LeagueDao();

        LeagueSearch leagueSearch = new LeagueSearch();
        leagueSearch.setSearchTerm(searchTerm);
        leagueSearch.setSearchType(searchType);

        // associate the Message with the request, and clear it before forwarding to JSP page
        String SearchMessage = "";
        session.setAttribute("leagueSearchMessage", SearchMessage);

        //set message on page use EL request or session.getAttribute(leagueSearchMessage); fill if errors.
        if ((leagueSearch.getSearchTerm() == null || leagueSearch.getSearchTerm().equals("") )
                &
                (leagueSearch.getSearchType() == null || leagueSearch.getSearchType().equals("") )
                ) {
            // Forward back to a JSP page named leagueSearch.jsp
            logger.info("return to leagueSearch JSP, incomplete parms..." + leagueSearch.getSearchType() + " " + leagueSearch.getSearchTerm());
            SearchMessage = "Please enter missing Search Term and Search Type, or chose All Leagues.";
            session.setAttribute("leagueSearchMessage", SearchMessage);
            url = "/leagueSearch.jsp";
        } else if (leagueSearch.getSearchType() == null || leagueSearch.getSearchType().equals("") ) {
            // Forward back to a JSP page named leagueSearch.jsp
            logger.info("return to leagueSearch JSP, incomplete parms..." + leagueSearch.getSearchType() + " " + leagueSearch.getSearchTerm());
            SearchMessage = "Please enter missing Search Type.";
            session.setAttribute("leagueSearchMessage", SearchMessage);
            url = "/leagueSearch.jsp";
        } else if (leagueSearch.getSearchType().equals("all") ) {
            // Forward to a JSP page named leagueSearchResults.jsp
            logger.info("forward to leagueSearchResults.jsp, with LeagueDao.searchForLeague(leagueSearch)");
            leagueSearch = leagueDao.searchForLeague(leagueSearch);
            session.setAttribute("leagueStatusSearch", leagueSearch);

            if ( !leagueSearch.isFound() ) {
                SearchMessage = "No League found.";
                session.setAttribute("leagueSearchMessage", SearchMessage);
            }
            url = "/leagueSearchResults.jsp";
        } else if (leagueSearch.getSearchTerm() == null || leagueSearch.getSearchTerm().equals("") ) {
            // Forward back to a JSP page named leagueSearch.jsp
            logger.info("return to leagueSearch JSP, incomplete parms..." + leagueSearch.getSearchType() + " " + leagueSearch.getSearchTerm());
            SearchMessage = "Please enter missing Search Term.";
            session.setAttribute("leagueSearchMessage", SearchMessage);
            url = "/leagueSearch.jsp";
        } else {
            // Forward to a JSP page named leagueSearchResults.jsp
            logger.info("forward to leagueSearchResults.jsp, with LeagueDao.searchForLeague(leagueSearch)") ;
            leagueSearch = leagueDao.searchForLeague(leagueSearch);
            session.setAttribute("leagueStatusSearch", leagueSearch);

            if ( !leagueSearch.isFound() ) {
                SearchMessage = "No League found.";
                session.setAttribute("leagueSearchMessage", SearchMessage);
            }
            url = "/leagueSearchResults.jsp";
        }

        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

}