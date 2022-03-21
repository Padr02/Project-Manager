package com.example.application.repository;

import com.example.application.model.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskEntity,Integer> {
    //metod saknas
}
