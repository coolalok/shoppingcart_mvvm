package com.assignment.shoppingcart.injection.module;

import android.app.Application;

import com.assignment.shoppingcart.data.catalogue.DataManager;
import com.assignment.shoppingcart.testUtils.TestDataManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Provides application-level dependencies for testing environment
 * This allows injecting mocks if necessary.
 */
@Module
public class ApplicationTestModule {
    private final Application mApplication;

    public ApplicationTestModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    DataManager provideDataManager() {
        return new TestDataManager(mApplication);
    }

}