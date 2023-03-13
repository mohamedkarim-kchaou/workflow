package com.example.backend.web.dto;

import com.example.backend.domain.model.Project;
import com.example.backend.domain.model.Step;
import com.example.backend.domain.model.Workflow;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkflowResponse {
    private Long id;
    private String name;
    @Builder.Default
    private List<StepResponse> steps = new ArrayList<>();
    private StepResponse firstStep;
    @Builder.Default
    private List<StepResponse> finalSteps = new ArrayList<>();
    @Builder.Default
    private Map<Step, Map<String, Step>> transitions = new HashMap<>();

    public static WorkflowResponse response(Workflow workflow) {
        return WorkflowResponse.builder()
                .id(workflow.getId())
                .name(workflow.getName())
                .steps(workflow.getSteps().stream().map(StepResponse::response).collect(Collectors.toList()))
                .finalSteps(workflow.getFinalSteps().stream().map(StepResponse::response).collect(Collectors.toList()))
                .firstStep(StepResponse.response(workflow.getFirstStep()))
                .transitions(workflow.getTransitions())
                .build();
    }
}
