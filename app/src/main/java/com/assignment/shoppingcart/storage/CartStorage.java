package com.assignment.shoppingcart.storage;


import android.content.Context;

import com.assignment.shoppingcart.ShoppingCartApplication;

/**
 * Created by Alok.Kulkarni on 5/1/2016.
 * Wrapper class for actual persistent storage
 */
public class CartStorage {
    public static final String CART_DATA = "CART_DATA";
    private DataStorage mDataStorage;

    public CartStorage(Context context) {
        mDataStorage = new DataStorage(context);
    }


    /**
     * Stores data into persistent storage
     *
     * @param COLUMN_NAME  The text used to store and retrieve data from storage
     * @param jsonCartData Json String to save to persistent storage
     */
    public void store(String COLUMN_NAME, String jsonCartData) {
        mDataStorage.store(COLUMN_NAME, jsonCartData);
    }

    public String load(String COLUMN_NAME) {
        return mDataStorage.load(COLUMN_NAME);
    }
}
