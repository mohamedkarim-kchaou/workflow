package com.example.backend.domain.useCase;

import com.example.backend.domain.model.Project;
import com.example.backend.domain.model.Step;
import com.example.backend.domain.model.Workflow;
import com.example.backend.exceptions.ProjectRunningOnStepException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WorkflowUseCases {
    public static Workflow addStepsToWorkflow(Workflow workflow, List<String> steps){
        steps.forEach(stepName -> workflow.getSteps().add(Step.builder().name(stepName).build()));
        return workflow;
    }

    public static Workflow designateFirstStep(Workflow workflow, Step step){
        workflow.setFirstStep(step);
        return workflow;
    }

    public static Workflow designateFinalSteps(Workflow workflow, List<Step> steps){
        workflow.getFinalSteps().clear();
        workflow.getFinalSteps().addAll(steps);
        return workflow;
    }

    public static Workflow setTransactions(Workflow workflow, Map<Step, Map<String, Step>> transitions){
        workflow.setTransitions(transitions);
        return workflow;
    }

    public static Workflow deleteStep(Workflow workflow, Step step, List<Project> projects){
        if (projects.isEmpty()){
            workflow.getSteps().remove(step);
            // If the step is the last one, cut every transition that leads to this step
            if (workflow.getFinalSteps().contains(step)){
                workflow.getFinalSteps().remove(step);
                for (Step stepFrom: workflow.getTransitions().keySet()){
                    for(String action :workflow.getTransitions().get(stepFrom).keySet()){
                        if(workflow.getTransitions().get(stepFrom).get(action).equals(step)){
                            workflow.getTransitions().get(stepFrom).remove(action);
                        }
                    }
                    workflow.getFinalSteps().add(stepFrom);
                }
            } else if (workflow.getFirstStep().equals(step)){
                // If the step is the first one, cut every transition that starts with or leads to this step
                List<String> actions = new ArrayList<>(workflow.getTransitions().get(step).keySet());
                workflow.setFirstStep(workflow.getTransitions().get(step).get(actions.get(0)));
                workflow.getTransitions().remove(step);
                for (Step stepFrom: workflow.getTransitions().keySet()){
                    for(String action :workflow.getTransitions().get(stepFrom).keySet()){
                        if(workflow.getTransitions().get(stepFrom).get(action).equals(step)){
                            workflow.getTransitions().get(stepFrom).remove(action);
                        }
                    }
                }
            } else {
                // If the step is in the middle, cut every transition that starts with or leads to this step
                for (Step stepFrom: workflow.getTransitions().keySet()){
                    for(String action :workflow.getTransitions().get(stepFrom).keySet()){
                        if(workflow.getTransitions().get(stepFrom).get(action).equals(step)){
                            workflow.getTransitions().get(stepFrom).remove(action);
                        }
                    }
                }
                workflow.getTransitions().remove(step);
            }
            return workflow;
        } else {
            throw new ProjectRunningOnStepException();
        }
    }
}
