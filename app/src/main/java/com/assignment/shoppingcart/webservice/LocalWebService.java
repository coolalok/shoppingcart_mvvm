package com.assignment.shoppingcart.webservice;

import android.content.Context;

import com.assignment.shoppingcart.utils.FileUtils;

import java.io.IOException;

/**
 * Local class to Mock data load from server
 */
public abstract class LocalWebService {

    private Context context;

    public LocalWebService(Context context) {
        this.context = context;
    }

    public String get(String url) throws IOException {
        return FileUtils.read(context, url);

    }
}
