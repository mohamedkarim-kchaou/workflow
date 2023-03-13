package com.example.backend.web.controllers;

import com.example.backend.domain.model.Step;
import com.example.backend.domain.service.WorkflowService;
import com.example.backend.web.dto.ProjectResponse;
import com.example.backend.web.dto.WorkflowResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/workflows")
@RequiredArgsConstructor
public class WorkflowController {
    private final WorkflowService workflowService;

    @PostMapping()
    @Transactional
    public ResponseEntity<WorkflowResponse> createProject(
            @RequestBody String name) {
        return ResponseEntity.ok(WorkflowResponse.response(workflowService.create(name)));
    }

    @PutMapping("/{workflowId}/steps")
    @Transactional
    public ResponseEntity<WorkflowResponse> addSteps(
            @PathVariable Long workflowId, @RequestBody List<String> steps) {
        return ResponseEntity.ok(WorkflowResponse.response(workflowService.addSteps(workflowId, steps)));
    }

    @PutMapping("/{workflowId}/steps/{stepId}")
    @Transactional
    public ResponseEntity<WorkflowResponse> designateFirstStep(
            @PathVariable Long workflowId, @PathVariable Long stepId) {
        return ResponseEntity.ok(WorkflowResponse.response(workflowService.designateFirstStep(workflowId, stepId)));
    }

    @PutMapping("/{workflowId}/final-steps")
    @Transactional
    public ResponseEntity<WorkflowResponse> designateFinalSteps(
            @PathVariable Long workflowId, @RequestBody List<Long> steps) {
        return ResponseEntity.ok(WorkflowResponse.response(workflowService.designateFinalSteps(workflowId, steps)));
    }

    @PutMapping("/{workflowId}/transitions")
    @Transactional
    public ResponseEntity<WorkflowResponse> setTransactions(
            @PathVariable Long workflowId, @RequestBody Map<Step, Map<String, Step>> transitions) {
        return ResponseEntity.ok(WorkflowResponse.response(workflowService.setTransitions(workflowId, transitions)));
    }

    @PutMapping("/{workflowId}/steps/{stepId}")
    @Transactional
    public ResponseEntity<WorkflowResponse> deleteStep(
            @PathVariable Long workflowId, @PathVariable Long stepId) {
        return ResponseEntity.ok(WorkflowResponse.response(workflowService.deleteStep(workflowId, stepId)));
    }
}
