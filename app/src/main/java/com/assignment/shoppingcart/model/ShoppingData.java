package com.assignment.shoppingcart.model;

import java.util.List;

/**
 * Model class for the Shopping data that is received from Web service
 */
public class ShoppingData {

    private List<Category> categories;

    public List<Category> getCategory() {
        return categories;
    }

    public void setCategory(List<Category> category) {
        this.categories = category;
    }
}
