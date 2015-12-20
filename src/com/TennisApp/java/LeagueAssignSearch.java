
package com.TennisApp.java;

import com.TennisApp.java.entity.League;
import com.TennisApp.java.entity.LeagueAssignmentResult;
import com.TennisApp.java.entity.League_Assignment;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.List;

/**
 * LeagueAssignSearch is a JavaBean holding LeagueAssignSearch type and League_Assignment data to be used by TennisApp when maintaining League_Assignment records.
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
    private boolean leaguesAvailFound;

    //    private ArrayList leagueAssignListResults = new ArrayList();
    private List<League_Assignment> leagueAssignList = new ArrayList<League_Assignment>();
    private ArrayList<LeagueAssignmentResult> leagueAssignDisplayList = new ArrayList();
    private ArrayList<League> leagueDisplayList = new ArrayList();

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
    public List<League_Assignment> getLeague_AssignmentList() {
        return leagueAssignList;
    }

    /**
     *  Gets the leagueAssignDisplayList of the LeagueAssignSearch object
     *
     *@return The leagueAssignDisplayList value
     */
    public ArrayList<LeagueAssignmentResult> getLeagueAssignDisplay() { return leagueAssignDisplayList; }

    /**
     *  Gets the leaguesAvailFound attribute of the LeagueAssignSearch object
     *
     *@return The leaguesAvailFound value
     */
    public boolean isAvailFound() {
        return leaguesAvailFound;
    }

    /**
     *  Gets the LeagueAvailDisplayList of the LeagueAssignSearch object
     *
     *@return The leagueAssignDisplayList value
     */
    public ArrayList<League>  getLeagueAvailDisplayList() { return leagueDisplayList; }
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
     *  Sets the assignmentsFound attribute of the LeagueAssignSearch object
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
    public void setLeague_AssignmentList( ArrayList<League_Assignment> leagueAssignList) {
        this.leagueAssignList = leagueAssignList;
    }

    /**
     *  Adds to the leagueAssignList attribute of the LeagueAssignSearch object
     *
     *@param leagueAssign  The new leagueAssign value to add to the list
     */
    public void addFoundLeague_Assignment(League_Assignment leagueAssign) {
        leagueAssignList.add(leagueAssign);
    }

    /**
     *  Sets the leagueAssignDisplayList of the LeagueAssignSearch object
     *
     *@param leagueAssignDisplayList  The new leagueAssignDisplayList value
     */
    public void setLeagueAssignDisplayList( ArrayList<LeagueAssignmentResult> leagueAssignDisplayList) {
        this.leagueAssignDisplayList = leagueAssignDisplayList;
    }

    /**
     *  Adds to the leagueAssignDisplayList of the LeagueAssignSearch object
     *
     *@param league_assignment_result  The new LeagueAssignSearch value to add to the list
     */
    public void addLeagueAssignDisplay(LeagueAssignmentResult league_assignment_result ) {
        leagueAssignDisplayList.add(league_assignment_result);
    }

    public void setLeagueAvailDisplayList(ArrayList<League> leagueDisplayList) {
        this.leagueDisplayList = leagueDisplayList;
    }

    /**
     *  Sets the leaguesAvailFound attribute of the LeagueAssignSearch object
     *
     *@param leaguesAvailFound  The new leaguesAvailFound value
     */
    public void setLeaguesAvailFound(boolean leaguesAvailFound) {
        this.leaguesAvailFound = leaguesAvailFound;
    }
}

