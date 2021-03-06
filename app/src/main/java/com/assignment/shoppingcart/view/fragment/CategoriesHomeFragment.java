package com.assignment.shoppingcart.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.assignment.shoppingcart.R;
import com.assignment.shoppingcart.ShoppingCartApplication;
import com.assignment.shoppingcart.data.catalogue.DataManager;
import com.assignment.shoppingcart.events.ShoppingDataReceivedEvent;
import com.assignment.shoppingcart.model.Category;
import com.assignment.shoppingcart.model.ShoppingData;
import com.assignment.shoppingcart.view.adapter.CategoriesAdapter;
import com.assignment.shoppingcart.webservice.ErrorCode;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Fragment that displays list of Categories. Uses DataManager to get the list of Categories
 */
public class CategoriesHomeFragment extends Fragment {

    @Bind(R.id.recycler_categories)
    RecyclerView mListCategorys;
    private CategoriesAdapter mCategoriesAdapter;
    private List<Category> mCategories;
    private DataManager mDataManager;

    public static CategoriesHomeFragment newInstance() {
        CategoriesHomeFragment categoriesHomeFragment = new CategoriesHomeFragment();
        Bundle args = new Bundle();
        categoriesHomeFragment.setArguments(args);
        return categoriesHomeFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mCategories = new ArrayList<>();
            Bundle bundle = getArguments();
            mCategoriesAdapter = new CategoriesAdapter(getActivity());
            EventBus.getDefault().register(this);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View fragmentView = inflater.inflate(R.layout.fragment_categories, container, false);
            ButterKnife.bind(this, fragmentView);
            setupRecyclerView();
            return fragmentView;
        }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDataManager = ShoppingCartApplication.get(getActivity()).getComponent().dataManager();
        mDataManager.getShoppingData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ShoppingDataReceivedEvent event) {
        int errorCode = event.getErrorCode();
        if (errorCode == ErrorCode.ERROR_NONE) {
            onDataReceived(event.getShoppingData());
        } else {
            //ToDo Show Error
        }
    }

    private void onDataReceived(ShoppingData shoppingData) {

        List<Category> categories = shoppingData.getCategory();
        mCategoriesAdapter.setItems(categories);
    }


    private void setupRecyclerView() {
        mListCategorys.setLayoutManager(new LinearLayoutManager(getActivity()));
        mListCategorys.setHasFixedSize(true);
        mCategoriesAdapter.setItems(mCategories);
        mListCategorys.setAdapter(mCategoriesAdapter);
        mListCategorys.setItemAnimator(new DefaultItemAnimator());
    }

}
