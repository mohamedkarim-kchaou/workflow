package com.example.backend.web.dto;

import com.example.backend.domain.model.Step;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StepResponse {
    private Long id;
    private String name;

    public static StepResponse response(Step step) {
        return StepResponse.builder()
                .id(step.getId())
                .name(step.getName())
                .build();
    }
}
