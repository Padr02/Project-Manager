package com.example.application.controller;

import com.example.application.data.entity.TaskEntity;
import com.example.application.data.repository.TaskRepository;
import com.example.application.data.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    @Autowired
    TaskService taskService;

    

    @GetMapping
    public List<TaskEntity>GetTasksFromRepo() {
        return taskService.getTasks();
    }

    @PostMapping
    public void addNewTask(@RequestBody TaskEntity taskEntity) {
        taskService.saveTask(taskEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable("id") UUID id) {
        taskService.deleteTask(id);
    }
                /*
                .stream()
                .map(task -> dtoConverter.entityToResponseDTO(task))
                .toList();
            */

/*
    @GetMapping("{id}")
    public TaskResponseDTO getTaskById(@PathVariable("id") Integer id) {
        TaskEntity taskEntity = taskService.findTaskById(id);
        return dtoConverter.entityToResponseDTO(taskEntity);
    }



    @PostMapping
    public TaskResponseDTO addNewTask(@RequestBody TaskRequestDTO taskRequestDTO) {
        TaskEntity newTask = dtoConverter.RequestDtoToEntity(taskRequestDTO);
        TaskEntity taskOut = taskService.saveTask(newTask);
        return dtoConverter.entityToResponseDTO(taskOut);
    }

   @PutMapping( "/{id}")
    public TaskResponseDTO updateBlogPostById(@PathVariable("id") int id, @RequestBody TaskRequestDTO changedTaskDTO){
        TaskEntity changedTask = dtoConverter.RequestDtoToEntity(changedTaskDTO);
        TaskEntity taskOut = taskService.updateTaskById(id, changedTask);
        return dtoConverter.entityToResponseDTO(taskOut);*/
    }

