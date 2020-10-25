package com.assignment.shoppingcart.injection.component;


import com.assignment.shoppingcart.injection.module.DataManagerTestModule;
import com.assignment.shoppingcart.injection.scope.PerDataManager;

import dagger.Component;

@PerDataManager
@Component(dependencies = TestComponent.class, modules = DataManagerTestModule.class)
public interface DataManagerTestComponent extends DataManagerComponent {
}