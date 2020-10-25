package com.assignment.shoppingcart.utils;

import android.widget.ImageView;

import com.assignment.shoppingcart.R;
import com.squareup.picasso.Picasso;

/**
 * Utility class for handling Image display. Acts as a wrapper over Image load library
 */
//ToDo Create an inteface for Image Loader. Create a class to handle image loading using Picasso.
public class ImageLoadHelper {
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.with(view.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.cart)
                .into(view);
    }
}
