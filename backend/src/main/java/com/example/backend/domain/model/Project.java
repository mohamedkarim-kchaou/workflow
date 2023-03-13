package com.example.backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    private Long id;
    private String name;
    private Workflow workflow;
    private Step status;
}
