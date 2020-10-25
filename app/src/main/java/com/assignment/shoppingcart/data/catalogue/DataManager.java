package com.assignment.shoppingcart.data.catalogue;

import android.content.Context;

import com.assignment.shoppingcart.ShoppingCartApplication;
import com.assignment.shoppingcart.events.ShoppingDataReceivedEvent;
import com.assignment.shoppingcart.injection.component.DaggerDataManagerComponent;
import com.assignment.shoppingcart.injection.module.DataManagerModule;
import com.assignment.shoppingcart.model.ShoppingData;
import com.assignment.shoppingcart.webservice.ErrorCode;
import com.assignment.shoppingcart.webservice.IWebService;
import com.assignment.shoppingcart.webservice.ShoppingDataWebService;

import org.greenrobot.eventbus.EventBus;

/**
 * Manages fetching data from data source
 */
public class DataManager {
    private Context mContext;

    public DataManager(Context context) {
        this.mContext = context;
        injectDependencies(context);

    }



    /* This constructor is provided so we can set up a DataManager with mocks from unit test.
     * At the moment this is not possible to do with Dagger because the Gradle APT plugin doesn't
     * work for the unit test variant, plus Dagger 2 doesn't provide a nice way of overriding
     * modules */

    protected void injectDependencies(Context context) {
        DaggerDataManagerComponent.builder()
                .applicationComponent(ShoppingCartApplication.get(context).getComponent())
                .dataManagerModule(new DataManagerModule())
                .build()
                .inject(this);
    }

    public void getShoppingData() {
        IWebService webService = new ShoppingDataWebService(mContext);

        ShoppingData shoppingData = null;
        try {
            shoppingData = webService.execute();
        } catch (Exception e) {
            e.printStackTrace();
            EventBus.getDefault().post(new ShoppingDataReceivedEvent(null, ErrorCode.ERROR_PARSE_RESPONSE));
        }
        EventBus.getDefault().post(new ShoppingDataReceivedEvent(shoppingData, ErrorCode.ERROR_NONE));

    }

}
