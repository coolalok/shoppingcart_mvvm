package com.assignment.shoppingcart.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alok.Kulkarni on 4/26/2016.
 */
public class Category implements Parcelable {

    private String id;
    private String name;
    private String image;
    private List<Product> products;

    //Dummy Constuctor for avoid Json Parsing error
    public Category() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.image);
        dest.writeTypedList(this.products);

    }

    private Category(Parcel in) {

        this.id = in.readString();
        this.name = in.readString();
        this.image = in.readString();
        this.products = new ArrayList<>();
        in.readTypedList(this.products, Product.CREATOR);

    }

    public static final Parcelable.Creator<Category> CREATOR = new Parcelable.Creator<Category>() {
        public Category createFromParcel(Parcel source) {
            return new Category(source);
        }

        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

}
