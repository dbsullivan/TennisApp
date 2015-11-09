package com.TennisApp.java;

import com.TennisApp.java.entity.Player;
import com.TennisApp.java.persistance.PlayerDao;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;

/**
 * Created by Dave on 11/9/2015.
 */
public class TestDaoMethods {
    public static void main(String[] args) {

        PlayerDao playerDao = new PlayerDao();

        System.out.println("ALL Before: " + playerDao.getAllPlayers());
//        int playerId, String firstName, String lastName, String email, String gender, String NTRPlevel, String phone
        Player player = new Player(0,"Bye","Bye","dbsullivan@madisoncollege.edu",null,null,null);
        Integer newId = playerDao.addPlayer(player);
        System.out.println("ALL After addPlayer: " + playerDao.getAllPlayers());

// player just added, change names
        Player newPlayer = new Player(newId,"PlayerBye","PlayerBye","dbsullivan@madisoncollege.edu",null,null,null);
        playerDao.addOrUpdatePlayer(newPlayer);
        System.out.println("Get newPlayer newId: " + playerDao.getPlayer(newId));

        PlayerSearch playerSearchID = new PlayerSearch();
        playerSearchID.setSearchTerm(newId.toString());  // test if string will match integer in query.
        playerSearchID.setSearchType("Id");  // in code convert/test using lowercase to remove case dependency
//        ArrayList<Player> playersListId = new ArrayList<Player>();
        PlayerSearch playerResultsId = playerDao.searchForPlayer(playerSearchID);
        System.out.println("searchForPlayer ID: " + playerResultsId.getPlayersList()); // takes

        PlayerSearch playerSearchName = new PlayerSearch();
        playerSearchName.setSearchTerm("Bye");
        playerSearchName.setSearchType("Name"); // in code convert/test using lowercase to remove case dependency
        ArrayList<Player> playersListName = new ArrayList<Player>();
        PlayerSearch playerResultsName = playerDao.searchForPlayer(playerSearchName);
        System.out.println("searchForPlayer Name: " + playerResultsName.getPlayersList()); // takes

        Player playerD = new Player(0,"TestDelete","PlayerTestDelete","dbsullivan@madisoncollege.edu",null,null,null);
        Integer newIdD = playerDao.addPlayer(playerD);
        System.out.println("ALL Before deletePlayer: " + playerDao.getAllPlayers());
        playerDao.deletePlayer(newIdD);
        System.out.println("ALL After deletePlayer: " + playerDao.getAllPlayers());

    }
}
