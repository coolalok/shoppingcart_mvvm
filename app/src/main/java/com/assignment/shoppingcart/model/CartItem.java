package com.assignment.shoppingcart.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Class that acts a wrapper over Product to add the quantity feature
 */
//ToDo Use this class instead of Product for handling quantity
public class CartItem implements Parcelable {


    private Product mProduct;
    private int mQuantity;

    public Product getProduct() {
        return mProduct;
    }

    public void setProduct(Product product) {
        this.mProduct = product;
    }

    public int getQuantity() {
        return mQuantity;
    }

    public void setQuantity(int quantity) {
        this.mQuantity = mQuantity;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(mProduct, 0);
        dest.writeInt(mQuantity);

    }

    private CartItem(Parcel in) {
        mProduct = in.readParcelable(mProduct.getClass().getClassLoader());
        mQuantity = in.readInt();
    }

    public static final Parcelable.Creator<CartItem> CREATOR = new Parcelable.Creator<CartItem>() {
        public CartItem createFromParcel(Parcel source) {
            return new CartItem(source);
        }

        public CartItem[] newArray(int size) {
            return new CartItem[size];
        }
    };
}
