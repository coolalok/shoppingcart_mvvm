package com.assignment.shoppingcart.viewModel.fragment;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.assignment.shoppingcart.events.CategorySelectEvent;
import com.assignment.shoppingcart.model.Category;
import com.assignment.shoppingcart.utils.ImageLoadHelper;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Alok.Kulkarni on 4/28/2016.
 */
public class CategoryViewModel extends BaseObservable {
    private final Context mContext;
    private Category mCategory;

    public CategoryViewModel(Context context, Category category) {
        this.mContext = context;
        this.mCategory = category;
    }

    public String getCategoryName() {
        return mCategory.getName();
    }

    public String getImageUrl() {
        return mCategory.getImage();
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        ImageLoadHelper.loadImage(view, imageUrl);
    }

    public View.OnClickListener onClickItem() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchProductsScreen();
            }
        };
    }

    private void launchProductsScreen() {
        EventBus.getDefault().post(new CategorySelectEvent(mCategory));
    }


}
