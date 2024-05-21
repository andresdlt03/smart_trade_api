package com.bluejtitans.smarttradebackend.exception;

public class ProductAlreadyAddedException extends Exception{
    public ProductAlreadyAddedException(String message){
        super(message);
    }
}