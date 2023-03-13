package com.example.backend.web.controllers;

import com.example.backend.domain.service.ProjectService;
import com.example.backend.domain.service.WorkflowService;
import com.example.backend.web.dto.ProjectResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping()
    @Transactional
    public ResponseEntity<ProjectResponse> createProject(
            @RequestBody String name) {
        return ResponseEntity.ok(ProjectResponse.response(projectService.create(name)));
    }

    @PutMapping("/{projectId}/assign-workflow")
    @Transactional
    public ResponseEntity<ProjectResponse> assignProjectToWorkflow(
            @PathVariable Long projectId, @RequestBody Long workflowId) {
        return ResponseEntity.ok(ProjectResponse.response(projectService.assignProjectToWorkflow(projectId, workflowId)));
    }

    @PutMapping("/{projectId}/proceed-step")
    @Transactional
    public ResponseEntity<ProjectResponse> proceedStep(
            @PathVariable Long projectId, @RequestBody String action) {
        return ResponseEntity.ok(ProjectResponse.response(projectService.proceedStep(projectId, action)));
    }
}
