package com.gamezone.E_commerce.exceptions;

public class ClientNotExistsException extends RuntimeException{
    public ClientNotExistsException(String message) {
        super(message);
    }
}
