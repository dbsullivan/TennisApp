package com.TennisApp.java;

import com.TennisApp.java.persistance.PlayerDao;
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
 *  This class provides controller following submit of Player search- form action in playerSearch.jsp)
 *  Here, PlayerSearchServlet.java (player-search-results)- decides if Player found using Search object boolean search.isFound()
 *  if found, display Player.jsp, else return with message to playerSearch.jsp
 *
 *@author    Dave Sullivan
 */

@WebServlet(
        name = "PlayerSearchResults",
        urlPatterns = { "/player-search-results" }
)
public class PlayerSearchServlet extends HttpServlet {

    /**
     * Handles HTTP GET requests.
     *
     * @param request               Description of the Parameter
     * @param response              Description of the Parameter
     * @exception ServletException  if there is a Servlet failure
     * @exception IOException       if there is an IO failure
     */
    private final Logger logger = Logger.getLogger(PlayerSearchServlet.class);

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        logger.info("In PlayerSearchServlet...get form parms Term/Type set into search object, forward or return");
        String url = null;

        String searchTerm = request.getParameter("searchTerm");
        String searchType = request.getParameter("searchType");

        PlayerDao playerDao = new PlayerDao();

        PlayerSearch playerSearch = new PlayerSearch();
        playerSearch.setSearchTerm(searchTerm);
        playerSearch.setSearchType(searchType);

        // associate the Message with the request, and clear it before forwarding to JSP page
        String SearchMessage = "";
        session.setAttribute("playerSearchMessage", SearchMessage);

        //set message on page use EL request or session.getAttribute(playerSearchMessage); fill if errors.
            // edits needed for Search Term and Type, if ALL was not selected:
            if ((playerSearch.getSearchTerm() == null || playerSearch.getSearchTerm().equals(""))
                    &
                (playerSearch.getSearchType() == null || playerSearch.getSearchType().equals(""))
                    ) {
                // Forward back to a JSP page named playerSearch.jsp
                logger.info("return to playerSearch JSP, incomplete parms..." + playerSearch.getSearchType() + " " + playerSearch.getSearchTerm());
                SearchMessage = "Please enter missing Search Term and Search Type, or chose All Players.";
                session.setAttribute("playerSearchMessage", SearchMessage);
                url = "/playerSearch.jsp";
            } else if (playerSearch.getSearchType() == null || playerSearch.getSearchType().equals("")) {
                // Forward back to a JSP page named playerSearch.jsp
                logger.info("return to playerSearch JSP, incomplete parms..." + playerSearch.getSearchType() + " " + playerSearch.getSearchTerm());
                SearchMessage = "Please enter missing Search Type.";
                session.setAttribute("playerSearchMessage", SearchMessage);
                url = "/playerSearch.jsp";
            } else if (playerSearch.getSearchType().equals("all") ) {
                // Forward to a JSP page named playerSearchResults.jsp
                logger.info("forward to playerSearchResults.jsp, with PlayerDao.searchForPlayer()");
                playerSearch = playerDao.searchForPlayer(playerSearch);
                session.setAttribute("playerStatusSearch", playerSearch);

                if (!playerSearch.isFound()) {
                    SearchMessage = "No Player found.";
                    session.setAttribute("playerSearchMessage", SearchMessage);
                }
                url = "/playerSearchResults.jsp";
            } else if (playerSearch.getSearchTerm() == null || playerSearch.getSearchTerm().equals("")) {
                // Forward back to a JSP page named playerSearch.jsp
                logger.info("return to playerSearch JSP, incomplete parms..." + playerSearch.getSearchType() + " " + playerSearch.getSearchTerm());
                SearchMessage = "Please enter missing Search Term.";
                session.setAttribute("playerSearchMessage", SearchMessage);
                url = "/playerSearch.jsp";

            } else {
                // Forward to a JSP page named playerSearchResults.jsp
                logger.info("forward to playerSearchResults.jsp, with PlayerDao.searchForPlayer(playerSearch)");
                playerSearch = playerDao.searchForPlayer(playerSearch);
                session.setAttribute("playerStatusSearch", playerSearch);
                // here, leagueAssignDao using player ID to set another session.setAttribute("playerLeagueAssigns", playerLeagues) <-- like playerSearch, Id, found, List if true

                if (!playerSearch.isFound()) {
                    SearchMessage = "No Player found.";
                    session.setAttribute("playerSearchMessage", SearchMessage);
                }
                url = "/playerSearchResults.jsp";
            }
//        }

        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

}