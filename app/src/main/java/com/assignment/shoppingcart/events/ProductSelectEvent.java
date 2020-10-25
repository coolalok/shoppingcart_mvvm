package com.assignment.shoppingcart.events;


import com.assignment.shoppingcart.model.Product;

/**
 * Created by Alok.Kulkarni on 4/30/2016.
 */
public class ProductSelectEvent {


    private Product product;

    public ProductSelectEvent(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }


}
