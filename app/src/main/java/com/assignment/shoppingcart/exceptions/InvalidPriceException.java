package com.assignment.shoppingcart.exceptions;

/**
 * Created by Alok.Kulkarni on 5/1/2016.
 */
public class InvalidPriceException extends Exception {
    public static final String NEGATIVE_NUMBER_MESSAGE = "Price cannot be negative";
    public InvalidPriceException(String message) {
        super(message);
    }
}
