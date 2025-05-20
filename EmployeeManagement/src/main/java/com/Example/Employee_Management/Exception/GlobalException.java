package com.Example.Employee_Management.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.Example.Employee_Management.Exception.CustomException.*;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException userNotFoundException, WebRequest webRequest){
        return new ResponseEntity<>(userNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundByFirstNameException.class)
    public ResponseEntity<?> handleUserNotFoundByFirstNameException(UserNotFoundByFirstNameException userNotFoundByFirstNameException, WebRequest webRequest){
        return new ResponseEntity<>(userNotFoundByFirstNameException.getMessage(), HttpStatus.NOT_FOUND);
    }

}
