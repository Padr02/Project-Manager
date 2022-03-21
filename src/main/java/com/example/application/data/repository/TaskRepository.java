package com.example.application.data.repository;

import com.example.application.data.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskEntity,Integer> {
    //metod saknas
}
