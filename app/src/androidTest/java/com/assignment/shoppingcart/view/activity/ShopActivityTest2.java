package com.assignment.shoppingcart.view.activity;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.assignment.shoppingcart.R;
import com.assignment.shoppingcart.events.ShoppingDataReceivedEvent;
import com.assignment.shoppingcart.injection.TestComponentRule;
import com.assignment.shoppingcart.model.Category;
import com.assignment.shoppingcart.model.Product;
import com.assignment.shoppingcart.model.ShoppingData;
import com.assignment.shoppingcart.testUtils.MockModelsUtilAndroidTest;
import com.assignment.shoppingcart.webservice.ErrorCode;

import org.greenrobot.eventbus.EventBus;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class ShopActivityTest2 {

    @Rule
    public final ActivityTestRule<ShopActivity> shopActivityRule =
            new ActivityTestRule<>(ShopActivity.class, false, false);

    @Rule
    public final TestComponentRule component = new TestComponentRule();

    private final ShoppingData mShoppingData;

    public ShopActivityTest2() {
        mShoppingData = MockModelsUtilAndroidTest.getMockShoppingData();
    }

    /**
     * Test that checks if all categories are correctly displayed in the RecyclerView
     *
     * @throws Exception
     */
    @Test
    public void testCategoryView() throws Exception {
        Intent i = new Intent(ShopActivity.getStartIntent(InstrumentationRegistry.getTargetContext()));
        shopActivityRule.launchActivity(i);
        EventBus.getDefault().post(new ShoppingDataReceivedEvent(mShoppingData, ErrorCode.ERROR_NONE));
        checkCategoriesDisplayOnRecyclerView(mShoppingData.getCategory());
    }

    /**
     * Test that checks if all products for a particular category are correctly displayed in the RecyclerView
     *
     * @throws Exception
     */
    @Test
    public void testProductsView() throws Exception {
        Intent i = new Intent(ShopActivity.getStartIntent(InstrumentationRegistry.getTargetContext()));
        shopActivityRule.launchActivity(i);
        EventBus.getDefault().post(new ShoppingDataReceivedEvent(mShoppingData, ErrorCode.ERROR_NONE));
        Category category = mShoppingData.getCategory().get(0);
        onView(withText(category.getName())).check(matches(isDisplayed()));
        onView(withText(category.getName())).perform(click());
        checkProductsDisplayOnRecyclerView(category.getProducts());
    }

    /**
     * Test that checks if a particular product is displayed
     *
     * @throws Exception
     */
    @Test
    public void testProductView() throws Exception {
        Intent i = new Intent(ShopActivity.getStartIntent(InstrumentationRegistry.getTargetContext()));
        shopActivityRule.launchActivity(i);
        EventBus.getDefault().post(new ShoppingDataReceivedEvent(mShoppingData, ErrorCode.ERROR_NONE));
        Category category = mShoppingData.getCategory().get(0);
        onView(withText(category.getName())).perform(click());
        Product product = category.getProducts().get(0);

        //Check if ProductItemsFragment shows a Product name from the selected Category
        onView(withText(product.getName())).check(matches(isDisplayed()));
        onView(withText(product.getName())).perform(click());

        //Check if ProductDetailFragment shows Product name and price
        onView(withText(product.getName())).check(matches(isDisplayed()));
        String strPrice = Float.toString(product.getPrice());
        onView(withText(strPrice)).check(matches(isDisplayed()));

    }

    /**
     * Test that checks if Add to cart button is present on ProductDetailFragment and gets clicked to process adding of a Product to Cart
     *
     * @throws Exception
     */
    @Test
    public void testAddToCart() throws Exception {
        Intent i = new Intent(ShopActivity.getStartIntent(InstrumentationRegistry.getTargetContext()));
        shopActivityRule.launchActivity(i);
        EventBus.getDefault().post(new ShoppingDataReceivedEvent(mShoppingData, ErrorCode.ERROR_NONE));
        Category category = mShoppingData.getCategory().get(0);
        onView(withText(category.getName())).perform(click());
        Product product = category.getProducts().get(0);
        onView(withText(product.getName())).perform(click());

        onView(withId(R.id.cartButton)).check(matches(withText(R.string.add_to_cart_text)));
        onView(withId(R.id.cartButton)).perform(click());


    }


    private void checkCategoriesDisplayOnRecyclerView(List<Category> categories) {
        int size = categories.size();
        for (int i = 0; i < size; i++) {
            onView(withId(R.id.recycler_categories))
                    .perform(RecyclerViewActions.scrollToPosition(i));
            checkCategoryDisplays(categories.get(i));
        }
    }

    private void checkCategoryDisplays(Category category) {
        onView(withText(category.getName()))
                .check(matches(isDisplayed()));
    }

    private void checkProductsDisplayOnRecyclerView(List<Product> products) {
        int size = products.size();
        for (int i = 0; i < size; i++) {
            onView(withId(R.id.recycler_products))
                    .perform(RecyclerViewActions.scrollToPosition(i));
            checkProductDisplays(products.get(i));
        }
    }

    private void checkProductDisplays(Product product) {
        onView(withText(product.getName()))
                .check(matches(isDisplayed()));
    }


}
