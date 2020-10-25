package com.assignment.shoppingcart.viewModel.fragment;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.assignment.shoppingcart.data.cart.CartManager;
import com.assignment.shoppingcart.model.Product;
import com.assignment.shoppingcart.utils.ImageLoadHelper;
import com.assignment.shoppingcart.view.activity.ShopActivity;

/**
 * Created by Alok.Kulkarni on 4/29/2016.
 */
public class CartItemViewModel {


    private Product mProduct;
    private final Context mContext;

    public CartItemViewModel(Context context, Product product) {
        this.mProduct = product;
        this.mContext = context;
    }

    public float getPrice() {
        return mProduct.getPrice();
    }

    public String getProductName() {
        return mProduct.getName();
    }

    public String getImageUrl() {
        return mProduct.getImage();
    }


    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        ImageLoadHelper.loadImage(view, imageUrl);
    }

    public View.OnClickListener onClickItem() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(ShopActivity.getProductStartIntent(mContext, mProduct));
            }
        };
    }

    public View.OnClickListener onClickRemoveItem() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartManager.getInstance().removeProduct(mProduct);

            }
        };
    }

}
