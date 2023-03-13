package com.example.backend.persistence.repositories.jpaRepositories;

import com.example.backend.persistence.entities.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectJpaRepository extends JpaRepository<ProjectEntity, Long> {
    List<ProjectEntity> findAllByStatusId(Long statusId);
}
