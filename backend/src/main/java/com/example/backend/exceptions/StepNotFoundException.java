package com.example.backend.exceptions;

public class StepNotFoundException extends RuntimeException {
    public StepNotFoundException(){
        super("Step Not Found Exception");
    }
}
