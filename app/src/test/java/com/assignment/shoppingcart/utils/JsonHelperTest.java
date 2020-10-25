package com.assignment.shoppingcart.utils;

import com.assignment.shoppingcart.model.Product;
import com.assignment.shoppingcart.testUtils.MockModelsUtil;

import junit.framework.Assert;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by Alok.Kulkarni on 4/28/2016.
 * Helper class for JSON parsing . Helps converting JSON to POJO and POJO to JSON
 */


public class JsonHelperTest {
    private JsonHelper jsonHelper;

    @Before
    public void setUp() {
        jsonHelper = JsonHelper.getInstance();
    }

    @Test
    public void testToJsonString() {

        Product product = MockModelsUtil.createMockProduct();
        try {
            String jsonString = jsonHelper.toJsonString(product);
            try {
                JSONObject jsonObject = new JSONObject(jsonString);
            } catch (JSONException e) {
                Assert.fail("Exception " + e);
            }
        } catch (IOException e) {
            Assert.fail("Exception " + e);
        }
    }

    public void testFromJsonString() {

        //   jsonHelper.fromJsonString();
    }
}
