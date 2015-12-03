package com.TennisApp.java.utilities;

import com.TennisApp.java.entity.Email;
import com.fasterxml.jackson.core.JsonParseException;
import org.apache.log4j.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import com.TennisApp.java.utilities.JsonMapper.*;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;


/**
 * Created by Dave on 11/30/2015.
 */
public class WebServiceEmailValidationJSON {
    private final Logger logger = Logger.getLogger(this.getClass());

    private Client client;
    private String REST_SERVICE_URL = "http://localhost:9012/EmailValidation";

    private void init(){
        this.client = ClientBuilder.newClient();
    }

    /**
     * @param emailToTest
     * @return
     */
    public Boolean isValidEmail(String emailToTest) {

        init();
        Boolean returnValue=false;

        String callResponse = client
                .target(REST_SERVICE_URL)
                .path("/json/{emailAddress}")
                .resolveTemplate("emailAddress", emailToTest)
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);

        ObjectMapper jsonMapper = new ObjectMapper();
        Email email = new Email();

        try {
            email = jsonMapper.readValue(callResponse, Email.class);
            returnValue = email.getIsValid();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//
//        try {
//            Email email = new Email();
//            email = JsonMapper.decode(callResponse, Email.class);
//            returnValue = email.getIsValid();
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//        }

        return returnValue;
    }
}
