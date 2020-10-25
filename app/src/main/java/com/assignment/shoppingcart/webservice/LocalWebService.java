package com.assignment.shoppingcart.webservice;

import android.content.Context;

import com.assignment.shoppingcart.utils.JsonHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Alok.Kulkarni on 4/28/2016.
 */
public abstract class LocalWebService {

    private Context context;

    public LocalWebService(Context context) {
        this.context = context;
    }


    public abstract <T> T execute() throws Exception;


    public <T> T parseAssetsJson(String filename, Class<T> responseClass) throws IOException {
        BufferedReader reader = null;
        StringBuffer buffer = new StringBuffer();
        try {
            reader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open(filename), "UTF-8"));

            // do reading, usually loop until end of file reading
            String strLine;
            while ((strLine = reader.readLine()) != null) {
                buffer.append(strLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //ToDo Update code to separate parsing and file read calls
        String jsonString = buffer.toString();
        return JsonHelper.getInstance().fromJsonString(jsonString, responseClass);


    }
}
