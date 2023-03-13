package com.example.backend.persistence.entities;

import com.example.backend.domain.model.Workflow;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class WorkflowEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Builder.Default
    @OneToMany
    private List<StepEntity> steps = new ArrayList<>();
    @OneToOne
    private StepEntity firstStep;
    @Builder.Default
    @OneToMany
    private List<StepEntity> finalSteps = new ArrayList<>();
    @Builder.Default
    @OneToMany
    private List<ProjectEntity> projects = new ArrayList<>();
    @Builder.Default
    private Map<StepEntity, Map<String, StepEntity>> transitions = new HashMap<>();

    public Workflow toDomainModel() {
        return Workflow.builder()
                .id(id)
                .name(name)
                .steps(steps.stream().map(StepEntity::toDomainModel).collect(Collectors.toList()))
                .firstStep(firstStep.toDomainModel())
                .finalSteps(finalSteps.stream().map(StepEntity::toDomainModel).collect(Collectors.toList()))
                .build();
    }

    public static WorkflowEntity fromDomainModel(Workflow workflow) {
        return WorkflowEntity.builder()
                .id(workflow.getId())
                .name(workflow.getName())
                .steps(workflow.getSteps().stream().map(StepEntity::fromDomainModel).collect(Collectors.toList()))
                .firstStep(StepEntity.fromDomainModel(workflow.getFirstStep()))
                .finalSteps(workflow.getFinalSteps().stream().map(StepEntity::fromDomainModel).collect(Collectors.toList()))
                .build();
    }
}
