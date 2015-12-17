package com.TennisApp.java.persistance;

import com.TennisApp.java.LeagueAssignSearch;
import com.TennisApp.java.LeagueSearch;
import com.TennisApp.java.entity.League;
import com.TennisApp.java.entity.LeagueAssignmentResult;
import com.TennisApp.java.entity.League_Assignment;
import org.apache.log4j.Logger;
import org.hibernate.*;

import java.util.ArrayList;
import java.util.List;


/**
 * This is the database access object class for the League_Assignment entity. It will perform various CRUD operations,
 * to Create, Read one or many, Update, and Delete Leagues in the TennisApp.
 * Additionally, it will allow for  directional assignments.
 * AssignLeagueToPlayer, when coming from the Player Maintenance direction, have playerId, need a league.
 * or
 * AssignPlayerToLeague, when coming from the League Maintenance direction, have leagueId, need a player.
 *
 * Created by Dave on 12/10/2015.
 */
public class League_AssignmentDao {

    // pull LA for a given player id, pull LA (leagues list)
    // pull LA for a given league id, pull LA (players list)
    // The league assignment "slot" for any assignment will attempt to use the min(slot) available for that league
    // unless the min(slot) = slot_nums for that league, which indicates the league is filled with player slots.

    private final Logger logger = Logger.getLogger(this.getClass());

    /**
     * Create a method that will search for an League using a LeagueSearch object by either id or name or all.
     *
     *@param leagueAssignSearch  The search LeagueAssignSearch method returns a LeagueAssignSearch object. Can search by player_id or league_id.
     *@return LeagueAssignSearch
     */
    public LeagueAssignSearch searchForLeagueAssign(LeagueAssignSearch leagueAssignSearch){

        logger.info("method searchForLeagueAssign() in League_AssignmentDao, with type: " + leagueAssignSearch.getSearchType());

        if (leagueAssignSearch.getSearchType().toLowerCase().equals("assign player to league")) {
            getCurrentLeagueAssignmentsForPlayerId(leagueAssignSearch); // return void, but leagueAssignList.add(leagueAssign) LeagueAssignSearch object
//        }  else
//        if (leagueAssignSearch.getSearchType().toLowerCase().equals("assign league to player")) {
//            searchForLeagueID(leagueAssignSearch);  // return void, but leagueAssignList.add(leagueAssign) LeagueAssignSearch object
//        }  else
//        if (leagueAssignSearch.getSearchType().toLowerCase().equals("all")) {
//            searchForAllLeagues(leagueAssignSearch);  // return void, but leagueAssignList.add(leagueAssign) LeagueAssignSearch object
        }

        return leagueAssignSearch;
    }


