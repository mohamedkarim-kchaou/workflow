package com.example.backend.exceptions;

public class WorkflowNotFoundException extends RuntimeException {
    public WorkflowNotFoundException(){
        super("Workflow Not Found Exception");
    }
}
