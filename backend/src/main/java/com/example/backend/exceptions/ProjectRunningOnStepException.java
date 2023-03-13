package com.example.backend.exceptions;

public class ProjectRunningOnStepException extends RuntimeException {
    public ProjectRunningOnStepException(){
        super("Project Running On Step Exception");
    }
}
