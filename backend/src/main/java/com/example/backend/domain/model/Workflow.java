package com.example.backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Workflow {
    private Long id;
    private String name;
    @Builder.Default
    private List<Step> steps = new ArrayList<>();
    private Step firstStep;
    @Builder.Default
    private List<Step> finalSteps = new ArrayList<>();
    @Builder.Default
    private Map<Step, Map<String, Step>> transitions = new HashMap<>();
}
