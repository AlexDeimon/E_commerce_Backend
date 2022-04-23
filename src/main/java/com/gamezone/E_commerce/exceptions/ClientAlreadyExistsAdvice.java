package com.gamezone.E_commerce.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody

public class ClientAlreadyExistsAdvice {
    @ResponseBody
    @ExceptionHandler(ClientAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    String ClientAlreadyExists(ClientAlreadyExistsException ex){
        return ex.getMessage();
    }
}
