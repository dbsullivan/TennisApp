package com.TennisApp.java;

import com.TennisApp.java.persistance.PlayerDao;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.*;

/**
 * Created by Dave on 11/9/2015.
 */
public class PlayerSearchServletTest {

    @Before
    public void setUp() throws Exception {
        PlayerSearchServlet pss = new PlayerSearchServlet();
        //todo http://stackoverflow.com/questions/12945907/how-to-mock-the-httpservletrequest
        // this is not considered a unit test, but an integration test, see Mockito, others.
//        pss.doGet(HttpServletRequest request,
//                HttpServletResponse response );
    }

    // negative test for parameters
    @Test
    public void playerSearchNullsTest() {
        PlayerDao playerDao = new PlayerDao();

        PlayerSearch playerSearch = new PlayerSearch();
        playerSearch.setSearchTerm("");
        playerSearch.setSearchType("");

    }

    // negative test for term parameter
    @Test
    public void playerSearchNullsTermTest() {
        PlayerDao playerDao = new PlayerDao();

        PlayerSearch playerSearch = new PlayerSearch();
        playerSearch.setSearchTerm("");
        playerSearch.setSearchType("id");

    }

    // negative test for type parameter
    @Test
    public void playerSearchNullsTypeTest() {
        PlayerDao playerDao = new PlayerDao();

        PlayerSearch playerSearch = new PlayerSearch();
        playerSearch.setSearchTerm("1");
        playerSearch.setSearchType("");

    }

    // positive test for ID
    @Test
    public void playerSearchValidIDTest() {
        PlayerDao playerDao = new PlayerDao();

        PlayerSearch playerSearch = new PlayerSearch();
        playerSearch.setSearchTerm("1");
        playerSearch.setSearchType("id");

    }

    // positive test for Name
    @Test
    public void playerSearchValidNameTest() {
        PlayerDao playerDao = new PlayerDao();

        PlayerSearch playerSearch = new PlayerSearch();
        playerSearch.setSearchTerm("Player");
        playerSearch.setSearchType("name");

    }
}