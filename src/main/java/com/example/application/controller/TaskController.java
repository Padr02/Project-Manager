package com.example.application.controller;

import com.example.application.data.entity.TaskEntity;
import com.example.application.data.entity.UserEntity;
import com.example.application.data.service.TaskService;
import com.example.application.data.service.UserService;
import com.example.application.dto.DtoConverter;
import com.example.application.dto.TaskRequestDTO;
import com.example.application.dto.TaskResponseDTO;
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

    @Autowired
    DtoConverter dtoConverter;

    @Autowired
    UserService userService;

    @GetMapping
    public List<TaskResponseDTO>GetTasksFromRepo(@RequestParam(required = false)String title) {
        return taskService.getTasksByFilter(title)
                .stream()
                .map(task -> dtoConverter.entityToResponseDTO(task))
                .toList();
    }

    @GetMapping("/{user}")
    public List<TaskResponseDTO>GetTasksOfUsers(@RequestParam(required = false)String username) {
            UserEntity user = userService.getUsername(username);
            return taskService.getTasks()
            .stream().filter(u -> u.getOwner().equals(user))
            .map(task -> dtoConverter.entityToResponseDTO(task))
            .toList();
    }

    @PostMapping
    public TaskResponseDTO addNewTask(@RequestBody TaskRequestDTO taskRequestDTO) {
        TaskEntity taskEntityIn = dtoConverter.RequestDtoToEntity(taskRequestDTO);
        TaskEntity taskEntityOut = taskService.saveTask(taskEntityIn);
        return dtoConverter.entityToResponseDTO(taskEntityOut);
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

