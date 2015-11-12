
package com.TennisApp.java;

import com.TennisApp.java.entity.League;
import org.apache.log4j.Logger;
import java.util.ArrayList;

/**
 * LeagueSearch is a JavaBean holding Search type and data to be used by TennisApp when searching for Leagues.
 * This is the javabean object that determines if LeagueSearchServlet.java controller servlet should be returned a
 * League result, results, or a message if not found.  It contains the various flavors of a league search selectable
 * by LeagueId, or by lastName
 *
 * Created by Dave on 11/8/2015.
 */
public class LeagueSearch  extends java.lang.Object {

    //JavaBean: default constuctor (empty), instance vars, getters/setters
    private final Logger logger = Logger.getLogger(LeagueSearch.class);
    private String searchType;
    private String searchTerm;
    private boolean leaguesFound;

    private ArrayList<League> leaguesList = new ArrayList<League>();

    /**
     *  Constructor for the LeagueSearch object
     */
    public LeagueSearch() {

    }

    /**
     *  Gets the searchType attribute of the LeagueSearch object
     *
     *@return The searchType value
     */
    public String getSearchType() {
        return searchType;
    }

    /**
     *  Gets the searchTerm attribute of the LeagueSearch object
     *
     *@return The searchTerm value
     */
    public String getSearchTerm() {
        return searchTerm;
    }

    /**
     *  Gets the leaguesFound attribute of the LeagueSearch object
     *
     *@return The leaguesFound value
     */
    public boolean isFound() {
        return leaguesFound;
    }

    /**
     *  Gets the leaguesList attribute of the LeagueSearch object
     *
     *@return The leaguesList value
     */
    public ArrayList<League> getLeaguesList() {
        return leaguesList;
    }

/******************************************************************************/

    /**
     *  Sets the searchType attribute of the LeagueSearch object
     *
     *@param searchType  The new searchType value
     */
    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    /**
     *  Sets the searchTerm attribute of the LeagueSearch object
     *
     *@param searchTerm  The new searchTerm value
     */
    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    /**
     *  Sets the leaguesFound attribute of the LeagueSearch object
     *
     *@param leaguesFound  The new leaguesFound value
     */
    public void setLeaguesFound(boolean leaguesFound) {
        this.leaguesFound = leaguesFound;
    }

    /**
     *  Sets the leaguesList attribute of the LeagueSearch object
     *
     *@param leaguesList  The new leaguesList value
     */
    public void setLeaguesList(ArrayList<League> leaguesList) {
        this.leaguesList = leaguesList;
    }

    /**
     *  Adds to the leaguesList attribute of the LeagueSearch object
     *
     *@param league  The new league value to add to the list
     */
    public void addFoundLeague(League league) {

        logger.info("method LeagueSearch.addFoundLeague(): " + league);
        leaguesList.add(league);
    }

}
