package com.TennisApp.java.entity;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the LeagueAssignmentResult entity bean.
 *  LA.leagueAssignId, L.leagueName, L.level, L.typeSinglesDoubles, L.numPlayerSlots
 *
 * Created by Dave on 12/14/2015.
 */
//public class LeagueAssignmentResult implements List<LeagueAssignmentResult> {

public class LeagueAssignmentResult {

    private int leagueAssignId;
    private String leagueName;
    private String level;
    private String typeSinglesDoubles;
    private int numPlayerSlots;

    private final Logger logger = Logger.getLogger(this.getClass());

    public LeagueAssignmentResult() {
    }

    public LeagueAssignmentResult(int leagueAssignId, String leagueName, String level, String typeSinglesDoubles, int numPlayerSlots ) {
        this.leagueAssignId = leagueAssignId;
        this.leagueName = leagueName;
        this.numPlayerSlots = numPlayerSlots;
        this.typeSinglesDoubles = typeSinglesDoubles;
        this.level = level;
    }

    public int getLeagueAssignId() {return leagueAssignId; }

    public void setLeagueAssignId(int leagueAssignId) { this.leagueAssignId = leagueAssignId; }

    public String getLeagueName() {return leagueName; }

    public void setLeagueName(String leagueName) { this.leagueName = leagueName; }

    public String getTypeSinglesDoubles() {return typeSinglesDoubles; }

    public void setTypeSinglesDoubles(String typeSinglesDoubles) { this.typeSinglesDoubles = typeSinglesDoubles; }

    public String getLevel() {return level; }

    public void setLevel(String level) { this.level = level; }

    public int getNumPlayerSlots() {return numPlayerSlots; }

    public void setNumPlayerSlots(int numPlayerSlots) { this.numPlayerSlots = numPlayerSlots; }


    public String toString() {
        return  System.lineSeparator() +
                "Assign Id: " + leagueAssignId +
                " League: " + leagueName +
                " Slots: " + numPlayerSlots +
                " Type: " + typeSinglesDoubles ;
    }
}
