package com.assignment.shoppingcart.events;


import com.assignment.shoppingcart.model.ShoppingData;
import com.assignment.shoppingcart.webservice.ErrorCode;

/**
 * Created by Alok.Kulkarni on 4/30/2016.
 */
public class ShoppingDataReceivedEvent {


    private ShoppingData mShoppingData;
    private int mErrorCode;

    public ShoppingDataReceivedEvent(ShoppingData shoppingData, int errorCode) {
        this.mShoppingData = shoppingData;
        this.mErrorCode = errorCode;
    }

    public ShoppingData getShoppingData() {
        return mShoppingData;
    }

    public int getErrorCode() {
        return mErrorCode;
    }
}
