package com.assignment.shoppingcart.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.assignment.shoppingcart.R;
import com.assignment.shoppingcart.databinding.ItemCartBinding;
import com.assignment.shoppingcart.model.Product;
import com.assignment.shoppingcart.viewModel.fragment.CartItemViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


/**
 * Created by Alok.Kulkarni on 4/26/2016.
 */
public class CartAdapter extends RecyclerView.Adapter<CartAdapter.BindingHolder> {
    private List<Product> mProducts;
    private Context mContext;
    @Bind(R.id.coordinatorLayout)
    View coordinatorLayout;

    public CartAdapter(Context context) {
        mContext = context;
        mProducts = new ArrayList<>();
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemCartBinding itemCartBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_cart,
                parent,
                false);
        return new BindingHolder(itemCartBinding);
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        ItemCartBinding productBinding = holder.binding;
        productBinding.setViewModel(new CartItemViewModel(mContext, mProducts.get(position)));
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
        private ItemCartBinding binding;

        public BindingHolder(ItemCartBinding binding) {
            super(binding.root);
            this.binding = binding;
        }
    }
}
