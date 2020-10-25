package com.assignment.shoppingcart.webservice;

import android.content.Context;

import com.assignment.shoppingcart.model.ShoppingData;
import com.assignment.shoppingcart.utils.JsonHelper;

import java.io.IOException;

/**
 * Class responsible for providing ShoppingData to user
 */
public class ShoppingDataWebService extends LocalWebService implements IWebService {
    public ShoppingDataWebService(Context context) {
        super(context);
    }

    @Override
    public ShoppingData execute() throws IOException {

        String resultJson = get(WSConstants.SHOPPING_FILE_NAME);
        return JsonHelper.getInstance().fromJsonString(resultJson, ShoppingData.class);
    }
}
