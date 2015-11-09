package com.TennisApp.java.persistance;

import com.TennisApp.java.PlayerSearch;
import com.TennisApp.java.entity.Player;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the database access object class for the Player entity. It will perform various CRUD operations,
 * to Create, Read one or many, Update, and Delete Players in the TennisApp.
 * Additionally, it will provide methods for the PlayerSearchServlet.java to use a PlayerSearch object
 * lookup to the database.
 * // http://www.developer.com/db/using-criteria-in-hibernate-for-advanced-queries.html
 *
 * Created by Dave on 11/8/2015.
 */

public class PlayerDao {

    private final Logger logger = Logger.getLogger(PlayerDao.class);

    /**
     * Create a method that will search for an Player using a PlayerSearch object by either id or Lastname.
     *
     *@param playerSearch  The search Player method returns a PlayerSearch object. Can search by ID or Lastname.
     *@return The playerSearch value
     */
    public PlayerSearch searchForPlayer(PlayerSearch playerSearch){

        logger.info("method searchForPlayer() in PlayerDao, with type: " + playerSearch.getSearchType());

        if (playerSearch.getSearchType().toLowerCase().equals("id")) {
            searchForPlayerID(playerSearch); // should return player
        }  else
        if (playerSearch.getSearchType().toLowerCase().equals("name")) {
            searchForPlayerName(playerSearch);  // returns an array players from like match
        }

        return playerSearch;
    }

    /**
     * Create a method that will search for an Player in the database by player id.
     *
     *@param playerSearch  The new playerSearch object holds search type, term, results.
     */


    public void searchForPlayerID(PlayerSearch playerSearch) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Player player = null;
        int playerId =  Integer.parseInt(playerSearch.getSearchTerm());
        try {
            player = (Player)session.get(Player.class, playerId);
            if (player != null) {
                playerSearch.addFoundPlayer(player);
                playerSearch.setPlayersFound(true);
            } else {
                playerSearch.setPlayersFound(false);
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     *  method to search by Player LastName. Similar to search by Employee ID, differs by SQL.
     *
     *@param playerSearch  The new playerSearch object holds search type, term, results.
     */

//    private void searchForPlayerName(PlayerSearch playerSearch) {
//    public List searchForPlayerName(PlayerSearch playerSearch) {
    public void searchForPlayerName(PlayerSearch playerSearch) {
        String lastNameToFind = playerSearch.getSearchTerm();
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Player.class);
        criteria.add(Restrictions.like("lastName", lastNameToFind, MatchMode.ANYWHERE));
        List<Player> players = null;
        try {
            players = criteria.list();
            if ( !players.isEmpty() ) {
                for (Player player : players) {
                    playerSearch.addFoundPlayer(player);
                }
                playerSearch.setPlayersFound(true);
            }
//            return players;
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
//        return players;
    }


    /** Method to CREATE a player in the database
    *
    * @param  player  The Player to be added.
    **/
    public Integer addPlayer(Player player) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        Integer playerId = null;
        try {
            tx = session.beginTransaction();
            playerId = (Integer) session.save(player);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return playerId;
    }

    /** Method to CREATE or UPDATE a player in the database
     * If an id of 0 is passed in, a new Player entry will be created
     * If an id of an existing record is passed in, the Player is updated
     *
     * @param  player  The Player to be added.
     **/
    public void addOrUpdatePlayer(Player player) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        Integer playerId = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(player);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    /** Method to READ a player in the database
     *
     * @param  playerId  The Player to be added.
     **/
    public Player getPlayer(int playerId) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Player player = null;
        try {
            player = (Player)session.get(Player.class, playerId);
            if (player != null) {
                return player;
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return player;
    }


    /** Method to list all the players in the database
     *
     * may want to pass in PlayerSearch playerSearch, where ALL is selected, modify PlayerSearchServlet.
     **/
    public List getAllPlayers() {
        List<Player> players = new ArrayList<Player>();
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Player.class);
        Transaction tx = null;
        try {
            players = criteria.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return players;
    }


    /** Method to DELETE an player in the database
     *
     * @param  playerId  The Player to be deleted.
     **/
    public void deletePlayer(int playerId){
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Player player =  (Player)session.get(Player.class, playerId);
            if (player != null) {
                session.delete(player);
            }
            tx.commit();
        }catch (HibernateException e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

// TODO revive this if we need List players rather then void, player returned.
//
//    public List searchForPlayerID(PlayerSearch playerSearch) {
//        List<Player> players = new ArrayList<Player>();
//        Session session = SessionFactoryProvider.getSessionFactory().openSession();
//        Player player = null;
//        int playerId =  Integer.parseInt(playerSearch.getSearchTerm());
//        try {
//            player = (Player)session.get(Player.class, playerId);
//            if (player != null) {
//                players.add(player);
//                playerSearch.addFoundPlayer(player);
//                playerSearch.setPlayersFound(true);
//                return players;
//            }
//        } catch (HibernateException e) {
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//        playerSearch.addFoundPlayer(player);
//        playerSearch.setPlayersFound(true);
//        return players;
//    }

}