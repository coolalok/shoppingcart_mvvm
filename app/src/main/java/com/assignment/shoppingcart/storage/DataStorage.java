package com.assignment.shoppingcart.storage;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * This class manages operations for local data storage and retrieval
 */
public class DataStorage {

    //ToDo Make this class generic to store different table structure , not just Cart table
    private final String MyPREFERENCES = "DataStorage";
    private Context mContext;
    private SharedPreferences mSharedPreferences;

    public DataStorage(SharedPreferences preferences) {

        this.mSharedPreferences = preferences;
    }


    public boolean store(String column_name, String value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(column_name, value);
        return editor.commit();
    }

    public String load(String column_name) {
        return mSharedPreferences.getString(column_name, null);
    }
}
