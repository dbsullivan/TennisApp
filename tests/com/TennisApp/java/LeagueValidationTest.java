package com.TennisApp.java;

import com.TennisApp.java.utilities.DateValidator;
import com.TennisApp.java.utilities.UserInputTypeCheck;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Dave on 11/27/2015.
 * Validation of League input form logic, the following input string.
 *
 *    performValidations(String leagueName, String leagueTypeSnglDbls, String leaguePlayerSlots, String leagueCourtsNeeded,
 *                      String leagueEvents, String leagueNTRPLevel, String leagueStartDate, String leagueEndDate, String leagueStatus)
 */
public class LeagueValidationTest {

    /**
     * Test for missing value and error type for leagueName
     */
    @Test
    public void testleagueNameFilled() {
        LeagueValidation leagueValidation = new LeagueValidation();
        assertNotNull(leagueValidation);
        leagueValidation.performValidations(null, "2", "3", "4", "5", "6", "01/01/2016", "06/30/2016", "9");
        assertEquals("Please enter missing League Name.", leagueValidation.getErrorMessage());
        assertEquals("leagueNameErr", leagueValidation.getErrorType());
    }

    /**
     * Test for missing value and error type for leagueTypeSnglDbls
     */
    @Test
    public void testleagueTypeSnglDblsFilled() {
        LeagueValidation leagueValidation = new LeagueValidation();
        assertNotNull(leagueValidation);
        leagueValidation.performValidations("1", null, "3", "4", "5", "6", "01/01/2016", "06/30/2016", "9");
        assertEquals("Please enter missing League Type Singles or Doubles.", leagueValidation.getErrorMessage());
        assertEquals("leagueTypeSnglDblsErr", leagueValidation.getErrorType());
    }

    /**
     * Test for missing value and error type for leaguePlayerSlots
     */
    @Test
    public void testleaguePlayerSlotsFilled() {
        LeagueValidation leagueValidation = new LeagueValidation();
        assertNotNull(leagueValidation);
        leagueValidation.performValidations("1", "2", null, "4", "5", "6", "01/01/2016", "06/30/2016", "9");
        assertEquals("Please enter missing Number of League Player Slots.", leagueValidation.getErrorMessage());
        assertEquals("leaguePlayerSlotsErr", leagueValidation.getErrorType());
    }

    /**
     * Test for correct data type for leaguePlayerSlots
     */
    @Test
    public void testleaguePlayerSlotsInteger() throws Exception {

        String leaguePlayerSlotsOther = "not Int";
        String leaguePlayerSlotsInt = "10";
        UserInputTypeCheck userInputTypeCheck = new UserInputTypeCheck();
        assertNotNull(userInputTypeCheck);
        assertFalse("true", userInputTypeCheck.isValidInt(leaguePlayerSlotsOther));
        assertTrue("true", userInputTypeCheck.isValidInt(leaguePlayerSlotsInt));
    }


    /**
     * Test for missing value and error type for leagueCourtsNeeded
     */
    @Test
    public void testleagueCourtsNeededFilled() {
        LeagueValidation leagueValidation = new LeagueValidation();
        assertNotNull(leagueValidation);
        leagueValidation.performValidations("1", "2", "3", null, "5", "6", "01/01/2016", "06/30/2016", "9");
        assertEquals("Please enter missing Number of League Courts Needed.", leagueValidation.getErrorMessage());
        assertEquals("leagueCourtsNeededErr", leagueValidation.getErrorType());
    }

    /**
     * Test for correct data type for leagueCourtsNeeded
     */
    @Test
    public void testleagueCourtsNeededInteger() throws Exception {

        String leagueCourtsNeededOther = "not Int";
        String leagueCourtsNeededInt = "10";
        UserInputTypeCheck userInputTypeCheck = new UserInputTypeCheck();
        assertNotNull(userInputTypeCheck);
        assertFalse("true", userInputTypeCheck.isValidInt(leagueCourtsNeededOther));
        assertTrue("true", userInputTypeCheck.isValidInt(leagueCourtsNeededInt));
    }

    /**
     * Test for missing value and error type for leagueEvents
     */
    @Test
    public void testleagueEventsFilled() {
        LeagueValidation leagueValidation = new LeagueValidation();
        assertNotNull(leagueValidation);
        leagueValidation.performValidations("1", "2", "3", "4", null, "6", "01/01/2016", "06/30/2016", "9");
        assertEquals("Please enter missing Number of League Events.", leagueValidation.getErrorMessage());
        assertEquals("leagueEventsErr", leagueValidation.getErrorType());
    }

