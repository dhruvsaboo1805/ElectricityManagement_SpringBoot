package com.example.ElectricityMgmt.exceptions;

public class ConsumerNotFoundException extends RuntimeException{
    public ConsumerNotFoundException(String message) {
        super(message);
    }
}
