package com.assignment.shoppingcart.data.cart;

import com.assignment.shoppingcart.exceptions.InvalidPriceException;
import com.assignment.shoppingcart.model.Product;
import com.assignment.shoppingcart.utils.JsonHelper;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Core class for handling Cart operations.
 */
//This class should only be accessible from current package. Also it cannot be inherited
final class Cart {

    //ToDo Use CartItem instead of Product
    private ArrayList<Product> mCartProducts = new ArrayList<Product>();


    ArrayList<Product> getCartProducts() {
        return mCartProducts;
    }


    void setCartProducts(ArrayList<Product> mCartProducts) {
        this.mCartProducts = mCartProducts;
    }

    Product getProduct(int position) {

        return mCartProducts != null ? mCartProducts.get(position) : null;
    }

    boolean addProduct(Product product) throws InvalidPriceException {
        if (mCartProducts != null && !mCartProducts.contains(product)) {
            if (product.getPrice() < 0) {
                throw new InvalidPriceException(InvalidPriceException.NEGATIVE_NUMBER_MESSAGE);
            } else {
                return mCartProducts.add(product);
            }
        }
        return false;
    }

    boolean removeProduct(Product product) {

        return mCartProducts != null ? mCartProducts.remove(product) : false;

    }

    int getCartSize() {

        return mCartProducts != null ? mCartProducts.size() : 0;

    }

    boolean checkProductInCart(Product aProduct) {

        return mCartProducts != null ? mCartProducts.contains(aProduct) : false;

    }

    String saveCart() {
        int size = 0;
        if (mCartProducts != null && (size = mCartProducts.size()) > 0) {
            try {
                return JsonHelper.getInstance().toJsonString(this);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    void loadCart(String jsonCart) {
        try {
            Cart cartObject = JsonHelper.getInstance().fromJsonString(jsonCart, Cart.class);
            setCartProducts(cartObject.getCartProducts());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    Float calculateCartPrice() {
        Float price = 0F;
        if (mCartProducts == null)
            return price;

        int size = mCartProducts.size();
        for (int i = 0; i < size; i++) {
            price += mCartProducts.get(i).getPrice();
        }
        return price;

    }

}