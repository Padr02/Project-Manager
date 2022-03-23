package com.example.application.data.service;

import com.example.application.data.entity.TaskEntity;
import com.example.application.data.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

/*    public TaskEntity findByTitle(String title) {
        TaskEntity task = taskRepository.findByTitle(title).orElseThrow();
        return task;
    }*/

    public List<TaskEntity> getTasks() {
        return taskRepository.findAll();
    }

    public TaskEntity saveTask(TaskEntity task){
        return taskRepository.save(task);
    }

    public void deleteTask(Integer id) {
        taskRepository.deleteById(id);
    }
}
