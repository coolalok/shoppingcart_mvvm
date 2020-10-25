package com.assignment.shoppingcart.viewModel.activity;

import android.content.Context;
import android.databinding.BaseObservable;

import com.assignment.shoppingcart.viewModel.ViewModel;

/**
 * Created by Alok.Kulkarni on 5/2/2016.
 */
public class CartActivityViewModel extends BaseObservable implements ViewModel {
    private final Context mContext;

    public CartActivityViewModel(Context context) {
        this.mContext = context;
    }


    @Override
    public void destroy() {

    }
}
