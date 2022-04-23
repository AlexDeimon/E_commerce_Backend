package com.gamezone.E_commerce.exceptions;

public class CategoryNotExistsException extends RuntimeException{
    public CategoryNotExistsException(String message) {
        super(message);
    }
}
