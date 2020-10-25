package com.assignment.shoppingcart.testUtils;

import com.assignment.shoppingcart.model.Category;
import com.assignment.shoppingcart.model.Product;
import com.assignment.shoppingcart.model.ShoppingData;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Utility class for mocking Models for Android tests
 */
public class MockModelsUtilAndroidTest {
    public static String generateRandomString() {
        return UUID.randomUUID().toString();
    }

    public static ArrayList<Product> createMockProductList() {
        Product product1 = createMockProduct(generateRandomString(), "Panasonic Vierra", 40000F, "file:///android_asset/electronics/television.jpg");
        Product product2 = createMockProduct(generateRandomString(), "Samsung Microwave", 10000F, "file:///android_asset/electronics/microwave.jpg");
        ArrayList<Product> arrProducts = new ArrayList<>();
        arrProducts.add(product1);
        arrProducts.add(product2);
        return arrProducts;
    }

    public static Product createMockProduct(String randomID, String productName, Float price, String imagePath) {
        Product product = new Product();
        product.setId(randomID);
        product.setImage(imagePath);
        product.setName(productName);
        product.setPrice(price);
        return product;
    }

    public static Category createMockCategory(String id,
                                              String name,
                                              String image,
                                              List<Product> products) {
        Category category = new Category();
        category.setId(id);
        category.setImage(image);
        category.setName(name);
        category.setProducts(products);
        return category;
    }


    public static ShoppingData getMockShoppingData() {
        ShoppingData data = new ShoppingData();
        Category categoryOne = createMockCategory(generateRandomString(), "Electronics", "file:///android_asset/electronics/electronics.jpg", createMockProductList());
        Category categoryTwo = createMockCategory(generateRandomString(), "Furniture", "file:///android_asset/electronics/furniture.jpg", createMockProductList());
        ArrayList<Category> arrCategories = new ArrayList<Category>();
        arrCategories.add(categoryOne);
        arrCategories.add(categoryTwo);

        ShoppingData shoppingData = new ShoppingData();
        shoppingData.setCategory(arrCategories);
        return shoppingData;
    }
}
