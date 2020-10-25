package com.assignment.shoppingcart.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.assignment.shoppingcart.R;
import com.assignment.shoppingcart.model.Category;
import com.assignment.shoppingcart.model.Product;
import com.assignment.shoppingcart.view.adapter.ProductsAdapter;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Alok.Kulkarni on 4/26/2016.
 */
public class ProductsFragment extends Fragment {

    @Bind(R.id.recycler_products)
    RecyclerView mListProducts;


    public static final String ARG_CATEGORY = "ARG_CATEGORY";

    private ProductsAdapter mProductsAdapter;
    private Category mCategory;

    public static ProductsFragment newInstance(Category category) {
        ProductsFragment productsHomeFragment = new ProductsFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_CATEGORY, category);
        productsHomeFragment.setArguments(args);
        return productsHomeFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null)
            mCategory = bundle.getParcelable(ARG_CATEGORY);
        mProductsAdapter = new ProductsAdapter(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_products, container, false);
        ButterKnife.bind(this, fragmentView);
        setupRecyclerView();
        loadProducts();

        return fragmentView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void loadProducts() {
        if (mCategory == null) {
            //ToDo Handle error
            return;
        }
        List<Product> products = mCategory.getProducts();
        mProductsAdapter.setItems(products);
    }


    private void setupRecyclerView() {
        mListProducts.setLayoutManager(new LinearLayoutManager(getActivity()));
        mListProducts.setHasFixedSize(true);
        mListProducts.setAdapter(mProductsAdapter);

    }


}
