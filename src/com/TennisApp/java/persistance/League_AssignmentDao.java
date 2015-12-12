package com.TennisApp.java.persistance;

import com.TennisApp.java.LeagueSearch;
import com.TennisApp.java.entity.League;
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

    // simply read one or all for now.  Want to list all L_A's next to Player Maint, League Maint .jsps
    // Also, this read will need to get League name join, and/or Player name join
    // pull LA for a given player id, pull LA (leagues list)
    // pull LA for a given league id, pull LA (players list)

    // when time for assignment, just read a list of Leagues where no L_A exists for the current player_id, link/hrefs to chose one.
    // or read a list of Players where no L_A exists for the current league_id, link/hrefs to chose one.
    // Then the logic must validate to find the number of slots from League, and only assign the next
    // available slot indexing through an ordered Set, if L_A has some or none.

    private final Logger logger = Logger.getLogger(this.getClass());

    /**
     *  method to search for all league_assignments
     *
     *@param playerIDInteger  The new leagueSearch object holds search type, term, results.
     *
     */
    public List getCurrentLeagueAssignmentsForPlayerId(int playerIDInteger) {

        List<League_Assignment> league_assignments = new ArrayList<~>();
        Session session = SessionFactoryProvider.getSessionFactory().openSession();

        // use HQL to join league_assignment to league for league_name, and playerIdInteger parameter

        Criteria criteria = session.createCriteria(League_Assignment.class);
        Transaction tx = null;
        try {
            league_assignments = criteria.list();
            if ( !league_assignments.isEmpty() ) {
                for (League_Assignment league_assignment : league_assignments) {
                    leagueAssign.addFoundLeague(league);  // what if none found, need a leagueAssign object to test?
                }
                leagueSearch.setLeaguesFound(true);
            }
//            String queryString =
//                "";
//            List league_assignments = session.createQuery( queryString );


        } catch (HibernateException e) {
            logger.error("Exception: ", e);
        } finally {
            session.close();
        }
        return league_assignments;
    }

}
