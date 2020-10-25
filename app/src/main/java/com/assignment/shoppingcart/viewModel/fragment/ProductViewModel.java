package com.assignment.shoppingcart.viewModel.fragment;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.assignment.shoppingcart.events.ProductSelectEvent;
import com.assignment.shoppingcart.model.Product;
import com.assignment.shoppingcart.utils.ImageLoadHelper;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Alok.Kulkarni on 4/28/2016.
 */
public class ProductViewModel extends BaseObservable {
    private Product mProduct;
    private Context mContext;

    public ProductViewModel(Context context, Product product) {
        this.mContext = context;
        this.mProduct = product;
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

    public View.OnClickListener onClickItem() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchProductScreen();
            }
        };
    }

    private void launchProductScreen() {
        EventBus.getDefault().post(new ProductSelectEvent(mProduct));
    }

}
