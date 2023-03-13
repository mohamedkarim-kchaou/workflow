package com.example.backend.persistence.repositories.jpaRepositories;

import com.example.backend.persistence.entities.WorkflowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkflowJpaRepository extends JpaRepository<WorkflowEntity, Long> {
}
