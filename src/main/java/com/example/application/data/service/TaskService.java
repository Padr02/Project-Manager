package com.example.application.data.service;

import com.example.application.data.RoleEnum;
import com.example.application.data.entity.TaskEntity;
import com.example.application.data.entity.UserEntity;
import com.example.application.data.repository.TaskRepository;
import com.example.application.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    TaskRepository taskRepository;


    public TaskService(@Autowired TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /*    public TaskEntity findByTitle(String title) {
        TaskEntity task = taskRepository.findByTitle(title).orElseThrow();
        return task;
    }*/

    public List<TaskEntity> getTasks() {
    return taskRepository.findAll();
    }
    public List<TaskEntity> getTasksByFilter(String filter) {
        if (filter == null || filter.isEmpty()){
            return taskRepository.findAll();
        }else{
            return taskRepository.search(filter);
        }
    }



    public TaskEntity saveTask(TaskEntity task){
        return taskRepository.save(task);
    }

    public void deleteTask(UUID id) {
        taskRepository.deleteById(id);
    }
/*
    //findByOwner_username(username);
    public TaskEntity findTaskById(int id) {
        return taskRepository.findById(id).orElseThrow();
    }




    // TODO: Update method


    public TaskEntity updateTaskById(int id, TaskEntity changedTaskEntity) {

        TaskEntity task = taskRepository.findById(id).orElseThrow();

        if(changedTaskEntity.getTitle() != null)
            task.setTitle(changedTaskEntity.getTitle());

        return taskRepository.save(task);

        //BeanUtils.copyProperties(changedTaskEntity, existingBlogPost, "id");
    }
*/

}
