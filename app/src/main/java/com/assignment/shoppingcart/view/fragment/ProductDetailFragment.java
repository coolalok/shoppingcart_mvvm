package com.assignment.shoppingcart.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.assignment.shoppingcart.R;
import com.assignment.shoppingcart.databinding.FragmentProductDetailsBinding;
import com.assignment.shoppingcart.events.CartAddEvent;
import com.assignment.shoppingcart.model.Product;
import com.assignment.shoppingcart.viewModel.fragment.ProductDetailViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Alok.Kulkarni on 4/26/2016.
 */
public class ProductDetailFragment extends Fragment {


    public static final String ARG_PRODUCT = "ARG_PRODUCT";
    private Product mProduct;
    private View rootView;
    private FragmentProductDetailsBinding mBinding;
    private ProductDetailViewModel viewModel;

    public static ProductDetailFragment newInstance(Product product) {
        ProductDetailFragment productDetailFragment = new ProductDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PRODUCT, product);
        productDetailFragment.setArguments(args);
        return productDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null)
            mProduct = bundle.getParcelable(ARG_PRODUCT);
        EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_details, container, false);
        rootView = mBinding.getRoot();
        viewModel = new ProductDetailViewModel(getActivity(), mProduct);
        mBinding.setViewModel(viewModel);
        return rootView;
    }

    //ToDo Create a separate event to show Snackbar and fire that event from ViewModel
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(CartAddEvent event) {
        Snackbar.make(rootView, R.string.product_added_to_cart_text, Snackbar.LENGTH_SHORT).show();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
