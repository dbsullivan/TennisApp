package com.TennisApp.java.utilities;

import com.TennisApp.java.entity.Email;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import java.io.IOException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

/**
 * Created by Dave on 12/06/2015.
 * This class uses a webservice developed internally, indicated in the URL, to validate the email passed.
 * Requires the Email object imported in the class that handle either JSON or XML webservice return types.
 * An Example JSON result: {"emailAdress":"dbsullivan@madisoncollege.edu","isValid":true}
 */
public class WebServiceEmailValidationJSON {
    private final Logger logger = Logger.getLogger(this.getClass());

    private Client client;
    private String REST_SERVICE_URL = "http://localhost:9012/EmailValidation";
    Email email = new Email();

    /**
     * This method will return a Boolean result, returnValue after calling the webservice to validate the
     * email input parameter, emailToTest.
     * @param emailToTest
     * @return returnValue
     */
    public Boolean isValidEmail (String emailToTest)  {

        Boolean returnValue=false;

        this.client = ClientBuilder.newClient();
        String callResponse = client
                .target(REST_SERVICE_URL)
                .path("/json/{emailAddress}")
                .resolveTemplate("emailAddress", emailToTest)
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);


        ObjectMapper jsonMapper = new ObjectMapper();
        try {
            this.email = jsonMapper.readValue(callResponse, Email.class);
        } catch (JsonMappingException e) {
            logger.error("Exception:", e);
        } catch (JsonParseException e) {
            logger.error("Exception:", e);
        } catch (IOException e) {
            logger.error("Exception:", e);
        }

        returnValue = email.getIsValid();

        return returnValue;
    }
}
