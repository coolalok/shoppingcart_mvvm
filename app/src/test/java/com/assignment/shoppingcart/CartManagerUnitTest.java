package com.assignment.shoppingcart;

import android.content.Context;
import android.content.SharedPreferences;

import com.assignment.shoppingcart.data.cart.CartManager;
import com.assignment.shoppingcart.model.Product;
import com.assignment.shoppingcart.storage.DataStorage;
import com.assignment.shoppingcart.utils.MockModelsUtil;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 * ToDo Need to get the tests working correctly
 */
public class CartManagerUnitTest {

    private Context mockedContext;
    private CartManager cartManager;
    private SharedPreferences mockedSharedPreference;
    private SharedPreferences.Editor mockedEditor;

    @Before
    public void setUp() {
        mockedContext = Mockito.mock(Context.class);
        cartManager = CartManager.getInstance();
        cartManager.init(mockedContext);
        mockedSharedPreference = Mockito.mock(SharedPreferences.class);
        Mockito.when(mockedContext.getSharedPreferences(Mockito.anyString(), Mockito.anyInt())).thenReturn(mockedSharedPreference);
        mockedEditor = Mockito.mock(SharedPreferences.Editor.class);
        Mockito.when(mockedSharedPreference.edit()).thenReturn(mockedEditor);
    }

    @Test
    public void addToCartTest() throws Exception {

        Product product = MockModelsUtil.createMockProduct();
        cartManager.addProduct(product);
      //  Mockito.verify(mockedEditor).apply();
        assertEquals(CartManager.getInstance().getCartSize(), 1);

        // Product product =  Mockito.mock(Product.class);


    }

    @Test
    public void removeFromCartTest() throws Exception {

        Product product = MockModelsUtil.createMockProduct();
        CartManager.getInstance().addProduct(product);
        CartManager.getInstance().removeProduct(product);
        assertEquals(CartManager.getInstance().getCartSize(), 0);
    }

    @Test
    public void checkCartSizeTest() throws Exception {
        ArrayList<Product> products = MockModelsUtil.createMockProductList();
        CartManager.getInstance().setCartProducts(products);
        assertEquals(CartManager.getInstance().getCartSize(), products.size());
    }

    @Test
    public void checkCartDuplicateProductTest() throws Exception {
        Product product1 = MockModelsUtil.createMockProduct();
        Product product2 = MockModelsUtil.createMockProduct();
        CartManager.getInstance().addProduct(product1);
        CartManager.getInstance().addProduct(product2);
        assertEquals(CartManager.getInstance().getCartSize(), 2);
    }

    @Test
    public void checkCartPriceTest() throws Exception {
        Float price1 = 40000F;
        Float price2 = 10000F;
        Float price3 = 8000F;
        Product product1 = MockModelsUtil.createMockProduct("Product1", "Panasonic Vierra", price1, "file:///android_asset/electronics/television.jpg");
        Product product2 = MockModelsUtil.createMockProduct("Product2", "Samsung Microwave", price2, "file:///android_asset/electronics/microwave.jpg");
        Product product3 = MockModelsUtil.createMockProduct("Product3", "Samsung Microwave", price3, "file:///android_asset/electronics/microwave.jpg");
        CartManager.getInstance().addProduct(product1);
        CartManager.getInstance().addProduct(product2);
        CartManager.getInstance().addProduct(product3);
        CartManager.getInstance().removeProduct(product2);
        Float expectedPrice = price1 + price3;
        assertEquals(CartManager.getInstance().getTotalPrice(), expectedPrice, 0);
    }
}