package com.assignment.shoppingcart.injection.module;

import android.app.Application;

import com.assignment.shoppingcart.data.catalogue.DataManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


/**
 * Provide application-level dependencies. Mainly singleton object that can be injected from
 * anywhere in the app.
 */
@Module
public class ApplicationModule {
    protected final Application mApplication;

    public ApplicationModule(Application application) {
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
        return new DataManager(mApplication);
    }

}