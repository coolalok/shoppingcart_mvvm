package com.assignment.shoppingcart.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.assignment.shoppingcart.R;
import com.assignment.shoppingcart.model.Category;
import com.assignment.shoppingcart.model.Product;
import com.assignment.shoppingcart.view.adapter.ProductsAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Fragment that displays a list of products. Pass the selected Category to this Fragment
 */
public class ProductItemsFragment extends Fragment {

    public static final String ARG_CATEGORY = "ARG_CATEGORY";
    @Bind(R.id.recycler_products)
    RecyclerView mListProducts;
    private ProductsAdapter mProductsAdapter;
    private Category mCategory;

    public static ProductItemsFragment newInstance(Category category) {
        ProductItemsFragment productsHomeFragment = new ProductItemsFragment();
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
        mProductsAdapter = new ProductsAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_products, container, false);
        ButterKnife.bind(this, fragmentView);
        setupRecyclerView();
        loadProducts();

        return fragmentView;
    }

    private void setupRecyclerView() {
        mListProducts.setLayoutManager(new LinearLayoutManager(getActivity()));
        mListProducts.setHasFixedSize(true);
        mListProducts.setAdapter(mProductsAdapter);

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


}
