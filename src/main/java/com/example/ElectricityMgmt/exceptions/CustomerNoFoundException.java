package com.example.ElectricityMgmt.exceptions;

public class CustomerNoFoundException extends RuntimeException{
    public CustomerNoFoundException(String message) {
        super(message);
    }
}
