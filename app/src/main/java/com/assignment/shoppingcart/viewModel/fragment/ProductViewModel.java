package com.assignment.shoppingcart.viewModel.fragment;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.assignment.shoppingcart.events.ProductSelectEvent;
import com.assignment.shoppingcart.model.Product;
import com.assignment.shoppingcart.utils.ImageLoadHelper;
import com.assignment.shoppingcart.viewModel.ViewModel;

import org.greenrobot.eventbus.EventBus;

/**
 * ViewModel class for a Product
 */
public class ProductViewModel extends BaseObservable implements ViewModel {
    private Product mProduct;

    public ProductViewModel(Product product) {
        this.mProduct = product;
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        ImageLoadHelper.loadImage(view, imageUrl);
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

    @Override
    public void destroy() {

    }
}
