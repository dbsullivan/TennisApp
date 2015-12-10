package com.TennisApp.java.entity;

import java.util.Date;

/**
 * This is the League entity bean. Getters/Setters don't require javadoc.
 * Created by Dave on 11/11/2015.
 */
public class League_Assignment {
    private int leagueAssignId;
    private int leagueId;
    private int playerId;
    private int playerSlotNum;

    public League_Assignment() {
    }

    public League_Assignment(int leagueAssignId, int leagueId, int playerId, int playerSlotNum) {
        this.leagueAssignId = leagueAssignId;
        this.leagueId = leagueId;
        this.playerId = playerId;
        this.playerSlotNum = playerSlotNum;
    }

    public int getLeagueAssignId() {return leagueAssignId; }

    public void setLeagueAssignId(int leagueAssignId) { this.leagueAssignId = leagueAssignId; }

    public int getLeagueId() {return leagueId; }

    public void setLeagueId(int leagueId) { this.leagueId = leagueId; }

    public int getPlayerId() {return playerId; }

    public void setPlayerId(int playerId) { this.playerId = playerId; }

    public int getPlayerSlotNum() {return playerSlotNum; }

    public void setPlayerSlotNum(int playerSlotNum) { this.playerSlotNum = playerSlotNum; }


    public String toString() {
        return  System.lineSeparator() +
                "Id: " + " "
                + leagueAssignId + " "
                + "leagueId=" + leagueId + " "
                + "playerId=" + playerId + " "
                + "playerSlotNum="  + playerSlotNum ;
    }
}

