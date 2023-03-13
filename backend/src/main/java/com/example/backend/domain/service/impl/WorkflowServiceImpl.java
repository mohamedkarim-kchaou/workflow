package com.example.backend.domain.service.impl;

import com.example.backend.domain.model.Project;
import com.example.backend.domain.model.Step;
import com.example.backend.domain.model.Workflow;
import com.example.backend.domain.repositoryPorts.ProjectRepositoryPort;
import com.example.backend.domain.repositoryPorts.StepRepositoryPort;
import com.example.backend.domain.repositoryPorts.WorkflowRepositoryPort;
import com.example.backend.domain.service.ProjectService;
import com.example.backend.domain.service.WorkflowService;
import com.example.backend.domain.useCase.WorkflowUseCases;
import com.example.backend.exceptions.StepNotFoundException;
import com.example.backend.exceptions.WorkflowNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class WorkflowServiceImpl implements WorkflowService {
    private final WorkflowRepositoryPort workflowRepositoryPort;
    private final StepRepositoryPort stepRepositoryPort;
    private final ProjectRepositoryPort projectRepositoryPort;
    @Override
    public Workflow create(String name) {
        return workflowRepositoryPort.save(Workflow.builder().name(name).build());
    }

    @Override
    public Workflow addSteps(Long workflowId, List<String> steps){
        Workflow workflow = workflowRepositoryPort.findById(workflowId).orElseThrow(WorkflowNotFoundException::new);
        return workflowRepositoryPort.save(WorkflowUseCases.addStepsToWorkflow(workflow, steps));
    }

    @Override
    public Workflow designateFirstStep(Long workflowId, Long stepId) {
        Workflow workflow = workflowRepositoryPort.findById(workflowId).orElseThrow(WorkflowNotFoundException::new);
        Step step = stepRepositoryPort.findById(stepId).orElseThrow(StepNotFoundException::new);
        return workflowRepositoryPort.save(WorkflowUseCases.designateFirstStep(workflow, step));
    }

    @Override
    public Workflow designateFinalSteps(Long workflowId, List<Long> stepsIds) {
        Workflow workflow = workflowRepositoryPort.findById(workflowId).orElseThrow(WorkflowNotFoundException::new);
        List<Step> steps = stepRepositoryPort.findAllById(stepsIds);
        return workflowRepositoryPort.save(WorkflowUseCases.designateFinalSteps(workflow, steps));
    }

    @Override
    public Workflow setTransitions(Long workflowId, Map<Step, Map<String, Step>> transitions) {
        Workflow workflow = workflowRepositoryPort.findById(workflowId).orElseThrow(WorkflowNotFoundException::new);
        return workflowRepositoryPort.save(WorkflowUseCases.setTransactions(workflow, transitions));
    }

    @Override
    public Workflow deleteStep(Long workflowId, Long stepId) {
        Workflow workflow = workflowRepositoryPort.findById(workflowId).orElseThrow(WorkflowNotFoundException::new);
        Step step = stepRepositoryPort.findById(stepId).orElseThrow(StepNotFoundException::new);
        List<Project> projects = projectRepositoryPort.findAllByStatusId(stepId);
        return workflowRepositoryPort.save(WorkflowUseCases.deleteStep(workflow, step, projects));
    }
}
