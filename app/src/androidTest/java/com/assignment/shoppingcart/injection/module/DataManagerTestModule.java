package com.assignment.shoppingcart.injection.module;


import dagger.Module;

/**
 * Provides dependencies for an app running on a testing environment
 * This allows injecting mocks if necessary
 */
@Module
public class DataManagerTestModule {

    public DataManagerTestModule() {
    }

}
