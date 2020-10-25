package com.assignment.shoppingcart.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Utility class for JSON parsing . Helps converting JSON to POJO and POJO to JSON
 */


public class JsonHelper {
    private static JsonHelper sJsonHelper = new JsonHelper();
    private final ObjectMapper objectMapper;


    private JsonHelper() {
        objectMapper = new ObjectMapper();
        //Enable access to private fields
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }

    public static JsonHelper getInstance() {
        return sJsonHelper;
    }

    /**
     * Generic function to convert a Json String to a class object
     *
     * @param jsonString    Json string input
     * @param responseClass Class refrence for object type
     * @param <T>           Class type of object
     * @return Returns the class type object created using Json string
     * @throws IOException
     */
    public <T> T fromJsonString(String jsonString, Class<T> responseClass) throws IOException {
        T genericType = null;
        try {
            genericType = objectMapper.readValue(jsonString, responseClass);
        } catch (JsonMappingException e) {
            e.printStackTrace();
            return null;
        }
        return genericType;
    }

    /**
     * Converts an Object to Json string
     *
     * @param object Object to convert to Json string
     * @return Json string conversion for the object
     * @throws IOException
     */
    public String toJsonString(Object object) throws IOException {

        return objectMapper.writeValueAsString(object);
    }


}
