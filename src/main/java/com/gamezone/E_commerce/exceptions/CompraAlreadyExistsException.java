package com.gamezone.E_commerce.exceptions;

public class CompraAlreadyExistsException extends RuntimeException{
    public CompraAlreadyExistsException(String message) {
        super(message);
    }
}
