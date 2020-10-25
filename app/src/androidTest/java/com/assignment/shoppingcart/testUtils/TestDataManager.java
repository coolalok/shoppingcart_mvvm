package com.assignment.shoppingcart.testUtils;

import android.content.Context;

import com.assignment.shoppingcart.ShoppingCartApplication;
import com.assignment.shoppingcart.data.catalogue.DataManager;
import com.assignment.shoppingcart.injection.component.DaggerDataManagerTestComponent;
import com.assignment.shoppingcart.injection.component.TestComponent;
import com.assignment.shoppingcart.injection.module.DataManagerTestModule;


/**
 * Extension of DataManager to be used on a testing environment.
 * It uses DataManagerTestComponent to inject mock dependencies that are different to the
 * normal runtime ones..
 */
public class TestDataManager extends DataManager {

    public TestDataManager(Context context) {
        super(context);
    }

    @Override
    protected void injectDependencies(Context context) {
        TestComponent testComponent = (TestComponent)
                ShoppingCartApplication.get(context).getComponent();
        DaggerDataManagerTestComponent.builder()
                .testComponent(testComponent)
                .dataManagerTestModule(new DataManagerTestModule())
                .build()
                .inject(this);
    }


    @Override
    public void getShoppingData() {
        //Do nothing as we want to mock the ShoppingData event
    }
}