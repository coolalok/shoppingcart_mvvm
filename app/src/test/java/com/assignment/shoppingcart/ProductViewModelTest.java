package com.assignment.shoppingcart;

import com.assignment.shoppingcart.model.Product;
import com.assignment.shoppingcart.testUtils.MockModelsUtil;
import com.assignment.shoppingcart.viewModel.fragment.ProductViewModel;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Alok.Kulkarni on 5/2/2016.
 */


public class ProductViewModelTest {

    private ProductViewModel mProductViewModel;
    private Product mProduct;

    @Before
    public void setUp() throws Exception {
        mProduct = MockModelsUtil.createMockProduct();
        mProductViewModel = new ProductViewModel(mProduct);
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
