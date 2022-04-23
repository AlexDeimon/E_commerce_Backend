package com.gamezone.E_commerce.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody

public class ProductAlreadyExistAdvice {
    @ResponseBody
    @ExceptionHandler(ProductAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    String ProductAlreadyExist(ProductAlreadyExistsException ex){
        return ex.getMessage();
    }
}