    /**
     * Test for correct data type for leagueEvents
     */
    @Test
    public void testleagueEventsInteger() throws Exception {

        String leagueEventsOther = "not Int";
        String leagueEventsInt = "10";
        UserInputTypeCheck userInputTypeCheck = new UserInputTypeCheck();
        assertNotNull(userInputTypeCheck);
        assertFalse("true", userInputTypeCheck.isValidInt(leagueEventsOther));
        assertTrue("true", userInputTypeCheck.isValidInt(leagueEventsInt));
    }

    /**
     * Test for missing value and error type for leagueNTRPLevel
     */
    @Test
    public void testleagueNTRPLevelFilled() {
        LeagueValidation leagueValidation = new LeagueValidation();
        assertNotNull(leagueValidation);
        leagueValidation.performValidations("1", "2", "3", "4", "5", null, "01/01/2016", "06/30/2016", "9");
        assertEquals("Please enter missing League NTRP Level.", leagueValidation.getErrorMessage());
        assertEquals("leagueNTRPLevelErr", leagueValidation.getErrorType());
    }

    /**
     * Test for correct data type for leagueNTRPLevel
     */
    @Test
    public void testleagueNTRPLevelInteger() throws Exception {

        String leagueNTRPLevelOther = "not Int";
        String leagueNTRPLevelInt = "10";
        UserInputTypeCheck userInputTypeCheck = new UserInputTypeCheck();
        assertNotNull(userInputTypeCheck);
        assertFalse("true", userInputTypeCheck.isValidInt(leagueNTRPLevelOther));
        assertTrue("true", userInputTypeCheck.isValidInt(leagueNTRPLevelInt));
    }

    /**
     * Test for missing value and error type for leagueStartDate
     */
    @Test
    public void testleagueStartDateFilled() {
        LeagueValidation leagueValidation = new LeagueValidation();
        assertNotNull(leagueValidation);
        leagueValidation.performValidations("1", "2", "3", "4", "5", "6", null, "06/30/2016", "9");
        assertEquals("Please enter missing League Start Date.", leagueValidation.getErrorMessage());
        assertEquals("leagueStartDateErr", leagueValidation.getErrorType());
    }

    /**
     * Test for correct data type for leagueStartDate
     */
    @Test
    public void testleagueStartDateType() throws Exception {

        String leagueStartDateOther = "not Date";
        String leagueStartDateDATE = "01/01/2016";
        DateValidator dateValidator = new DateValidator();
        assertNotNull(dateValidator);
        assertFalse("false", dateValidator.isDateValid(leagueStartDateOther, "MM/dd/yyyy")); // unparseable returns false
        assertTrue("true", dateValidator.isDateValid(leagueStartDateDATE, "MM/dd/yyyy"));
    }

    /**
     * Test for missing value and error type for leagueEndDate
     */
    @Test
    public void testleagueEndDateFilled() {
        LeagueValidation leagueValidation = new LeagueValidation();
        assertNotNull(leagueValidation);
        leagueValidation.performValidations("1", "2", "3", "4", "5", "6", "01/01/2016", null, "9");
        assertEquals("Please enter missing League End Date.", leagueValidation.getErrorMessage());
        assertEquals("leagueEndDateErr", leagueValidation.getErrorType());
    }

    /**
     * Test for correct data type for leagueEndDate
     */
    @Test
    public void testleagueEndDateType() throws Exception {

        String leagueEndDateOther = "not Date";
        String leagueEndDateDATE = "06/30/2016";
        DateValidator dateValidator = new DateValidator();
        assertNotNull(dateValidator);
        assertFalse("false", dateValidator.isDateValid(leagueEndDateOther, "MM/dd/yyyy")); // unparseable returns false
        assertTrue("true", dateValidator.isDateValid(leagueEndDateDATE, "MM/dd/yyyy"));
    }

    /**
     * Test for missing value and error type for leagueStatus
     */
    @Test
    public void testleagueStatusFilled() {
        LeagueValidation leagueValidation = new LeagueValidation();
        assertNotNull(leagueValidation);
        leagueValidation.performValidations("1", "2", "3", "4", "5", "6", "01/01/2016", "06/30/2016", null);
        assertEquals("Please enter missing League Status.", leagueValidation.getErrorMessage());
        assertEquals("leagueStatusErr", leagueValidation.getErrorType());
    }

    @Test
    public void testGetErrorMessage() throws Exception {
        // handled above
    }

    @Test
    public void testGetErrorType() throws Exception {
        // handled above
    }
}