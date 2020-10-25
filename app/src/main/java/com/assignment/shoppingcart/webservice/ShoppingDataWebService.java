package com.assignment.shoppingcart.webservice;

import android.content.Context;


import com.assignment.shoppingcart.model.ShoppingData;
import com.assignment.shoppingcart.utils.JsonHelper;

import java.io.IOException;

/**
 * Created by Alok.Kulkarni on 4/28/2016.
 */
public class ShoppingDataWebService extends LocalWebService {
    public ShoppingDataWebService(Context context) {
        super(context);
    }

    @Override
    public ShoppingData execute() throws IOException {
        return parseAssetsJson("shoppingData.json", ShoppingData.class);
    }
}
