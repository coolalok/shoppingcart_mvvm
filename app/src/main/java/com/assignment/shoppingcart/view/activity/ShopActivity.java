package com.assignment.shoppingcart.view.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;

import com.assignment.shoppingcart.R;
import com.assignment.shoppingcart.databinding.ActivityShopBinding;
import com.assignment.shoppingcart.events.CategorySelectEvent;
import com.assignment.shoppingcart.events.ProductSelectEvent;
import com.assignment.shoppingcart.model.Category;
import com.assignment.shoppingcart.model.Product;
import com.assignment.shoppingcart.view.fragment.CategoriesHomeFragment;
import com.assignment.shoppingcart.view.fragment.ProductDetailFragment;
import com.assignment.shoppingcart.view.fragment.ProductsFragment;
import com.assignment.shoppingcart.viewModel.activity.ShopActivityViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


public class ShopActivity extends BaseActivity {

    public static final String EXTRA_PRODUCT_FRAGMENT = "PRODUCT_FRAGMENT";

    public static final String EXTRA_SCREEN_NAME = "EXTRA_SCREEN_NAME";
    public static final String EXTRA_SCREEN_DATA = "EXTRA_SCREEN_DATA";

    private ActivityShopBinding mBinding;
    private ShopActivityViewModel viewModel;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, ShopActivity.class);
        return intent;
    }

    public static Intent getProductStartIntent(Context context, Parcelable parcelable) {
        Intent intent = new Intent(context, ShopActivity.class);
        intent.putExtra(EXTRA_SCREEN_NAME, EXTRA_PRODUCT_FRAGMENT);
        intent.putExtra(EXTRA_SCREEN_DATA, parcelable);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_shop);
        viewModel = new ShopActivityViewModel(this);
        mBinding.setViewModel(viewModel);
        setSupportActionBar(mBinding.toolbar);
        setupToolbar();

        Intent intent = getIntent();
        String screenName = intent.getStringExtra(EXTRA_SCREEN_NAME);

        if (screenName != null && screenName.equals(EXTRA_PRODUCT_FRAGMENT)) {
            Parcelable parcelable = intent.getParcelableExtra(EXTRA_SCREEN_DATA);
            Product product = (Product) parcelable;
            addProductDetailFragment(product);
        } else if (savedInstanceState == null)
            addCategoriesFragment();

    }


    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shop, menu);
        return true;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.destroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_cart) {
            addCartActivity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickCart() {
        addCartActivity();
    }

    private void setupToolbar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setTitle(R.string.app_title);
            actionBar.setDisplayHomeAsUpEnabled(true);

        }
    }

    @Subscribe
    public void onEvent(CategorySelectEvent event) {
        Category category = event.getCategory();
        addProductsFragment(category);
    }


    @Subscribe
    public void onEvent(ProductSelectEvent event) {
        Product product = event.getProduct();
        addProductDetailFragment(product);
    }

    private void addCategoriesFragment() {
        Fragment categoriesHomeFragment = CategoriesHomeFragment.newInstance();
        addFragment(categoriesHomeFragment);
    }

    private void addProductsFragment(Category category) {
        Fragment productsFragment = ProductsFragment.newInstance(category);
        addFragment(productsFragment);
    }

    private void addProductDetailFragment(Product product) {
        Fragment productDetailFragment = ProductDetailFragment.newInstance(product);
        addFragment(productDetailFragment);
    }

    private void addCartActivity() {
        startActivity(CartActivity.getStartIntent(this));
    }

    private void addFragment(Fragment fragment) {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, fragment).addToBackStack(null)
                .commit();
    }


}
