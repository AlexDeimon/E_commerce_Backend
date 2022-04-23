package com.gamezone.E_commerce.exceptions;

public class ProductNotExistsException extends RuntimeException{
    public ProductNotExistsException(String message) {
        super(message);
    }
}
