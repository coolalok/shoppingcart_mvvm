package com.assignment.shoppingcart.testUtils;

import com.assignment.shoppingcart.model.Product;

import java.util.ArrayList;
import java.util.UUID;

public class MockModelsUtil {


    public static String generateRandomString() {
        return UUID.randomUUID().toString();
    }


    public static Product createMockProduct() {
        Product product = new Product();
        product.setId(generateRandomString());
        product.setImage("file:///android_asset/electronics/television.jpg");
        product.setName("Panasonic Vierra");
        product.setPrice(40000F);
        return product;
    }

    public static Product createMockProduct(String randomID, String productName, Float price, String imagePath) {
        Product product = new Product();
        product.setId(randomID);
        product.setImage(imagePath);
        product.setName(productName);
        product.setPrice(price);
        return product;
    }

    public static ArrayList<Product> createMockProductList() {
        Product product1 = createMockProduct(generateRandomString(), "Panasonic Vierra", 40000F, "file:///android_asset/electronics/television.jpg");
        Product product2 = createMockProduct(generateRandomString(), "Samsung Microwave", 10000F, "file:///android_asset/electronics/microwave.jpg");
        ArrayList<Product> arrProducts = new ArrayList<>();
        arrProducts.add(product1);
        arrProducts.add(product2);
        return arrProducts;
    }

    public static String getTestCartData() {
        return " \"products\": [\n" +
                "                {\n" +
                "                    \"id\": \"E_001\",\n" +
                "                    \"name\": \"Television\",\n" +
                "                    \"price\": 10000,\n" +
                "                    \"image\": \"file:///android_asset/electronics/television.jpg\"\n" +
                "                }]";
    }
}