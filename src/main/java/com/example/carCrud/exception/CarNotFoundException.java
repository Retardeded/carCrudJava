package com.example.carCrud.exception;

public class CarNotFoundException extends RuntimeException {
    public CarNotFoundException(Long id) {
        super("Could not find car with id: " + id);
    }
}
