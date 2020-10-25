package com.assignment.shoppingcart.webservice;

/**
 * Interface for web-services
 */
public interface IWebService {
    <T> T execute() throws Exception;
}
