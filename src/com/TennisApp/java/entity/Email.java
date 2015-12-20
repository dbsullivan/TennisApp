package com.TennisApp.java.entity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Dave on 11/30/2015.
 * Defines an Email bean for validation of user email by WebService
 *
 */

@XmlRootElement
public class Email {
    private String emailAddress;
    private boolean isValid;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String email) {
        this.emailAddress = email;
    }

    public boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }
}
