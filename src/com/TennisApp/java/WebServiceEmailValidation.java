package com.TennisApp.java;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import java.util.Objects;


/**
 * Created by Dave on 11/19/2015.
 */
public class WebServiceEmailValidation {

    private Client client;
    private String REST_SERVICE_URL = "http://localhost:9998/test";
//    private static final String SUCCESS_RESULT="<result>success</result>";
//    private static final String PASS = "true";
//    private static final String FAIL = "false";

    private void init(){
        this.client = ClientBuilder.newClient();
    }

    /**
     * @param emailToTest
     * @return
     */
    public Boolean isValidEmail(String emailToTest) {

        init();
        Boolean returnValue;

        String callResponse = client
                .target(REST_SERVICE_URL)
                .path("/isEmailValid/{emailtest}")
                .resolveTemplate("emailtest", emailToTest)
                .request(MediaType.TEXT_PLAIN)
                .get(String.class);

        if (Objects.equals(callResponse, "true")) {
            returnValue = true;
        } else {
            returnValue = false;
        };

        return returnValue;
    }

}
