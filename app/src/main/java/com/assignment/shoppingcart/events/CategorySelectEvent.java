package com.assignment.shoppingcart.events;

import com.assignment.shoppingcart.model.Category;

/**
 * Created by Alok.Kulkarni on 4/30/2016.
 */
public class CategorySelectEvent {


    private Category category;

    public CategorySelectEvent(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }


}
