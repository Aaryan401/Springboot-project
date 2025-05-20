package com.Example.Product_Details.Exception;

import jakarta.persistence.GeneratedValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.Example.Product_Details.Exception.CustomException.*;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(productNotFoundById.class)
    public ResponseEntity<?> handleProductNotFound(productNotFoundById exception, WebRequest webRequest){

        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

}
