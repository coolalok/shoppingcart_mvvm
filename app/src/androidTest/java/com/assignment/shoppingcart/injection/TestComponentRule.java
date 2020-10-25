package com.assignment.shoppingcart.injection;

import android.support.test.InstrumentationRegistry;

import com.assignment.shoppingcart.ShoppingCartApplication;
import com.assignment.shoppingcart.injection.component.DaggerTestComponent;
import com.assignment.shoppingcart.injection.component.TestComponent;
import com.assignment.shoppingcart.injection.module.ApplicationTestModule;
import com.assignment.shoppingcart.testUtils.TestDataManager;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * Test rule that creates and sets a Dagger TestComponent into the application overriding the
 * existing application component.
 * This has been used to mock the dependency of DataManager.
 */
public class TestComponentRule implements TestRule {

    private TestComponent mTestComponent;

    public TestDataManager getDataManager() {
        return (TestDataManager) mTestComponent.dataManager();
    }

    private void setupDaggerTestComponentInApplication() {
        ShoppingCartApplication application = ShoppingCartApplication
                .get(InstrumentationRegistry.getTargetContext());
        if (application.getComponent() instanceof TestComponent) {
            mTestComponent = (TestComponent) application.getComponent();
        } else {
            mTestComponent = DaggerTestComponent.builder()
                    .applicationTestModule(new ApplicationTestModule(application))
                    .build();
            application.setComponent(mTestComponent);
        }
    }

    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                try {
                    setupDaggerTestComponentInApplication();
                    base.evaluate();
                } finally {
                    mTestComponent = null;
                }
            }
        };
    }
}