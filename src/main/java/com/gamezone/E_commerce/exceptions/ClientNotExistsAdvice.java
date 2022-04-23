package com.gamezone.E_commerce.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody

public class ClientNotExistsAdvice {
    @ResponseBody
    @ExceptionHandler(ClientNotExistsException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    String ClientNotExists(ClientNotExistsException ex){
        return ex.getMessage();
    }
}
