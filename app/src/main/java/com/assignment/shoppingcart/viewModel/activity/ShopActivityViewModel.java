package com.assignment.shoppingcart.viewModel.activity;

import android.content.Context;
import android.databinding.BaseObservable;

import com.assignment.shoppingcart.data.cart.CartManager;
import com.assignment.shoppingcart.viewModel.ViewModel;

/**
 * Created by Alok.Kulkarni on 5/2/2016.
 */
public class ShopActivityViewModel extends BaseObservable implements ViewModel {
    private final Context mContext;

    public ShopActivityViewModel(Context context) {
        this.mContext = context;
        CartManager.getInstance().loadCart();
    }


    @Override
    public void destroy() {

    }
}
