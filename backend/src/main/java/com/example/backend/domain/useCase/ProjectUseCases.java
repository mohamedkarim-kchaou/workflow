package com.example.backend.domain.useCase;

import com.example.backend.domain.model.Project;
import com.example.backend.domain.model.Step;
import com.example.backend.domain.model.Workflow;

public class ProjectUseCases {
    public static Project assignProjectToWorkflow(Project project, Workflow workflow){
        project.setWorkflow(workflow);
        project.setStatus(workflow.getFirstStep());
        return project;
    }
    public static Project proceedStep(Project project, String action){
        Step currentStep = project.getStatus();
        Workflow workflow = project.getWorkflow();
        if (workflow.getFinalSteps().contains(currentStep)){
            project.setStatus(null);
        } else {
            project.setStatus(StepUseCases.proceed(workflow, project.getStatus(), action));
        }
        return project;
    }
}
