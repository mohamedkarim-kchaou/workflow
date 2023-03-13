package com.example.backend.persistence.repositories.adapters;


import com.example.backend.domain.model.Project;
import com.example.backend.domain.repositoryPorts.ProjectRepositoryPort;
import com.example.backend.persistence.entities.ProjectEntity;
import com.example.backend.persistence.repositories.jpaRepositories.ProjectJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectRepositoryAdapter implements ProjectRepositoryPort {
    private final ProjectJpaRepository projectJpaRepository;
    @Override
    public Project save(Project project) {
        return projectJpaRepository.save(ProjectEntity.fromDomainModel(project)).toDomainModel();
    }

    @Override
    public Optional<Project> findById(Long projectId) {
        Optional<Project> project = Optional.empty();
        Optional<ProjectEntity> projectEntity = projectJpaRepository.findById(projectId);
        if (projectEntity.isPresent()){
            project = Optional.of(projectEntity.get().toDomainModel());
        }
        return project;
    }

    @Override
    public List<Project> findAllByStatusId(Long statusId) {
        return projectJpaRepository.findAllByStatusId(statusId).stream().map(ProjectEntity::toDomainModel).collect(Collectors.toList());
    }
}
