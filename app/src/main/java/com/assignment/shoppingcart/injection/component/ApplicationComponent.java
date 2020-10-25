package com.assignment.shoppingcart.injection.component;

import android.app.Application;

import com.assignment.shoppingcart.data.catalogue.DataManager;
import com.assignment.shoppingcart.injection.module.ApplicationModule;
import com.assignment.shoppingcart.view.activity.ShopActivity;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(ShopActivity mainActivity);

    Application application();

    DataManager dataManager();
}