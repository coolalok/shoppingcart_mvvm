package com.assignment.shoppingcart.injection.component;


import com.assignment.shoppingcart.data.catalogue.DataManager;
import com.assignment.shoppingcart.injection.module.DataManagerModule;
import com.assignment.shoppingcart.injection.scope.PerDataManager;

import dagger.Component;


@PerDataManager
@Component(dependencies = ApplicationComponent.class, modules = DataManagerModule.class)
public interface DataManagerComponent {

    void inject(DataManager dataManager);
}