package com.TennisApp.java;

import com.TennisApp.java.utilities.DateValidator;
import com.TennisApp.java.utilities.UserInputTypeCheck;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Dave on 11/27/2015.
 * Performs user entry validation on league form String inputs.
 */
public class LeagueValidation {

    private final Logger logger = Logger.getLogger(this.getClass());

    String AddMessage = "";
    String ErrorType = "";

    /**
     * Performs user entry validation on league form String inputs.
     * @param leagueName
     * @param leagueTypeSnglDbls
     * @param leaguePlayerSlots
     * @param leagueCourtsNeeded
     * @param leagueEvents
     * @param leagueNTRPLevel
     * @param leagueStartDate
     * @param leagueEndDate
     * @param leagueStatus
     */
    public void performValidations(String leagueName, String leagueTypeSnglDbls, String leaguePlayerSlots, String leagueCourtsNeeded,
                                   String leagueEvents, String leagueNTRPLevel, String leagueStartDate, String leagueEndDate, String leagueStatus) {

        UserInputTypeCheck userInputTypeCheck = new UserInputTypeCheck();
        DateValidator dateValidator = new DateValidator();

        if (leagueName == null || leagueName.equals("") ) {
            AddMessage = "Please enter missing League Name.";
            ErrorType="leagueNameErr";
        } else if (leagueTypeSnglDbls == null || leagueTypeSnglDbls.equals("")) {
            AddMessage = "Please enter missing League Type Singles or Doubles.";
            ErrorType="leagueTypeSnglDblsErr";
        } else if (leaguePlayerSlots == null || leaguePlayerSlots.equals("")) {
            AddMessage = "Please enter missing Number of League Player Slots.";
            ErrorType="leaguePlayerSlotsErr";
        } else if ( !userInputTypeCheck.isValidInt(leaguePlayerSlots) ) {
            AddMessage = "Please enter valid number.";
            ErrorType="leaguePlayerSlotsErr";
        } else if (leagueCourtsNeeded == null || leagueCourtsNeeded.equals("")) {
            AddMessage = "Please enter missing Number of League Courts Needed.";
            ErrorType="leagueCourtsNeededErr";
        } else if ( !userInputTypeCheck.isValidInt(leagueCourtsNeeded) ) {
            AddMessage = "Please enter valid number.";
            ErrorType="leagueCourtsNeededErr";
        } else if (leagueEvents == null || leagueEvents.equals("")) {
            AddMessage = "Please enter missing Number of League Events.";
            ErrorType="leagueEventsErr";
        } else if ( !userInputTypeCheck.isValidInt(leagueEvents) ) {
            AddMessage = "Please enter valid number.";
            ErrorType="leagueEventsErr";
        } else if (leagueNTRPLevel == null || leagueNTRPLevel.equals("")) {
            AddMessage = "Please enter missing League NTRP Level.";
            ErrorType="leagueNTRPLevelErr";
        } else if (leagueStartDate == null || leagueStartDate.equals("")) {
            AddMessage = "Please enter missing League Start Date.";
            ErrorType="leagueStartDateErr";
        } else if ( !dateValidator.isDateValid(leagueStartDate, "yy-mm-dd") ) {
            AddMessage = "Please enter valid date.";
            ErrorType="leagueStartDateErr";
        } else if (leagueEndDate == null || leagueEndDate.equals("")) {
            AddMessage = "Please enter missing League End Date.";
            ErrorType="leagueEndDateErr";
        } else if ( !dateValidator.isDateValid(leagueEndDate, "yy-mm-dd") ) {
            AddMessage = "Please enter valid date.";
            ErrorType="leagueEndDateErr";
        } else if (leagueStatus == null || leagueStatus.equals("")) {
            AddMessage = "Please enter missing League Status.";
            ErrorType = "leagueStatusErr";
        }

    }

    public String getErrorMessage() {
        return AddMessage;
    }

    public String getErrorType() {
        return ErrorType;
    }
}

