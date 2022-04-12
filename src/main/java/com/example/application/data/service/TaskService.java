package com.example.application.data.service;

import com.example.application.data.entity.TaskEntity;
import com.example.application.data.repository.TaskRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    TaskRepository taskRepository;

    public TaskService(@Autowired TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskEntity> getTasks() {
        return taskRepository.findAll();
    }

    public List<TaskEntity> getTasksByFilter(String username) {
        if (username == null || username.isEmpty()){
            return taskRepository.findAll();
        } else {
            return taskRepository.search(username);
        }
    }

    public TaskEntity saveTask(TaskEntity task) {
        return taskRepository.save(task);
    }

    public void deleteTask(UUID id) {
        taskRepository.deleteById(id);
    }

    public TaskEntity updateTask(UUID id, TaskEntity updatedTaskIn) {
        TaskEntity currTask = taskRepository.findById(id).orElseThrow();
        BeanUtils.copyProperties(updatedTaskIn, currTask,"id");
        taskRepository.save(currTask);
        return currTask;
    }
}
