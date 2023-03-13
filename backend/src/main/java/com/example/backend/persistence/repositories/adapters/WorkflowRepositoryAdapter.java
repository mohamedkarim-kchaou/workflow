package com.example.backend.persistence.repositories.adapters;


import com.example.backend.domain.model.Project;
import com.example.backend.domain.model.Workflow;
import com.example.backend.domain.repositoryPorts.WorkflowRepositoryPort;
import com.example.backend.persistence.entities.ProjectEntity;
import com.example.backend.persistence.entities.WorkflowEntity;
import com.example.backend.persistence.repositories.jpaRepositories.WorkflowJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkflowRepositoryAdapter implements WorkflowRepositoryPort {
    private final WorkflowJpaRepository workflowJpaRepository;
    @Override
    public Workflow save(Workflow workflow) {
        return workflowJpaRepository.save(WorkflowEntity.fromDomainModel(workflow)).toDomainModel();
    }

    @Override
    public Optional<Workflow> findById(Long workflowId) {
        Optional<Workflow> workflow = Optional.empty();
        Optional<WorkflowEntity> workflowEntity = workflowJpaRepository.findById(workflowId);
        if (workflowEntity.isPresent()){
            workflow = Optional.of(workflowEntity.get().toDomainModel());
        }
        return workflow;
    }
}
