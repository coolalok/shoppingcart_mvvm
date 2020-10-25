package com.assignment.shoppingcart;

import android.app.Application;
import android.content.Context;

import com.assignment.shoppingcart.data.cart.CartManager;

/**
 * Created by Alok.Kulkarni on 4/26/2016.
 */
public class ShoppingCartApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        CartManager.getInstance().init(mContext);
    }

    //ToDo Fix issue of keeping static reference to Context.
    public static Context getAppContext() {
        return mContext;
    }


}
