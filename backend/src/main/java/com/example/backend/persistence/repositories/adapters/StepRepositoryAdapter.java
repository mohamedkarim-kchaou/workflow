package com.example.backend.persistence.repositories.adapters;


import com.example.backend.domain.model.Step;
import com.example.backend.domain.repositoryPorts.StepRepositoryPort;
import com.example.backend.persistence.entities.StepEntity;
import com.example.backend.persistence.repositories.jpaRepositories.StepJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StepRepositoryAdapter implements StepRepositoryPort {
    private final StepJpaRepository stepJpaRepository;
    @Override
    public Optional<Step> findById(Long stepId) {
        Optional<Step> step = Optional.empty();
        Optional<StepEntity> stepEntity = stepJpaRepository.findById(stepId);
        if (stepEntity.isPresent()){
            step = Optional.of(stepEntity.get().toDomainModel());
        }
        return step;
    }

    @Override
    public List<Step> findAllById(List<Long> stepsIds) {
        return stepJpaRepository.findAllById(stepsIds).stream().map(StepEntity::toDomainModel).collect(Collectors.toList());
    }
}
