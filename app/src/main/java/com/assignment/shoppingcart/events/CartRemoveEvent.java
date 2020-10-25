package com.assignment.shoppingcart.events;

import com.assignment.shoppingcart.model.Product;

/**
 * Created by Alok.Kulkarni on 5/2/2016.
 */
public class CartRemoveEvent {
    private final Product mProduct;

    public CartRemoveEvent(Product product) {
        this.mProduct = product;
    }

    public Product getProduct() {
        return mProduct;
    }
}
