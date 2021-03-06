package com.assignment.shoppingcart.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.assignment.shoppingcart.R;
import com.assignment.shoppingcart.data.cart.CartManager;
import com.assignment.shoppingcart.databinding.FragmentCartBinding;
import com.assignment.shoppingcart.events.CartLoadEvent;
import com.assignment.shoppingcart.events.CartRemoveEvent;
import com.assignment.shoppingcart.view.adapter.CartAdapter;
import com.assignment.shoppingcart.viewModel.fragment.CartViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Fragment that loads the Cart data
 */
public class CartItemsFragment extends Fragment {

    RecyclerView mListProducts;

    private CartAdapter cartAdapter;
    private FragmentCartBinding binding;
    private CartViewModel viewModel;

    public static CartItemsFragment newInstance() {
        CartItemsFragment productsHomeFragment = new CartItemsFragment();
        Bundle args = new Bundle();
        productsHomeFragment.setArguments(args);
        return productsHomeFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cartAdapter = new CartAdapter(getActivity());
        EventBus.getDefault().register(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false);
        View rootView = binding.getRoot();
        mListProducts = binding.recyclerProducts;
        viewModel = new CartViewModel();
        binding.setViewModel(viewModel);
        setupRecyclerView();
        return rootView;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        viewModel.destroy();
        EventBus.getDefault().unregister(this);
    }


    private void setupRecyclerView() {
        mListProducts.setLayoutManager(new LinearLayoutManager(getActivity()));
        mListProducts.setAdapter(cartAdapter);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(CartLoadEvent event) {
        cartAdapter.setItems(CartManager.getInstance().getCartProducts());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(CartRemoveEvent event) {

        cartAdapter.setItems(CartManager.getInstance().getCartProducts());
    }


}
