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

    @ExceptionHandler(UserNotFoundByLastNameException.class)
    public ResponseEntity<?> handleUserNotFoundByLastNameException(UserNotFoundByLastNameException userNotFoundByLastNameException, WebRequest webRequest){
        return new ResponseEntity<>(userNotFoundByLastNameException.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundByEmailException.class)
    public ResponseEntity<?> handleUserNotFoundByEmailException(UserNotFoundByEmailException userNotFoundByEmailException, WebRequest webRequest){
        return new ResponseEntity<>(userNotFoundByEmailException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundByNumberException.class)
    public ResponseEntity<?> handleUserNotFoundByNumberException(UserNotFoundByNumberException userNotFoundByNumberException, WebRequest webRequest){
        return new ResponseEntity<>(userNotFoundByNumberException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundByNameException.class)
    public ResponseEntity<?> handleUserNotFoundByNameException(UserNotFoundByNameException userNotFoundByNameException,WebRequest webRequest){
        return new ResponseEntity<>(userNotFoundByNameException.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundByFirstNameAndEmailException.class)
    public ResponseEntity<?> handleUserNotFoundByFirstNameAndEmailException(UserNotFoundByFirstNameAndEmailException userNotFoundByFirstNameAndEmailException, WebRequest webRequest){
        return new ResponseEntity<>(userNotFoundByFirstNameAndEmailException.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundByLastNameAndEmailException.class)
    public ResponseEntity<?> handleUserNotFoundByLastNameAndEmailException(UserNotFoundByLastNameAndEmailException userNotFoundByLastNameAndEmailException, WebRequest webRequest){
        return new ResponseEntity<>(userNotFoundByLastNameAndEmailException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoDataExistException.class)
    public ResponseEntity<?> handleNoDataExistException(NoDataExistException noDataExistException, WebRequest webRequest){
        return new ResponseEntity<>(noDataExistException.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<?> handleUserAlreadyExistsException(UserAlreadyExistsException userAlreadyExistsException, WebRequest webRequest){
        return new ResponseEntity<>(userAlreadyExistsException.getMessage(),HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserAlreadyExistByThisEmailException.class)
    public ResponseEntity<?> handleUserAlreadyExistByThisEmailException(UserAlreadyExistByThisEmailException userAlreadyExistByThisEmailException, WebRequest webRequest){
        return new ResponseEntity<>(userAlreadyExistByThisEmailException.getMessage(),HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserAlreadyExistByThisNumberException.class)
    public ResponseEntity<?> handleUserAlreadyExistByThisNumberException(UserAlreadyExistByThisNumberException userAlreadyExistByThisNumberException, WebRequest webRequest){
        return new ResponseEntity<>(userAlreadyExistByThisNumberException.getMessage(), HttpStatus.CONFLICT);
    }
}
