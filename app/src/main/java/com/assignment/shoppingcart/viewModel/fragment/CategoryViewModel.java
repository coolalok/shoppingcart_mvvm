package com.assignment.shoppingcart.viewModel.fragment;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.assignment.shoppingcart.events.CategorySelectEvent;
import com.assignment.shoppingcart.model.Category;
import com.assignment.shoppingcart.utils.ImageLoadHelper;
import com.assignment.shoppingcart.viewModel.ViewModel;

import org.greenrobot.eventbus.EventBus;

/**
 * ViewModel class for a Categories
 */
public class CategoryViewModel extends BaseObservable implements ViewModel {
    private Category mCategory;

    public CategoryViewModel(Category category) {
        this.mCategory = category;
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        ImageLoadHelper.loadImage(view, imageUrl);
    }

    public String getCategoryName() {
        return mCategory.getName();
    }

    public String getImageUrl() {
        return mCategory.getImage();
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


    @Override
    public void destroy() {

    }
}
