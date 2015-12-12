package com.TennisApp.java.persistance;

import com.TennisApp.java.LeagueSearch;
import com.TennisApp.java.entity.League;
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
 * This is the database access object class for the League entity. It will perform various CRUD operations,
 * to Create, Read one or many, Update, and Delete Leagues in the TennisApp.
 * Additionally, it will provide methods for the LeagueSearchServlet.java to use a LeagueSearch object
 * lookup to the database.
 * // http://www.developer.com/db/using-criteria-in-hibernate-for-advanced-queries.html
 *
 * Created by Dave on 11/11/2015.
 */

public class LeagueDao {

    private final Logger logger = Logger.getLogger(LeagueDao.class);

    /**
     * Create a method that will search for an League using a LeagueSearch object by either id or name or all.
     *
     *@param leagueSearch  The search League method returns a LeagueSearch object. Can search by ID or name.
     *@return The leagueSearch value
     */
    public LeagueSearch searchForLeague(LeagueSearch leagueSearch){

        logger.info("method searchForLeague() in LeagueDao, with type: " + leagueSearch.getSearchType());
        
        if (leagueSearch.getSearchType().toLowerCase().equals("id")) {
            searchForLeagueID(leagueSearch); // return void, but addFoundLeague to LeagueSearch object
        }  else
        if (leagueSearch.getSearchType().toLowerCase().equals("name")) {
            searchForLeagueName(leagueSearch);  // return void, but addFoundLeague to LeagueSearch object
        }  else
        if (leagueSearch.getSearchType().toLowerCase().equals("all")) {
            searchForAllLeagues(leagueSearch);  // return void, but addFoundLeague to LeagueSearch object
        }


        return leagueSearch;
    }

    /**
     * Create a method that will search for an League in the database by league id.
     *
     *@param leagueSearch  The new leagueSearch object holds search type, term, results.
     */
    public void searchForLeagueID(LeagueSearch leagueSearch) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        League league = null;
        int leagueId =  Integer.parseInt(leagueSearch.getSearchTerm());
        try {
            league = (League)session.get(League.class, leagueId);
            if (league != null) {
                leagueSearch.addFoundLeague(league);
                leagueSearch.setLeaguesFound(true);
            } else {
                leagueSearch.setLeaguesFound(false);
            }
        } catch (HibernateException e) {
            logger.error("Exception: ", e);
        } finally {
            session.close();
        }
    }

    /**
     *  method to search by League name. Similar to search by Employee ID, differs by SQL.
     *
     *@param leagueSearch  The new leagueSearch object holds search type, term, results.
     */
    public void searchForLeagueName(LeagueSearch leagueSearch) {
        String nameToFind = leagueSearch.getSearchTerm();
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(League.class);
        criteria.add(Restrictions.like("leagueName", nameToFind, MatchMode.ANYWHERE));
        List<League> leagues = null;
        try {
            leagues = criteria.list();
            if ( !leagues.isEmpty() ) {
                for (League league : leagues) {
                    leagueSearch.addFoundLeague(league);
                }
                leagueSearch.setLeaguesFound(true);
            }
        } catch (HibernateException e) {
            logger.error("Exception: ", e);
        } finally {
            session.close();
        }
    }

    /**
     *  method to search for all leagues return the leagues to the leagueSearch object
     *
     *@param leagueSearch  The new leagueSearch object holds search type, term, results.
     */
    public void searchForAllLeagues(LeagueSearch leagueSearch) {
        List<League> leagues = new ArrayList<League>();
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(League.class);
        Transaction tx = null;
        try {
            leagues = criteria.list();
            if ( !leagues.isEmpty() ) {
                for (League league : leagues) {
                    leagueSearch.addFoundLeague(league);
                }
                leagueSearch.setLeaguesFound(true);
            }
        } catch (HibernateException e) {
            logger.error("Exception: ", e);
        } finally {
            session.close();
        }
    }



    /** Method to CREATE a league in the database
     *
     * @param  league  The League to be added.
     **/
    public Integer addLeague(League league) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        Integer leagueId = null;
        try {
            tx = session.beginTransaction();
            leagueId = (Integer) session.save(league);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            logger.error("Exception: ", e);
        } finally {
            session.close();
        }
        return leagueId;
    }

    /** Method to CREATE or UPDATE a league in the database
     * If an id of 0 is passed in, a new League entry will be created
     * If an id of an existing record is passed in, the League is updated
     *
     * @param  league  The League to be added.
     **/
    public void addOrUpdateLeague(League league) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        Integer leagueId = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(league);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            logger.error("Exception: ", e);
        } finally {
            session.close();
        }
    }


    /** Method to READ a league in the database
     *
     * @param  leagueId  The League to be added.
     **/
    public League getLeague(int leagueId) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        League league = null;
        try {
            league = (League)session.get(League.class, leagueId);
            if (league != null) {
                return league;
            }
        } catch (HibernateException e) {
            logger.error("Exception: ", e);
        } finally {
            session.close();
        }
        return league;
    }


    /** Method to list all the leagues in the database
     *
     * may want to pass in LeagueSearch leagueSearch, where ALL is selected, modify LeagueSearchServlet.
     **/
    public List getAllLeagues() {
        List<League> leagues = new ArrayList<League>();
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(League.class);
        Transaction tx = null;
        try {
            leagues = criteria.list();
        } catch (HibernateException e) {
//            e.printStackTrace();
            logger.error("Exception: ", e);
        } finally {
            session.close();
        }
        return leagues;
    }


    /** Method to DELETE an league in the database
     *
     * @param  leagueId  The League to be deleted.
     **/
    public void deleteLeague(int leagueId){
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            League league =  (League)session.get(League.class, leagueId);
            if (league != null) {
                session.delete(league);
            }
            tx.commit();
        }catch (HibernateException e) {
            logger.error("Exception: ", e);
        }finally {
            session.close();
        }
    }

// TODO revive this if we need List leagues rather then void, league returned.
//
//    public List searchForLeagueID(LeagueSearch leagueSearch) {
//        List<League> leagues = new ArrayList<League>();
//        Session session = SessionFactoryProvider.getSessionFactory().openSession();
//        League league = null;
//        int leagueId =  Integer.parseInt(leagueSearch.getSearchTerm());
//        try {
//            league = (League)session.get(League.class, leagueId);
//            if (league != null) {
//                leagues.add(league);
//                leagueSearch.addFoundLeague(league);
//                leagueSearch.setLeaguesFound(true);
//                return leagues;
//            }
//        } catch (HibernateException e) {
//            e.printStackTrace();
//    logger.error("Exception: ", e);
//        } finally {
//            session.close();
//        }
//        leagueSearch.addFoundLeague(league);
//        leagueSearch.setLeaguesFound(true);
//        return leagues;
//    }

}