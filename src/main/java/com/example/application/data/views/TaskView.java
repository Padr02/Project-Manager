package com.example.application.data.views;

import com.example.application.data.entity.TaskEntity;
import com.example.application.data.service.TaskService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import java.util.List;

@Route("/tasks")
@PageTitle("TaskView")
//@PermitAll
public class TaskView extends Div {

    TaskService taskService;

    public TaskView(TaskService taskService)  {
        this.taskService = taskService;
        Grid<TaskEntity> grid = new Grid<>(TaskEntity.class, false);
        gridConfig(grid);
        List<TaskEntity> tasks = taskService.getTasks();
        grid.setItems(tasks);
        add(grid);
    }

    private void gridConfig(Grid<TaskEntity> grid) {
        grid.addColumn(TaskEntity::getTitle).setHeader("Title").setFrozen(true).setSortable(true);
        grid.addColumn(TaskEntity::getOwner).setHeader("Owner").setFrozen(true).setSortable(true);
        grid.addColumn(TaskEntity::getStartDate).setHeader("Start date").setFrozen(true).setSortable(true);
        grid.addColumn(TaskEntity::getCompleted).setHeader("Completed").setFrozen(true).setSortable(true);
    }

}
