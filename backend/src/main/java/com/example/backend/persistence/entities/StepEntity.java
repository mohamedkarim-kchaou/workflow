package com.example.backend.persistence.entities;


import com.example.backend.domain.model.Project;
import com.example.backend.domain.model.Step;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class StepEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Step toDomainModel() {
        return Step.builder()
                .id(id)
                .name(name)
                .build();
    }

    public static StepEntity fromDomainModel(Step step) {
        return StepEntity.builder()
                .id(step.getId())
                .name(step.getName())
                .build();
    }
}
