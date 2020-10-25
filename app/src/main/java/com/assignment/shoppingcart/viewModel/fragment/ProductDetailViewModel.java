package com.assignment.shoppingcart.viewModel.fragment;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.ImageView;

import com.assignment.shoppingcart.R;
import com.assignment.shoppingcart.data.cart.CartManager;
import com.assignment.shoppingcart.events.CartAddEvent;
import com.assignment.shoppingcart.events.CartRemoveEvent;
import com.assignment.shoppingcart.model.Product;
import com.assignment.shoppingcart.utils.ImageLoadHelper;
import com.assignment.shoppingcart.view.activity.CartActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Alok.Kulkarni on 4/28/2016.
 */
public class ProductDetailViewModel extends BaseObservable {
    private final String mStrGoToCart;
    private final String mStrAddToCart;
    private final String mStrAddingToCart;
    private Product mProduct;

    private Context mContext;
    public ObservableField<String> cartButtonText;
    public ObservableBoolean isAdded;
    private boolean bAddingToCart;

    public ProductDetailViewModel(Context context, Product product) {
        this.mContext = context;
        this.mProduct = product;
        EventBus.getDefault().register(this);
        mStrGoToCart = mContext.getResources().getString(R.string.go_to_cart_text);
        mStrAddToCart = mContext.getResources().getString(R.string.add_to_cart_text);
        mStrAddingToCart = mContext.getResources().getString(R.string.adding_to_cart_text);
        cartButtonText = new ObservableField<String>();
        isAdded = new ObservableBoolean();
        refreshViews();
    }

    /**
     * Refresh screen data
     */
    private void refreshViews() {
        isAdded.set(CartManager.getInstance().checkProductInCart(mProduct));
        cartButtonText.set(isAdded.get() ? mStrGoToCart : mStrAddToCart);
    }

    public String getProductName() {
        return mProduct.getName();
    }

    public Float getPrice() {
        return mProduct.getPrice();
    }


    public String getImageUrl() {
        return mProduct.getImage();
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        ImageLoadHelper.loadImage(view, imageUrl);
    }

    public View.OnClickListener onClickAddItem() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bAddingToCart)
                    return;
                bAddingToCart = true;
                cartButtonText.set(mStrAddingToCart);
                new Thread() {
                    public void run() {
                        CartManager.getInstance().addProduct(mProduct);
                    }
                }.start();

            }
        };
    }

    public View.OnClickListener onClickViewCart() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(CartActivity.getStartIntent(mContext));
            }
        };
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(CartAddEvent event) {
        bAddingToCart = false;
        cartButtonText.set(mStrGoToCart);
        isAdded.set(true);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(CartRemoveEvent event) {
        Product eProduct = event.getProduct();
        if (mProduct.equals(eProduct)) {
            cartButtonText.set(mStrAddToCart);
            isAdded.set(false);
        }

    }

}
