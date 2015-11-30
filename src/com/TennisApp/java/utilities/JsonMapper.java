package com.TennisApp.java.utilities;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.log4j.Logger;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by Dave on 11/30/2015.
 * Use in validation of email using web service
 */
public class JsonMapper {
    private final Logger logger = Logger.getLogger(this.getClass());

    public static ObjectMapper jsonMapper = new ObjectMapper();

    /**
     * Encode any instance to the JSON string
     *
     * @param data Object to be converted to JSON string
     * @return String json string of Object
     */
    public static <T> String encode(T data) {

        try {
            return jsonMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
        }
        return "{}";
    }

    /**
     * Decode any instance to the mapped Java Class
     *
     * @param data JSON string to be converted to Java Object
     * @param <T>  Class file of object to be converted to Object
     *
     * @return Class Instance
     */
    public static <T> T decode(String data, Class<T> type) {
        try {
            return jsonMapper.readValue(data, type);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}