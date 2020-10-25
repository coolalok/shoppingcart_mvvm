package com.assignment.shoppingcart.model;

import java.util.List;

/**
 * Created by Alok.Kulkarni on 4/26/2016.
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
