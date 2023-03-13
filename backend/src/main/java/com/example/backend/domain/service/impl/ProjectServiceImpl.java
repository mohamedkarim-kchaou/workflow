package com.example.backend.domain.service.impl;

import com.example.backend.domain.model.Project;
import com.example.backend.domain.model.Workflow;
import com.example.backend.domain.repositoryPorts.ProjectRepositoryPort;
import com.example.backend.domain.repositoryPorts.WorkflowRepositoryPort;
import com.example.backend.domain.service.ProjectService;
import com.example.backend.domain.useCase.ProjectUseCases;
import com.example.backend.domain.useCase.WorkflowUseCases;
import com.example.backend.exceptions.ProjectNotFoundException;
import com.example.backend.exceptions.WorkflowNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepositoryPort projectRepositoryPort;
    private final WorkflowRepositoryPort workflowRepositoryPort;

    @Override
    public Project create(String name) {
        return projectRepositoryPort.save(Project.builder().name(name).build());
    }

    @Override
    public Project assignProjectToWorkflow(Long projectId, Long workflowId) {
        Workflow workflow = workflowRepositoryPort.findById(workflowId).orElseThrow(WorkflowNotFoundException::new);
        Project project = projectRepositoryPort.findById(projectId).orElseThrow(ProjectNotFoundException::new);
        return projectRepositoryPort.save(ProjectUseCases.assignProjectToWorkflow(project, workflow));
    }

    @Override
    public Project proceedStep(Long projectId, String action) {
        Project project = projectRepositoryPort.findById(projectId).orElseThrow(ProjectNotFoundException::new);
        return projectRepositoryPort.save(ProjectUseCases.proceedStep(project, action));
    }
}
