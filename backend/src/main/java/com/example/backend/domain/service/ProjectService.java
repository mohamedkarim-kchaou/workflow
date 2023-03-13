package com.example.backend.domain.service;

import com.example.backend.domain.model.Project;

public interface ProjectService {
    Project create(String name);
    Project assignProjectToWorkflow(Long projectId, Long workflowId);
    Project proceedStep(Long projectId, String action);
}
