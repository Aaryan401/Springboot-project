package com.Example.Employee_Management.Exception;

public class CustomException{
    public static class UserNotFoundException extends RuntimeException{
        public UserNotFoundException(String message){
            super(message);
        }
    }

    public static class UserNotFoundByFirstNameException extends RuntimeException{
        public UserNotFoundByFirstNameException(String message){
            super(message);
        }
    }

    public static class UserNotFoundByLastNameException extends RuntimeException{
        public UserNotFoundByLastNameException(String message){
            super(message);
        }
    }

    public static class UserNotFoundByEmailException extends RuntimeException{
        public UserNotFoundByEmailException(String message){
            super(message);
        }
    }

    public static class UserNotFoundByNumberException extends RuntimeException{
        public UserNotFoundByNumberException(String message){
            super(message);
        }
    }
    public static class UserNotFoundByNameException extends RuntimeException{
        public UserNotFoundByNameException(String message){
            super(message);
        }
    }
    public static class UserNotFoundByFirstNameAndEmailException extends RuntimeException{
        public UserNotFoundByFirstNameAndEmailException(String message){
            super(message);
        }
    }

    public static class UserNotFoundByLastNameAndEmailException extends RuntimeException{
        public UserNotFoundByLastNameAndEmailException(String message){
            super(message);
        }
    }

    public static class NoDataExistException extends RuntimeException{
        public NoDataExistException(String message){
            super(message);
        }
    }

    public static class UserAlreadyExistsException extends RuntimeException{
        public UserAlreadyExistsException(String message){
            super(message);
        }
    }

    public static class UserAlreadyExistByThisEmailException extends RuntimeException{
        public UserAlreadyExistByThisEmailException(String message){
            super(message);
        }
    }

    public static class UserAlreadyExistByThisNumberException extends RuntimeException{
        public UserAlreadyExistByThisNumberException(String message){
            super(message);
        }
    }
}
