package com.example.application.data.views;
import com.example.application.data.entity.TaskEntity;
import com.example.application.data.service.TaskService;
import com.example.application.data.service.UserService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Collections;
import java.util.Comparator;

@PageTitle("Tasks")
@Route("/tasks")
//@RolesAllowed("ADMIN")

public class TaskView extends VerticalLayout {

    @Autowired
    TaskService taskService;

    @Autowired
    UserService userService;

    Grid<TaskEntity> grid = new Grid<>(TaskEntity.class);
    TextField filter = new TextField();
    TaskForm taskForm;


    public TaskView(TaskService taskService, UserService userService)  {
        this.userService=userService;
        this.taskService=taskService;
        setSizeFull();
        configureGrid();
        configureForm();
        add(getToolBar(),getContent());
        updateFromFilter();
        closeEditor();
    }

    private void closeEditor() {
        taskForm.setTask(null);
        taskForm.setVisible(false);
    }

    private Component getContent() {
       HorizontalLayout content = new HorizontalLayout(grid,taskForm);
       content.setFlexGrow(2,grid);
       content.setFlexGrow(1,taskForm);
       content.setSizeFull();
       return content;
    }

    private void configureForm() {
        taskForm = new TaskForm(userService.getUsers());
        taskForm.setWidth("25em");
    }

    private void updateFromFilter() {
        grid.setItems(taskService.getTasksByFilter(filter.getValue()));
    }

    private Component getToolBar() {
        filter.setPlaceholder("Search by title");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.LAZY);
        filter.addValueChangeListener(e -> updateFromFilter());
        Button addTaskBtn = new Button("Add task");
        HorizontalLayout horizontalLayout = new HorizontalLayout(filter,addTaskBtn);
        horizontalLayout.setAlignItems(Alignment.CENTER);
        return horizontalLayout;
    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.setColumns("title","startDate","deadline");
        grid.addComponentColumn((item)->{
           Icon icon;
           if (item.isCompleted()) {
               icon=VaadinIcon.CHECK.create();
               icon.setColor("hsla(145, 92%, 51%, 0.5)");
           } else {
               icon=VaadinIcon.CLOSE.create();
               icon.setColor("hsla(3, 75%, 62%, 0.5)");

           }
           return icon;
        }).setKey("completed").setComparator(Comparator.comparing(TaskEntity::isCompleted)).setHeader("Completed");
        grid.getColumnByKey("completed").setTextAlign(ColumnTextAlign.CENTER);
        grid.addColumn(user -> user.getOwner().getUsername()).setHeader("Owner");
        grid.getColumns().forEach(column -> column.setAutoWidth(true));
        grid.asSingleSelect().addValueChangeListener(task -> editTask(task.getValue()));
    }

    public void editTask (TaskEntity task){
        if (task == null) {
            closeEditor();
        } else {
            taskForm.setTask(task);
            taskForm.setVisible(true);
        }
    }
}


