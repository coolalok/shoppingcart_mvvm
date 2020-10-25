package com.assignment.shoppingcart.data;

import android.content.Context;

import com.assignment.shoppingcart.ShoppingCartApplication;
import com.assignment.shoppingcart.events.ShoppingDataReceivedEvent;
import com.assignment.shoppingcart.model.ShoppingData;
import com.assignment.shoppingcart.webservice.ErrorCode;
import com.assignment.shoppingcart.webservice.ShoppingDataWebService;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

/**
 * Manages fetching data from data source
 */
public class DataManager {
    private Context mContext;

    public DataManager(Context context) {
        this.mContext = context;
    }

    public void getData() {
        ShoppingDataWebService webService = new ShoppingDataWebService(mContext);
        try {
            ShoppingData shoppingData = webService.execute();
            EventBus.getDefault().post(new ShoppingDataReceivedEvent(shoppingData, ErrorCode.ERROR_NONE));
        } catch (IOException e) {
            e.printStackTrace();
            EventBus.getDefault().post(new ShoppingDataReceivedEvent(null, ErrorCode.ERROR_PARSE_RESPONSE));
        }
    }
}
