package com.example.application.data.repository;

import com.example.application.data.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, UUID> {

    //List<TaskEntity> findByOwner_username(String title);
    //List<TaskEntity> getTaskList();

}
