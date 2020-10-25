package com.assignment.shoppingcart.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.assignment.shoppingcart.R;
import com.assignment.shoppingcart.databinding.ItemCategoryBinding;
import com.assignment.shoppingcart.model.Category;
import com.assignment.shoppingcart.viewModel.fragment.CategoryViewModel;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Alok.Kulkarni on 4/26/2016.
 */
public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.BindingHolder> {
    private List<Category> mCategories;
    private Context mContext;

    public CategoriesAdapter(Context context) {
        mContext = context;
        mCategories = new ArrayList<>();
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemCategoryBinding categoryBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_category,
                parent,
                false);
        return new BindingHolder(categoryBinding);
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        ItemCategoryBinding categoryBinding = holder.binding;
        categoryBinding.setViewModel(new CategoryViewModel(mContext, mCategories.get(position)));
    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }

    public void setItems(List<Category> categories) {
        mCategories = categories;
        notifyDataSetChanged();
    }

    public void addItem(Category category) {
        if (!mCategories.contains(category)) {
            mCategories.add(category);
            notifyItemInserted(mCategories.size() - 1);
        } else {
            mCategories.set(mCategories.indexOf(category), category);
            notifyItemChanged(mCategories.indexOf(category));
        }
    }

    public static class BindingHolder extends RecyclerView.ViewHolder {
        private ItemCategoryBinding binding;

        public BindingHolder(ItemCategoryBinding binding) {
            super(binding.root);
            this.binding = binding;
        }
    }
}
