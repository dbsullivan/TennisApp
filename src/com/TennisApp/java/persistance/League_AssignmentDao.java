package com.TennisApp.java.persistance;

import com.TennisApp.java.LeagueAssignSearch;
import com.TennisApp.java.LeagueSearch;
import com.TennisApp.java.entity.League;
import com.TennisApp.java.entity.LeagueAssignmentResult;
import com.TennisApp.java.entity.League_Assignment;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.mapping.Array;

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

//        Criteria creCriteria = session.createCriteria(LeagueAssignmentResult.class);
//        List<LeagueAssignmentResult> league_assign_results = new ArrayList<LeagueAssignmentResult>();
//        List<League_Assignment> league_assignments = new ArrayList<League_Assignment>();
//        List<Object[]> league_assign_results;


        int player_id =  Integer.parseInt(leagueAssignSearch.getSearchTerm());

        try {
            String hqlString =
//                    "SELECT new LeagueAssignmentResult(LA.leagueAssignId, L.leagueName, L.level, L.typeSinglesDoubles, L.numPlayerSlots)  " +
//                    "SELECT new list( LA.leagueAssignId, L.leagueName, L.level, L.typeSinglesDoubles, L.numPlayerSlots ) " +
                    "SELECT  LA.leagueAssignId, L.leagueName, L.level, L.typeSinglesDoubles, L.numPlayerSlots " +
                            " FROM League_Assignment LA, League L " +
                            " WHERE LA.leagueId = L.leagueId " +
                            " AND LA.playerId = :player_id " ;

            Query hqlQuery = session.createQuery(hqlString);
            hqlQuery.setParameter("player_id", player_id);

            //  return a List<LeagueAssignmentResult> compatible with EL getters/setters

            List<Object[]> league_assign_results = (List<Object[]>) hqlQuery.list();
//            List<LeagueAssignmentResult> league_assign_results = (ArrayList) hqlQuery.list();
//            List<LeagueAssignmentResult> league_assign_results = creCriteria.list();

            if ( !league_assign_results.isEmpty() ) {
//                for ( LeagueAssignmentResult  league_assignment_result : league_assign_results) {
                  for (Object[] league_assignment_result :  league_assign_results  ) {
//                    leagueAssignSearch.addLeagueAssignResult(league_assignment_result);
                    // when returning an array of Object[]
                      Integer leagueAssignId = (Integer) league_assignment_result[0];
                      String leagueName = (String) league_assignment_result[1];
                      String level = (String) league_assignment_result[2];
                      String typeSinglesDoubles = (String) league_assignment_result[3];
                      Integer numPlayerSlots = (Integer) league_assignment_result[4];

                      leagueAssignSearch.addLeagueAssignResult(league_assignment_result);
                }
                leagueAssignSearch.setLeagueAssignResult( (ArrayList) league_assign_results);
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

}
