package com.assignment.shoppingcart.data.cart;

import android.content.Context;

import com.assignment.shoppingcart.ShoppingCartApplication;
import com.assignment.shoppingcart.events.CartAddEvent;
import com.assignment.shoppingcart.events.CartLoadEvent;
import com.assignment.shoppingcart.events.CartRemoveEvent;
import com.assignment.shoppingcart.exceptions.InvalidPriceException;
import com.assignment.shoppingcart.model.Product;
import com.assignment.shoppingcart.storage.CartStorage;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * Created by Alok.Kulkarni on 5/1/2016.
 * This class provides all the operations that can be done over the Cart
 */
public class CartManager {

    private CartStorage mCartStorage;
    private Cart mCart;
    private static CartManager sCartManager = new CartManager();
    private boolean bCartLoaded;

    public class CartUpdateEventConstants {
        public static final int ADD_TO_CART = 1;
        public static final int REMOVE_FROM_CART = 2;
        public static final int REMOVE_ALL_FROM_CART = 3;
        public static final int LOAD_CART = 4;
    }


    private CartManager() {
        mCart = new Cart();

    }

    public static CartManager getInstance() {
        return sCartManager;
    }

    /**
     * Call this once at application launch
     *
     * @param context Context
     */
    public void init(Context context) {
        mCartStorage = new CartStorage(context);
    }

    /**
     * Adds a product to the Cart
     *
     * @param product Product to be added
     * @return True if product was succesfully added
     */
    public boolean addProduct(Product product) {

        try {
            boolean result = mCart.addProduct(product);
            if (result) {
                processCartUpdate(CartUpdateEventConstants.ADD_TO_CART);
                CartAddEvent event = new CartAddEvent();
                EventBus.getDefault().post(event);
            }
            return result;
        } catch (InvalidPriceException e) {
            e.printStackTrace();
        }
        return false;

    }

    /**
     * Removes a product from the Cart
     *
     * @param product Product to be removed
     * @return True if product was succesfully removed
     */
    public boolean removeProduct(Product product) {

        boolean result = mCart.removeProduct(product);
        if (result) {
            processCartUpdate(CartUpdateEventConstants.REMOVE_FROM_CART);
            CartRemoveEvent event = new CartRemoveEvent(product);
            EventBus.getDefault().post(event);
        }
        return result;

    }

    /**
     * Gets the number of items in the Cart
     *
     * @return Return the number of items in Cart
     */
    public int getCartSize() {
        return mCart.getCartSize();

    }

    /**
     * Checks if a product is in the Cart
     *
     * @param aProduct Product to check
     * @return True if Product was found
     */
    public boolean checkProductInCart(Product aProduct) {
        return mCart.checkProductInCart(aProduct);
    }

    /**
     * Gets all the products in the Cart
     *
     * @return Product List
     */
    public ArrayList<Product> getCartProducts() {
        return mCart.getCartProducts();

    }

    /**
     * Adds a list of
     *
     * @param cartProducts
     */
    public void setCartProducts(ArrayList<Product> cartProducts) {
        mCart.setCartProducts(cartProducts);
        processCartUpdate(CartUpdateEventConstants.ADD_TO_CART);
        CartAddEvent event = new CartAddEvent();
        EventBus.getDefault().post(event);

    }

    /**
     * Saves the Cart data to persistent storage
     */
    private void saveCart() {
        String jsonCartData = mCart.saveCart();
        mCartStorage.store(CartStorage.CART_DATA, jsonCartData);
    }

    /**
     * Cart should be loaded before doing any further processing. Once cart is loaded , it fires a CartUpdateEvent that interested Object can register.
     */
    public synchronized void loadCart() {
        if (bCartLoaded) {
            CartLoadEvent event = new CartLoadEvent();
            EventBus.getDefault().post(event);
            return;
        }
        String jsonCartData = mCartStorage.load(CartStorage.CART_DATA);
        if (jsonCartData == null)
            return;
        mCart.loadCart(jsonCartData);
        processCartUpdate(CartUpdateEventConstants.LOAD_CART);

        CartLoadEvent event = new CartLoadEvent();
        EventBus.getDefault().post(event);
        bCartLoaded = true;
    }

    /**
     * Persists the cart updates and computes the Cart price.
     *
     * @param cartUpdateEvent Value of type CartUpdateEventConstants
     */
    private void processCartUpdate(int cartUpdateEvent) {
        //Save Cart for events such as Add and Remove
        if (cartUpdateEvent == CartUpdateEventConstants.ADD_TO_CART ||
                cartUpdateEvent == CartUpdateEventConstants.REMOVE_FROM_CART ||
                cartUpdateEvent == CartUpdateEventConstants.REMOVE_ALL_FROM_CART) {
            saveCart();
        }
        calculateCartPrice();


    }

    /**
     * Calculates the total Cart price
     */
    private void calculateCartPrice() {
        mCart.calculateCartPrice();
    }

    /**
     * Gets the total Cart price
     */
    public Float getTotalPrice() {
        return mCart.calculateCartPrice();
    }
}
