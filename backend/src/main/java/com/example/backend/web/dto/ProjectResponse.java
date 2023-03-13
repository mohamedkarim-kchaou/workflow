package com.example.backend.web.dto;

import com.example.backend.domain.model.Project;
import com.example.backend.domain.model.Step;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectResponse {
    private Long id;
    private String name;
    private WorkflowResponse workflow;
    private StepResponse status;

    public static ProjectResponse response(Project project) {
        return ProjectResponse.builder()
                .id(project.getId())
                .name(project.getName())
                .workflow(WorkflowResponse.response(project.getWorkflow()))
                .status(StepResponse.response(project.getStatus()))
                .build();
    }
}
