package com.TennisApp.java.persistance;

/**
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

}
