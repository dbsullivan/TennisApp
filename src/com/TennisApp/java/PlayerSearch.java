package com.TennisApp.java;

import com.TennisApp.java.entity.Player;
import org.apache.log4j.Logger;
import java.util.ArrayList;

/**
 * PlayerSearch is a JavaBean holding Search type and data to be used by TennisApp when searching for Players.
 * This is the javabean object that determines if PlayerSearchServlet.java controller servlet should be returned a
 * Player result, results, or a message if not found.  It contains the various flavors of a player search selectable
 * by PlayerId, or by lastName
 *
 * Created by Dave on 11/8/2015.
 */
public class PlayerSearch  extends java.lang.Object {

    //JavaBean: default constuctor (empty), instance vars, getters/setters
    private final Logger logger = Logger.getLogger(PlayerSearch.class);
    private String searchType;
    private String searchTerm;
    private boolean playersFound;

    private ArrayList<Player> playersList = new ArrayList<Player>();

    /**
     *  Constructor for the PlayerSearch object
     */
    public PlayerSearch() {

    }

    /**
     *  Gets the searchType attribute of the PlayerSearch object
     *
     *@return The searchType value
     */
    public String getSearchType() {
        return searchType;
    }

    /**
     *  Gets the searchTerm attribute of the PlayerSearch object
     *
     *@return The searchTerm value
     */
    public String getSearchTerm() {
        return searchTerm;
    }

    /**
     *  Gets the playersFound attribute of the PlayerSearch object
     *
     *@return The playersFound value
     */
    public boolean isFound() {
        return playersFound;
    }

    /**
     *  Gets the playersList attribute of the PlayerSearch object
     *
     *@return The playersList value
     */
    public ArrayList<Player> getPlayersList() {
        return playersList;
    }

/******************************************************************************/

    /**
     *  Sets the searchType attribute of the PlayerSearch object
     *
     *@param searchType  The new searchType value
     */
    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    /**
     *  Sets the searchTerm attribute of the PlayerSearch object
     *
     *@param searchTerm  The new searchTerm value
     */
    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    /**
     *  Sets the playersFound attribute of the PlayerSearch object
     *
     *@param playersFound  The new playersFound value
     */
    public void setPlayersFound(boolean playersFound) {
        this.playersFound = playersFound;
    }

    /**
     *  Sets the playersList attribute of the PlayerSearch object
     *
     *@param playersList  The new playersList value
     */
    public void setPlayersList(ArrayList<Player> playersList) {
        this.playersList = playersList;
    }

    /**
     *  Adds to the playersList attribute of the PlayerSearch object
     *
     *@param player  The new player value to add to the list
     */
    public void addFoundPlayer(Player player) {

        logger.info("method PlayerSearch.addFoundPlayer(): " + player);
        playersList.add(player);
    }

}
