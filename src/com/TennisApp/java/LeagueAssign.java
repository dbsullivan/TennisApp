
package com.TennisApp.java;

import com.TennisApp.java.entity.League_Assignment;
import org.apache.log4j.Logger;
import java.util.ArrayList;

/**
 * LeagueAssign is a JavaBean holding Search type and data to be used by TennisApp when searching for League_Assignment records.
 * This is the javabean object that determines if PlayerToLeagueAssignmentServlet.java controller servlet should be returned a
 * League_Assignment result, results, or a message if not found.
 *
 * Created by Dave on 12/12/2015.
 */
public class LeagueAssign  extends java.lang.Object {

    //JavaBean: default constuctor (empty), instance vars, getters/setters
    private final Logger logger = Logger.getLogger(this.getClass());
    private String searchType;
    private String searchTerm;
    private boolean leaguesFound;

    private ArrayList<League_Assignment> leaguesList = new ArrayList<League_Assignment>();

    /**
     *  Constructor for the League_Assign object
     */
    public LeagueAssign() {

    }

    /**
     *  Gets the searchType attribute of the LeagueAssign object
     *
     *@return The searchType value
     */
    public String getSearchType() {
        return searchType;
    }

    /**
     *  Gets the searchTerm attribute of the LeagueAssign object
     *
     *@return The searchTerm value
     */
    public String getSearchTerm() {
        return searchTerm;
    }

    /**
     *  Gets the leaguesFound attribute of the LeagueAssign object
     *
     *@return The leaguesFound value
     */
    public boolean isFound() {
        return leaguesFound;
    }

    /**
     *  Gets the leaguesList attribute of the LeagueAssign object
     *
     *@return The leaguesList value
     */
    public ArrayList<League_Assignment> getLeague_AssignmentList() {
        return leaguesList;
    }

/******************************************************************************/

    /**
     *  Sets the searchType attribute of the LeagueAssign object
     *
     *@param searchType  The new searchType value
     */
    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    /**
     *  Sets the searchTerm attribute of the LeagueAssign object
     *
     *@param searchTerm  The new searchTerm value
     */
    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    /**
     *  Sets the leaguesFound attribute of the LeagueAssign object
     *
     *@param leaguesFound  The new leaguesFound value
     */
    public void setLeaguesFound(boolean leaguesFound) {
        this.leaguesFound = leaguesFound;
    }

    /**
     *  Sets the leaguesList attribute of the LeagueAssign object
     *
     *@param leaguesList  The new leaguesList value
     */
    public void setLeaguesList(ArrayList<League_Assignment> leaguesList) {
        this.leaguesList = leaguesList;
    }

    /**
     *  Adds to the leaguesList attribute of the LeagueAssign object
     *
     *@param league  The new league value to add to the list
     */
    public void addFoundLeague_Assignment(League_Assignment league) {

        logger.info("method League_Assignment.addFoundLeague(): " + league);
        leaguesList.add(league);
    }

}

