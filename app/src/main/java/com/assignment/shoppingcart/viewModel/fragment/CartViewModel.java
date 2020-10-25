package com.assignment.shoppingcart.viewModel.fragment;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.assignment.shoppingcart.BR;
import com.assignment.shoppingcart.data.cart.CartManager;
import com.assignment.shoppingcart.events.CartAddEvent;
import com.assignment.shoppingcart.events.CartLoadEvent;
import com.assignment.shoppingcart.events.CartRemoveEvent;
import com.assignment.shoppingcart.viewModel.ViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Alok.Kulkarni on 4/28/2016.
 */
public class CartViewModel extends BaseObservable implements ViewModel {
    private Float totalPrice;

    public CartViewModel(Context context) {
        EventBus.getDefault().register(this);
        CartManager.getInstance().loadCart();
    }


    @Bindable
    public Float getTotalPrice() {
        return CartManager.getInstance().getTotalPrice();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(CartAddEvent event) {
        notifyPropertyChanged(BR.totalPrice);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(CartRemoveEvent event) {
        notifyPropertyChanged(BR.totalPrice);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(CartLoadEvent event) {
        notifyPropertyChanged(BR.totalPrice);
    }


    @Override
    public void destroy() {
        EventBus.getDefault().unregister(this);
    }
}
