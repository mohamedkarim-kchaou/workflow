package com.example.backend.domain.repositoryPorts;

import com.example.backend.domain.model.Step;
import com.example.backend.domain.model.Workflow;

import java.util.List;
import java.util.Optional;

public interface WorkflowRepositoryPort {
    Workflow save(Workflow workflow);
    Optional<Workflow> findById(Long workflowId);
}
