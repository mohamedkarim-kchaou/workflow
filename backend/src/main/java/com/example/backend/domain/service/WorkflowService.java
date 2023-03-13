package com.example.backend.domain.service;

import com.example.backend.domain.model.Step;
import com.example.backend.domain.model.Workflow;

import java.util.List;
import java.util.Map;

public interface WorkflowService {
    Workflow create(String name);
    Workflow addSteps(Long workflowId, List<String> steps);
    Workflow designateFirstStep(Long workflowId, Long stepId);
    Workflow designateFinalSteps(Long workflowId, List<Long> stepsIds);
    Workflow setTransitions(Long workflowId, Map<Step, Map<String, Step>> transitions);
    Workflow deleteStep(Long workflowId, Long stepId);
}
