package com.assignment.shoppingcart;

import android.app.Application;
import android.content.Context;

import com.assignment.shoppingcart.data.cart.CartManager;
import com.assignment.shoppingcart.injection.component.ApplicationComponent;
import com.assignment.shoppingcart.injection.component.DaggerApplicationComponent;
import com.assignment.shoppingcart.injection.module.ApplicationModule;

/**
 * Application class
 */
public class ShoppingCartApplication extends Application {
    private static Context mContext;
    ApplicationComponent mApplicationComponent;


    public static ShoppingCartApplication get(Context context) {
        return (ShoppingCartApplication) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        CartManager.getInstance().init(mContext);
    }


}
