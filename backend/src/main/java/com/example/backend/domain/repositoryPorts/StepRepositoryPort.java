package com.example.backend.domain.repositoryPorts;

import com.example.backend.domain.model.Step;

import java.util.List;
import java.util.Optional;

public interface StepRepositoryPort {
    Optional<Step> findById(Long stepId);
    List<Step> findAllById(List<Long> stepsIds);
}
