package com.TennisApp.java;

/**
 * Created by Dave on 11/21/2015.
 */
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateValidator {
    private final Logger logger = Logger.getLogger(this.getClass());

    public boolean isDateValid(String dateToValidate, String dateFormat){

        if(dateToValidate == null){
            return false;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);
        try {
            //if not valid, it will throw ParseException
            Date date = sdf.parse(dateToValidate);
            logger.info("This date is in DateValidator: " + date);

        } catch (ParseException pe) {
//            e.printStackTrace();
            logger.error("This date could not be parsed, not fatal error", pe);
            return false;
        }
        return true;
    }

}