    /**
     * This method uses the LeagueAssignSearch object to handle the League_Assign results returned by setting here.
     * @param leagueAssignSearch
     */
    public void getCurrentLeagueAssignmentsForPlayerId(LeagueAssignSearch leagueAssignSearch) {

        logger.info("method getCurrentLeagueAssignmentsForPlayerId() in League_AssignmentDao for: " + leagueAssignSearch.getSearchTerm());
        Session session = SessionFactoryProvider.getSessionFactory().openSession();

        List<LeagueAssignmentResult> league_assign_results = new ArrayList<LeagueAssignmentResult>();

//        List<League_Assignment> league_assignments = new ArrayList<League_Assignment>();
    //  List of Java String objects is desired, NOT the league_assignment entity bean.
//        List<Object[]> league_assign_results;
//        ArrayList<LeagueAssignmentResult> league_assign_results = null;

        int player_id =  Integer.parseInt(leagueAssignSearch.getSearchTerm());

        try {
//            String hqlString =
//                    "SELECT LA.leagueAssignId, LA.leagueId, LA.playerId, LA.playerSlotNum  " +
//                            " FROM League_Assignment LA " +
//                            " WHERE LA.playerId = :player_id";
//
////                            " WHERE LA.playerId = ?";
////            hqlQuery.setInteger(0,player_id);

            String hqlString =
//                    "SELECT new LeagueAssignmentResult(LA.leagueAssignId, L.leagueName, L.level, L.typeSinglesDoubles, L.numPlayerSlots)  " +
                    "SELECT  LA.leagueAssignId, L.leagueName, L.level, L.typeSinglesDoubles, L.numPlayerSlots  " +
                            " FROM League_Assignment LA, League L " +
                            " WHERE LA.leagueId = L.leagueId " +
                            " AND LA.playerId = :player_id " ;

            Query hqlQuery = session.createQuery(hqlString);
            hqlQuery.setParameter("player_id", player_id);

////            // if we wanted League_Assignment objects as a list:
//            league_assignments = (List<League_Assignment>) hqlQuery.list();  // cast attempt to put into an ArrayList of League_Assignments
//            if ( !league_assignments.isEmpty() ) {
//                for (League_Assignment league_assignment : league_assignments) {
//                    leagueAssignSearch.addFoundLeague_Assignment(league_assignment);
//                }
////                leagueAssignSearch.setLeague_AssignmentList(league_assignments);
//                leagueAssignSearch.setAssignmentsFound(true);
//            } else {
//                leagueAssignSearch.setAssignmentsFound(false);
//            }


            // start here, return a List<LeagueAssignmentResult> compatible with EL getters/setters

            league_assign_results = (ArrayList) hqlQuery.list();
            if ( !league_assign_results.isEmpty() ) {
                for ( LeagueAssignmentResult  league_assignment_result : league_assign_results) {
                    //TODO this might be added to a different object, etc...
                    leagueAssignSearch.addLeagueAssignResult(league_assignment_result);
//                    leagueAssignSearch.addLeagueAssignResult(leagueAssignSearch);
                    // when returning an array of Object[]
//                    League_Assignment leagueAssignment = (League_Assignment) league_assignment_result[0];
                }
//                leagueAssignSearch.setLeagueAssignResult(league_assign_results);
                leagueAssignSearch.setAssignmentsFound(true);
            } else {
                leagueAssignSearch.setAssignmentsFound(false);
            }


        } catch (HibernateException e) {
            logger.error("Exception: ", e);
        } finally {
            session.close();
        }

    }


//    /**
//     * This method directly returns a List of League_Assign results for a player int passed .
//     * @param playerIDInteger
//     */
//    public List<League_Assignment> getCurrentLeagueAssignmentsForPlayerId(int playerIDInteger) {
//
//        logger.info("method getCurrentLeagueAssignmentsForPlayerId() in League_AssignmentDao for: " + playerIDInteger);
//        Session session = SessionFactoryProvider.getSessionFactory().openSession();
//        List<League_Assignment> league_assignments = null;
//
//        try {
//            String hqlString =
//                    "SELECT LA.leagueAssignId, LA.leagueId, LA.playerId, LA.playerSlotNum  " +
//                            " FROM League_Assignment LA " +
//                            " WHERE LA.playerId = ?";
////                            " WHERE LA.playerId = :player_id";
//
//            Query hqlQuery = session.createQuery(hqlString);
////            hqlQuery.setParameter("player_id", playerIDInteger);
//            hqlQuery.setInteger(0,playerIDInteger);
//            league_assignments = hqlQuery.list();
//
//            return league_assignments;
//
//        } catch (HibernateException e) {
//            logger.error("Exception: ", e);
//        } finally {
//            session.close();
//        }
//        return league_assignments;
//    }



// alternates *************************************
    //
//    /**
//     * Create a method that will search for an Player_id in the League_Assignment.
//     *
//     *@param leagueAssignSearch  The new leagueAssignSearch object holds search type, term, results.
//     */
//    public void searchForPlayerID(LeagueAssignSearch leagueAssignSearch) {
//        Session session = SessionFactoryProvider.getSessionFactory().openSession();
//        League_Assignment league_assignment = null;
//        int playerId =  Integer.parseInt(leagueAssignSearch.getSearchTerm());
//        try {
//            league_assignment = (League_Assignment)session.get(League_Assignment.class, playerId);
//            if (league_assignment != null) {
//                leagueAssignSearch.addFoundLeague_Assignment(league_assignment);
//                leagueAssignSearch.setAssignmentsFound(true);
//            } else {
//                leagueAssignSearch.setAssignmentsFound(false);
//            }
//        } catch (HibernateException e) {
//            logger.error("Exception: ", e);
//        } finally {
//            session.close();
//        }
//    }


}
