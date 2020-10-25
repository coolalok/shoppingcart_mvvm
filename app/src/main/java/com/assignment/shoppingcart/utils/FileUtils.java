package com.assignment.shoppingcart.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Utility class for File operations
 */
public class FileUtils {

    public static String read(Context context, String url) {
        BufferedReader reader = null;
        StringBuffer buffer = new StringBuffer();
        try {
            reader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open(url), "UTF-8"));

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
        return buffer.toString();

    }
}
