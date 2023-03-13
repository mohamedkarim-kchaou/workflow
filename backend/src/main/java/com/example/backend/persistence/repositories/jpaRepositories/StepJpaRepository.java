package com.example.backend.persistence.repositories.jpaRepositories;

import com.example.backend.persistence.entities.StepEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StepJpaRepository extends JpaRepository<StepEntity, Long> {
}
