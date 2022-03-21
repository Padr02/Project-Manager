package com.example.application.controller;

import com.example.application.data.entity.TaskEntity;
import com.example.application.data.service.PcsService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final PcsService taskService;

    @GetMapping
    public List<TaskEntity> getTasks() {
        return taskService.getTasks();
    }

    @PostMapping
    public void addTask(@RequestBody TaskEntity taskEntity) {
        taskService.saveTask(taskEntity);
    }

    @DeleteMapping({"/id"})
    public void deleteTask(@PathVariable("id") Integer id) {
        taskService.deleteTask(id);

    }

    @PutMapping(path = "{id}")
    public void updateTask(
            @PathVariable("id") Integer id,
            @RequestParam(required = false) String title,
              @RequestParam(required = false) Date date,
              @RequestParam(required = true) boolean completed) {
        //taskService.updateTask(id, title, date, completed);
    }






    

}
