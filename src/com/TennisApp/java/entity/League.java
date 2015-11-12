package com.TennisApp.java.entity;

import java.util.Date;

/**
 * This is the League entity bean. Getters/Setters don't require javadoc.
 * Created by Dave on 11/11/2015.
 */
public class League {
    private int leagueId;
    private String leagueName;
    private int numPlayerSlots;
    private int numCourtsNeeded;
    private int numEvents;
    private String typeSinglesDoubles;
    private String level;
    private Date startDate;
    private Date endDate;
    private String status;

    public League() {
    }

    public League(int leagueId, String leagueName, int numPlayerSlots, int numCourtsNeeded, int numEvents,
                  String typeSinglesDoubles, String level, Date startDate, Date endDate, String status ) {
        this.leagueId = leagueId;
        this.leagueName = leagueName;
        this.numPlayerSlots = numPlayerSlots;
        this.numCourtsNeeded = numCourtsNeeded;
        this.numEvents = numEvents;
        this.typeSinglesDoubles = typeSinglesDoubles;
        this.level = level;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public int getLeagueId() {return leagueId; }

    public void setLeagueId(int playerId) { this.leagueId = leagueId; }

    public String getLeagueName() {return leagueName; }

    public void setLeagueName(String leagueName) { this.leagueName = leagueName; }

    public int getNumPlayerSlots() {return numPlayerSlots; }

    public void setNumPlayerSlots(int numPlayerSlots) { this.numPlayerSlots = numPlayerSlots; }

    public int getNumCourtsNeeded() {return numCourtsNeeded; }

    public void setNumCourtsNeeded(int numCourtsNeeded) { this.numCourtsNeeded = numCourtsNeeded; }

    public int getNumEvents() {return numEvents; }

    public void setNumEvents(int numEvents) { this.numEvents = numEvents; }

    public String getTypeSinglesDoubles() {return typeSinglesDoubles; }

    public void setTypeSinglesDoubles(String typeSinglesDoubles) { this.typeSinglesDoubles = typeSinglesDoubles; }

    public String getLevel() {return level; }

    public void setLevel(String level) { this.level = level; }

    public Date getStartDate() {return startDate; }

    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public Date getEndDate() {return endDate; }

    public void setEndDate(Date endDate) { this.endDate = endDate; }

    public String getStatus() {return status; }

    public void setStatus(String status) { this.status = status; }


    public String toString() {
        return  System.lineSeparator() +
                "League: " + " "
                + leagueId + " "
                + leagueName + " "
                + numPlayerSlots + " "
                + numCourtsNeeded + " "
                + numEvents + " "
                + typeSinglesDoubles + " "
                + level + " "
                + startDate + " "
                + endDate + " "
                + status;
    }
}
