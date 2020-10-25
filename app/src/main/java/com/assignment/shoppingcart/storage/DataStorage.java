package com.assignment.shoppingcart.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.assignment.shoppingcart.ShoppingCartApplication;
import com.assignment.shoppingcart.view.activity.ShopActivity;

/**
 * Created by Alok.Kulkarni on 5/1/2016.
 * This class manages operations for local data storage and retrieval
 */
public class DataStorage {

    //ToDo Make this class generic to store different table structure , not just Cart table
    private final String MyPREFERENCES = "DataStorage";
    private Context mContext;

    public DataStorage(Context context) {
        this.mContext = context;
    }


    public void store(String column_name, String value) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(column_name, value);
        editor.commit();
    }

    public String load(String column_name) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        return sharedPreferences.getString(column_name, null);
    }
}
