package com.example.backend.domain.useCase;

import com.example.backend.domain.model.Step;
import com.example.backend.domain.model.Workflow;

public class StepUseCases {
    public static Step proceed(Workflow workflow, Step step, String action){
        return workflow.getTransitions().get(step).get(action);
    }
}
