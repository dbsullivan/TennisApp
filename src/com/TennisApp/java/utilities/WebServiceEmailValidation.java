package com.TennisApp.java.utilities;

import org.apache.log4j.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.util.Objects;


/**
 * Created by Dave on 11/19/2015.
 */
public class WebServiceEmailValidation {
    private final Logger logger = Logger.getLogger(this.getClass());

    private Client client;
    private String REST_SERVICE_URL = "http://localhost:9998/test";

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
