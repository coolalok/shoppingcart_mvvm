package com.assignment.shoppingcart;

import android.content.Context;
import android.test.AndroidTestCase;
import android.test.mock.MockContext;

import com.assignment.shoppingcart.model.Product;
import com.assignment.shoppingcart.utils.MockModelsUtil;
import com.assignment.shoppingcart.viewModel.fragment.ProductViewModel;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNotSame;

/**
 * Created by Alok.Kulkarni on 5/2/2016.
 */


public class ProductViewModelTest {

    private Context mContext;
    private ProductViewModel mProductViewModel;
    private Product mProduct;

    @Before
    public void setUp() throws Exception {
        mContext = new MockContext();
        mProduct = MockModelsUtil.createMockProduct();
        mProductViewModel = new ProductViewModel(mContext, mProduct);
    }

    @Test
    public void shouldGetProductName() throws Exception {
        assertEquals(mProductViewModel.getProductName(), mProduct.getName());
    }

    @Test
    public void shouldGetProductImageUrl() throws Exception {
        assertEquals(mProductViewModel.getImageUrl(), mProduct.getImage());
    }

    @Test
    public void shouldGetProductPrice() throws Exception {
        assertEquals(mProductViewModel.getPrice(), mProduct.getPrice());
    }


}
