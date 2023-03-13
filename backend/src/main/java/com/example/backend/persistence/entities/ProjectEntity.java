package com.example.backend.persistence.entities;

import javax.persistence.*;

import com.example.backend.domain.model.Project;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @ManyToOne
    private WorkflowEntity workflow;
    @OneToOne
    private StepEntity status;

    public Project toDomainModel() {
        return Project.builder()
                .id(id)
                .name(name)
                .workflow(workflow.toDomainModel())
                .status(status.toDomainModel())
                .build();
    }

    public static ProjectEntity fromDomainModel(Project project) {
        return ProjectEntity.builder()
                .id(project.getId())
                .name(project.getName())
                .workflow(WorkflowEntity.fromDomainModel(project.getWorkflow()))
                .status(StepEntity.fromDomainModel(project.getStatus()))
                .build();
    }
}
