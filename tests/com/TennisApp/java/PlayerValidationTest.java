package com.TennisApp.java;

import com.TennisApp.java.utilities.WebServiceEmailValidation;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Dave on 11/27/2015.
 */
public class PlayerValidationTest {

//    @Before
//    public void setUp() {
//        PlayerValidation playerValidation = new PlayerValidation();
//    }

    @Test
    public void testFirstNameFilled() {
        PlayerValidation playerValidation = new PlayerValidation();
        playerValidation.performValidations(null, "2", "dbsullivan@madisoncollege.edu", "4", "5", "6");
        assertEquals("Please enter missing First Name.", playerValidation.getErrorMessage());
        assertEquals("firstNameErr", playerValidation.getErrorType());
    }

    @Test
    public void testLastNameFilled() {
        PlayerValidation playerValidation = new PlayerValidation();
        playerValidation.performValidations("1", null, "dbsullivan@madisoncollege.edu", "4", "5", "6");
        assertEquals("Please enter missing Last Name.", playerValidation.getErrorMessage());
        assertEquals("lastNameErr", playerValidation.getErrorType());
    }

    @Test
    public void testEmailFilled() {
        PlayerValidation playerValidation = new PlayerValidation();
        playerValidation.performValidations("1", "2", null, "4", "5", "6");
        assertEquals("Please enter missing email.", playerValidation.getErrorMessage());
        assertEquals("emailErr", playerValidation.getErrorType());
    }

    @Test
    public void testGenderFilled() {
        PlayerValidation playerValidation = new PlayerValidation();
        playerValidation.performValidations("1", "2", "dbsullivan@madisoncollege.edu", null, "5", "6");
        assertEquals("Please enter missing Gender.", playerValidation.getErrorMessage());
        assertEquals("genderErr", playerValidation.getErrorType());
    }

    @Test
    public void testLevelFilled() {
        PlayerValidation playerValidation = new PlayerValidation();
        playerValidation.performValidations("1", "2", "dbsullivan@madisoncollege.edu", "4", null, "6");
        assertEquals("Please enter missing NTRP Level.", playerValidation.getErrorMessage());
        assertEquals("ntrpLevelErr", playerValidation.getErrorType());
    }

    @Test
    public void testPhoneFilled() {
        PlayerValidation playerValidation = new PlayerValidation();
        playerValidation.performValidations("1", "2", "dbsullivan@madisoncollege.edu", "4", "5", null);
        assertEquals( "Please enter missing Phone Number.", playerValidation.getErrorMessage());
        assertEquals("phoneNumberErr", playerValidation.getErrorType());
    }

    @Test
    public void testEmailValidation() throws Exception {

        WebServiceEmailValidation webServiceEmailValidation = new WebServiceEmailValidation();
        assertNotNull(webServiceEmailValidation);
        assertFalse("true", webServiceEmailValidation.isValidEmail("mailInvalid"));
        assertTrue("true", webServiceEmailValidation.isValidEmail("dbsullivan@madisoncollege.edu"));
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