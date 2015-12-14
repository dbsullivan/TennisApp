
package com.TennisApp.java;

import com.TennisApp.java.entity.League_Assignment;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.List;

/**
 * LeagueAssign is a JavaBean holding LeagueAssignSearch type and League_Assignment data to be used by TennisApp when maintaining League_Assignment records.
 * This is the javabean object that determines if PlayerToLeagueAssignmentServlet.java controller servlet should be returned a
 * League_Assignment result, results, or a message if not found.
 *
 * Created by Dave on 12/12/2015.
 */
public class LeagueAssignSearch extends java.lang.Object {

    //JavaBean: default constuctor (empty), instance vars, getters/setters
    private final Logger logger = Logger.getLogger(this.getClass());
    private String searchType;
    private String searchTerm;
    private boolean assignmentsFound;

//    private ArrayList<League_Assignment> leagueAssignList = new ArrayList<League_Assignment>();
    private List<League_Assignment> leagueAssignList = new ArrayList<League_Assignment>();
    private List leagueAssignListResults = new ArrayList<>();

    /**
     *  Constructor for the LeagueAssignSearch object
     */
    public LeagueAssignSearch() {

    }

    /**
     *  Gets the searchType attribute of the LeagueAssignSearch object
     *
     *@return The searchType value
     */
    public String getSearchType() { return searchType; }

    /**
     *  Gets the searchTerm attribute of the LeagueAssignSearch object
     *
     *@return The searchTerm value
     */
    public String getSearchTerm() {
        return searchTerm;
    }

    /**
     *  Gets the leaguesFound attribute of the LeagueAssignSearch object
     *
     *@return The leaguesFound value
     */
    public boolean isFound() {
        return assignmentsFound;
    }

    /**
     *  Gets the leagueAssignList attribute of the LeagueAssignSearch object
     *
     *@return The leagueAssignList value
     */
//    public ArrayList<League_Assignment> getLeague_AssignmentList() {
    public List<League_Assignment> getLeague_AssignmentList() {
        return leagueAssignList;
    }

    /**
     *  Gets the leagueAssignListResults of the LeagueAssignSearch object
     *
     *@return The leagueAssignListResults value
     */
    public List getLeagueAssignResult() { return leagueAssignListResults; }

/******************************************************************************/

    /**
     *  Sets the searchType attribute of the LeagueAssignSearch object
     *
     *@param searchType  The new searchType value
     */
    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    /**
     *  Sets the searchTerm attribute of the LeagueAssignSearch object
     *
     *@param searchTerm  The new searchTerm value
     */
    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    /**
     *  Sets the assignmentsFound attribute of the LeagueAssign object
     *
     *@param assignmentsFound  The new assignmentsFound value
     */
    public void setAssignmentsFound(boolean assignmentsFound) {
        this.assignmentsFound = assignmentsFound;
    }

    /**
     *  Sets the leagueAssignList attribute of the LeagueAssignSearch object
     *
     *@param leagueAssignList  The new leagueAssignList value
     */
//    public void setLeague_AssignmentList(ArrayList<League_Assignment> leagueAssignList) {
    public void setLeague_AssignmentList( List<League_Assignment> leagueAssignList) {
        this.leagueAssignList = leagueAssignList;
    }

    /**
     *  Adds to the leagueAssignList attribute of the LeagueAssign object
     *
     *@param leagueAssign  The new leagueAssign value to add to the list
     */
    public void addFoundLeague_Assignment(League_Assignment leagueAssign) {

        logger.info("method LeagueAssign.addFoundLeague_Assignment(): " + leagueAssign);
        leagueAssignList.add(leagueAssign);
    }

    /**
     *  Sets the leagueAssignList attribute of the LeagueAssignSearch object
     *
     *@param leagueAssignListResults  The new leagueAssignList value
     */
    public void setLeagueAssignResult( List leagueAssignListResults) {
        this.leagueAssignListResults = leagueAssignListResults;
    }

    /**
     *  Adds to the leagueAssignListResults of the LeagueAssign object
     *
     *@param league_assignment_result  The new leagueAssign value to add to the list
     */
    public void addLeagueAssignResult(Object league_assignment_result) {

        logger.info("method LeagueAssignSearch.addLeagueAssignResult(): " + league_assignment_result);
        leagueAssignListResults.add(league_assignment_result);
    }

}

