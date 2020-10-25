package com.assignment.shoppingcart.data.cart;

import com.assignment.shoppingcart.model.Product;
import com.assignment.shoppingcart.testUtils.MockModelsUtil;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

/**
 * Created by Alok.Kulkarni on 5/2/2016.
 */


public class CartUnitTest {

    private Cart cart;

    @Before
    public void setUp() {
        cart = new Cart();
    }

    @Test
    public void addToCartTest() throws Exception {
        Product product = MockModelsUtil.createMockProduct();
        assertTrue(cart.addProduct(product));
        Assert.assertEquals(cart.getCartSize(), 1);
    }

    @Test
    public void removeFromCartTest() throws Exception {

        Product product = MockModelsUtil.createMockProduct();
        cart.addProduct(product);
        cart.removeProduct(product);
        Assert.assertEquals(cart.getCartSize(), 0);
    }

    @Test
    public void checkCartSizeTest() throws Exception {
        ArrayList<Product> products = MockModelsUtil.createMockProductList();
        cart.setCartProducts(products);
        Assert.assertEquals(cart.getCartSize(), products.size());
    }

    @Test
    public void checkCartDuplicateProductTest() throws Exception {

        Product product1 = MockModelsUtil.createMockProduct();
        cart.addProduct(product1);
        cart.addProduct(product1);
        //Products are not same as their ID is different
        Assert.assertEquals(cart.getCartSize(), 1);
    }

    @Test
    public void checkCartPriceTest() throws Exception {
        Float price1 = 40000F;
        Float price2 = 10000F;
        Float price3 = 8000F;
        Product product1 = MockModelsUtil.createMockProduct("Product1", "Panasonic Vierra", price1, "file:///android_asset/electronics/television.jpg");
        Product product2 = MockModelsUtil.createMockProduct("Product2", "Samsung Microwave", price2, "file:///android_asset/electronics/microwave.jpg");
        Product product3 = MockModelsUtil.createMockProduct("Product3", "Samsung Microwave", price3, "file:///android_asset/electronics/microwave.jpg");
        cart.addProduct(product1);
        cart.addProduct(product2);
        cart.addProduct(product3);
        cart.removeProduct(product2);
        Float expectedPrice = price1 + price3;
        Assert.assertEquals(cart.calculateCartPrice(), expectedPrice, 0);
    }

    @Test
    public void getCartData() {
        ArrayList<Product> products = MockModelsUtil.createMockProductList();
        cart.setCartProducts(products);
        String strSaveCartData = cart.getSaveCartDataString();
        //If the String gets converted to a JSON object , at least its a valid JSON string
        try {
            JSONObject jsonCartData = new JSONObject(strSaveCartData);
        } catch (JSONException exception) {
            Assert.fail("Exception " + exception);
            return;
        }
    }
}
