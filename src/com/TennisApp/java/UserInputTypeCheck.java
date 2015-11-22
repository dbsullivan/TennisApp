package com.TennisApp.java;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.log4j.Logger;

/**
 * Created by Dave on 11/21/2015.
 * Intended to validate form String user input against the expected database numeric types.
 *
 */
public class UserInputTypeCheck {
    private final Logger logger = Logger.getLogger(this.getClass());

    // test for valid input as Int
    public Boolean isValidInt(String userInput) {
        if (!this.isType(userInput, "int")) {
            return false;
        }
        return true; //Integer.parseInt(userInput);
    }

    // test for valid input as Float
    public Boolean isValidFloat(String userInput) {
        if (!this.isType(userInput, "float")) {
            return false;
        }
        return true; //Float.parseFloat(userInput);
    }

    // test for valid input as Double
    public Boolean isValidDouble(String userInput) {
        if (!this.isType(userInput, "double")) {
            return false;
        }
        return true; // Double.parseDouble(userInput);
    }

    /**
     * Tests if a specific input can be converted to a specific type.
     *
     * @param testStr The input to test. Accepts String, int, double or float.
     * @param type    Which type to test against. Accepts 'int','float' or 'double'.
     * @return Boolean    True if can be transformed to requested type. False otherwise.
     */
    public Boolean isType(String testStr, String type) {
        try {
            if (type.equalsIgnoreCase("float")) {
                Float.parseFloat(testStr);
            } else if (type.equalsIgnoreCase("int")) {
                Integer.parseInt(testStr);
            } else if (type.equalsIgnoreCase("double")) {
                Double.parseDouble(testStr);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
