package com.example.backend.exceptions;

public class ProjectNotFoundException extends RuntimeException {
    public ProjectNotFoundException(){
        super("Project Not Found Exception");
    }
}
