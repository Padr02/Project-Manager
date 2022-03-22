package com.example.application.data.repository;

import com.example.application.data.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity,Integer> {
    public Optional<TaskEntity> findByTitle(String title);
}
