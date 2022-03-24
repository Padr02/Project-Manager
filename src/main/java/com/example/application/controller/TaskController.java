package com.example.application.controller;

import com.example.application.data.entity.TaskEntity;
import com.example.application.data.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("tasks")
public class TaskController {

    @Autowired
    TaskService taskService;

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
            @RequestParam(required = false) String owner,
            @RequestParam(required = false) Date date,
            @RequestParam(required = true) boolean completed) {
        //taskService.updateTask(id, title, date, completed);
    }






    

}
