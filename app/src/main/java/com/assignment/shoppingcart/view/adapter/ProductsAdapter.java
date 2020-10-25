package com.assignment.shoppingcart.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.assignment.shoppingcart.R;
import com.assignment.shoppingcart.databinding.ItemProductBinding;
import com.assignment.shoppingcart.model.Product;
import com.assignment.shoppingcart.viewModel.fragment.ProductViewModel;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Alok.Kulkarni on 4/26/2016.
 */
public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.BindingHolder> {
    private List<Product> mProducts;
    private Context mContext;

    public ProductsAdapter(Context context) {
        mContext = context;
        mProducts = new ArrayList<>();
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemProductBinding productBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_product,
                parent,
                false);
        return new BindingHolder(productBinding);
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        ItemProductBinding productBinding = holder.binding;
        productBinding.setViewModel(new ProductViewModel(mContext, mProducts.get(position)));
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public void setItems(List<Product> products) {
        mProducts = products;
        notifyDataSetChanged();
    }

    public void addItem(Product product) {
        if (!mProducts.contains(product)) {
            mProducts.add(product);
            notifyItemInserted(mProducts.size() - 1);
        } else {
            mProducts.set(mProducts.indexOf(product), product);
            notifyItemChanged(mProducts.indexOf(product));
        }
    }

    public static class BindingHolder extends RecyclerView.ViewHolder {
        private ItemProductBinding binding;

        public BindingHolder(ItemProductBinding binding) {
            super(binding.root);
            this.binding = binding;
        }
    }
}
