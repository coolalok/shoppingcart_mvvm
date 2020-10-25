package com.assignment.shoppingcart.view.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;

import com.assignment.shoppingcart.R;
import com.assignment.shoppingcart.databinding.ActivityCartBinding;
import com.assignment.shoppingcart.view.fragment.CartItemsFragment;
import com.assignment.shoppingcart.viewModel.activity.CartActivityViewModel;

/**
 * Displays the user's current cart
 */
public class CartActivity extends BaseActivity {
    private ActivityCartBinding mBinding;
    private CartActivityViewModel viewModel;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, CartActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_cart);
        viewModel = new CartActivityViewModel(this);
        mBinding.setViewModel(viewModel);
        setSupportActionBar(mBinding.toolbar);
        setupToolbar();
        if (savedInstanceState == null)
            addCartFragment();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    private void setupToolbar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setTitle(R.string.cart_title);
            actionBar.setDisplayHomeAsUpEnabled(true);

        }
    }

    private void addCartFragment() {
        Fragment cartItemsFragment = CartItemsFragment.newInstance();
        addFragment(cartItemsFragment);
    }


    private void addFragment(Fragment fragment) {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, fragment).addToBackStack(null)
                .commit();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
