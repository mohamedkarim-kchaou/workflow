package com.example.backend.domain.repositoryPorts;

import com.example.backend.domain.model.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectRepositoryPort {
    Project save(Project project);
    Optional<Project> findById(Long projectId);
    List<Project> findAllByStatusId(Long statusId);
}
