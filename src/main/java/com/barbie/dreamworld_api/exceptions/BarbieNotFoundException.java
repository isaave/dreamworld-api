package com.barbie.dreamworld_api.exceptions;

public class BarbieNotFoundException extends RuntimeException {
    public BarbieNotFoundException(Long id) {
        super("Could not find Barbie " + id);
    }
}