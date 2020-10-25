package com.assignment.shoppingcart.utils;

import com.assignment.shoppingcart.model.Product;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Alok.Kulkarni on 4/28/2016.
 * Helper class for JSON parsing . Helps converting JSON to POJO and POJO to JSON
 */


public class JsonHelper {
    private static JsonHelper sJsonHelper = new JsonHelper();
    private final ObjectMapper objectMapper;


    private JsonHelper() {
        objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }

    public static JsonHelper getInstance() {
        return sJsonHelper;
    }

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


    public String toJsonString(Object object) throws IOException {

        return objectMapper.writeValueAsString(object);
    }


}
