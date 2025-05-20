package com.Example.Product_Details.Exception;

public class CustomException {

    public static class productNotFoundById extends RuntimeException   {
        public productNotFoundById(String message){
            super(message);
    }
    }

}
