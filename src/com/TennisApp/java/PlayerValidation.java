package com.TennisApp.java;

import com.TennisApp.java.utilities.WebServiceEmailValidation;
import com.TennisApp.java.utilities.WebServiceEmailValidationJSON;
import org.apache.log4j.Logger;

/**
 * Created by Dave on 11/27/2015.
 * Performs user entry validation on player form.
 */
public class PlayerValidation {

    private final Logger logger = Logger.getLogger(this.getClass());

    String AddMessage = "";
    String ErrorType = "";

    /**
     * Performs user entry validation on player form String inputs
     * @param firstName
     * @param lastName
     * @param email
     * @param gender
     * @param ntrpLevel
     * @param phoneNumber
     */
    public void performValidations (String firstName, String lastName, String email, String gender, String ntrpLevel, String phoneNumber) {

//        WebServiceEmailValidation webServiceEmailValidation = new WebServiceEmailValidation(); // this is String version
        WebServiceEmailValidationJSON webServiceEmailValidation = new WebServiceEmailValidationJSON(); // this is JSON version

        if (firstName == null || firstName.equals("")) {
            AddMessage = "Please enter missing First Name.";
            ErrorType="firstNameErr";
        } else if (lastName == null || lastName.equals("")) {
            AddMessage = "Please enter missing Last Name.";
            ErrorType="lastNameErr";
        } else if (email == null || email.equals("")) {
            AddMessage = "Please enter missing email.";
            ErrorType="emailErr";
            //Now validate using WebService call, since email has passed as Not NULL or blank, call method passing email string
        } else if (!webServiceEmailValidation.isValidEmail(email)) {
            AddMessage = "Please enter valid email.";
            ErrorType="emailErr";
        } else if (gender == null || gender.equals("")) {
            AddMessage = "Please enter missing Gender.";
            ErrorType="genderErr";
        } else if (ntrpLevel == null || ntrpLevel.equals("")) {
            AddMessage = "Please enter missing NTRP Level.";
            ErrorType="ntrpLevelErr";
        } else if (phoneNumber == null || phoneNumber.equals("")) {
            AddMessage = "Please enter missing Phone Number.";
            ErrorType="phoneNumberErr";
        }
    }

    public String getErrorMessage() {
        return AddMessage;
    }

    public String getErrorType() {
        return ErrorType;
    }
}
