package com.example.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.application.data.entity.TaskEntity;
import com.example.application.data.entity.UserEntity;
import com.example.application.data.service.TaskService;
import com.example.application.data.service.UserService;
import com.example.application.dto.DtoConverter;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class TaskController {

    @Autowired
    TaskService taskService;

    @Autowired
    DtoConverter dtoConverter;

    @Autowired
    UserService userService;

    @GetMapping
    public List<DtoConverter.TaskResponseDTO>GetTasksFromRepo(@RequestParam(required = false) String title) {
        return taskService.getTasksByFilter(title)
                .stream()
                .map(task -> dtoConverter.entityToResponseDTO(task))
                .toList();
    }

    @GetMapping("/{user}")
    public List<DtoConverter.TaskResponseDTO>GetTasksOfUsers(@RequestParam(required = false) String username) {
            UserEntity user = userService.findUser(username);
            return taskService.getTasks()
            .stream().filter(u -> u.getOwner().equals(user))
            .map(task -> dtoConverter.entityToResponseDTO(task))
            .toList();
    }

    @PostMapping
    public DtoConverter.TaskResponseDTO addNewTask(@RequestBody DtoConverter.TaskRequestDTO taskRequestDTO ) {
        TaskEntity taskEntityIn = dtoConverter.RequestDtoToEntity(taskRequestDTO);
        TaskEntity taskEntityOut = taskService.saveTask(taskEntityIn);
        return dtoConverter.entityToResponseDTO(taskEntityOut);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable("id") UUID id) {
        taskService.deleteTask(id);
    }

    @PutMapping("/{id}")
    public DtoConverter.TaskResponseDTO updateTaskById(@PathVariable("id") UUID id, @RequestBody DtoConverter.TaskRequestDTO updatedTaskDTO) {
        TaskEntity updatedTaskIn = dtoConverter.RequestDtoToEntity(updatedTaskDTO);
        System.out.println(updatedTaskIn);
        TaskEntity updatedTaskOut = taskService.updateTask(id, updatedTaskIn);
        return dtoConverter.entityToResponseDTO(updatedTaskOut);
    }
}
