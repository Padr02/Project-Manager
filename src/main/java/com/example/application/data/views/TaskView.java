package com.example.application.data.views;
import com.example.application.data.entity.TaskEntity;
import com.example.application.data.service.TaskService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
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
    Grid<TaskEntity> grid = new Grid<>(TaskEntity.class);
    TextField filter = new TextField();
    TaskForm taskForm;

    public TaskView(TaskService taskService)  {
        this.taskService=taskService;
        setSizeFull();
        configureGrid();
        configureForm();
        add(getToolBar(),getContent());
        uppdateFromFilter();
    }

    private Component getContent() {
       HorizontalLayout content = new HorizontalLayout(grid,taskForm);
       content.setFlexGrow(2,grid);
       content.setFlexGrow(1,taskForm);
       content.setSizeFull();
       return content;
    }

    private void configureForm() {
        taskForm = new TaskForm(Collections.emptyList());
        taskForm.setWidth("25em");
    }

    private void uppdateFromFilter() {
        grid.setItems(taskService.getTasksByFilter(filter.getValue()));
    }

    private Component getToolBar() {
        filter.setPlaceholder("Search by title");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.LAZY);
        filter.addValueChangeListener(e -> uppdateFromFilter());
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
               icon = VaadinIcon.CHECK.create();
               icon.setColor("hsla(145, 92%, 51%, 0.5)");
           } else {
               icon=VaadinIcon.CLOSE.create();
               icon.setColor("hsla(3, 75%, 62%, 0.5)");

           }
           return icon;
        }).setKey("completed").setComparator(Comparator.comparing(TaskEntity::isCompleted)).setHeader("Completed");
        grid.getColumnByKey("completed").setTextAlign(ColumnTextAlign.CENTER);
        grid.addColumn(u -> u.getOwner().getUsername()).setHeader("Owner");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }
}


