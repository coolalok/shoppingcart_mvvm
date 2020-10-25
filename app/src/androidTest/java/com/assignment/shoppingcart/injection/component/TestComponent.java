package com.assignment.shoppingcart.injection.component;


import com.assignment.shoppingcart.injection.module.ApplicationTestModule;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = ApplicationTestModule.class)
public interface TestComponent extends ApplicationComponent {

}
