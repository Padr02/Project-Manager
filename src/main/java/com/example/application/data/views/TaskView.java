package com.example.application.data.views;
import com.example.application.data.entity.TaskEntity;
import com.example.application.data.service.TaskService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.crud.Crud;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;



import javax.annotation.security.RolesAllowed;
import java.awt.*;
import java.util.Collections;


@PageTitle("Tasks")
@Route("/tasks")
//@RolesAllowed("ADMIN")

public class TaskView extends Div {

    //Crud <TaskEntity> crud ;


    @Autowired
    TaskService taskService;
    private Checkbox completed;
    Grid<TaskEntity> grid = new Grid<>(TaskEntity.class);
    TextField filter = new TextField();
    TaskForm taskForm;



    public TaskView(TaskService taskService)  {
       /* this.taskService=taskService;
        crud = new Crud<>(TaskEntity.class,
                createGrid(),
                createEditor());
        add(crud);*/
        this.taskService=taskService;
        setSizeFull();
        configureGrid();
        configureForm();
        add(getToolBar(),getContent());
        uppdateFromFilter();


    }

   /* private CrudEditor<TaskEntity> createEditor() {
        TextField title = new TextField("Title");
        DatePicker startDate = new DatePicker("Start Date");
        DatePicker deadline = new DatePicker("Deadline");
         TextField completed = new TextField ("Completed");
        TextField owner = new TextField("Owner");
        FormLayout form = new FormLayout(title,startDate,deadline,completed,owner);


        Binder<TaskEntity> binder = new Binder<>(TaskEntity.class);
        binder.forField(title).asRequired().bind(TaskEntity::getTitle, TaskEntity::setTitle);
        binder.forField(startDate).asRequired().bind(TaskEntity::getStartDate,TaskEntity::setStartDate);
        binder.forField(deadline).asRequired().bind(TaskEntity::getDeadline,TaskEntity::setDeadline);
        binder.forField(completed).asRequired().bind(TaskEntity::getTitle, TaskEntity::setTitle);
        binder.forField(completed).asRequired().bind(taskEntity -> taskEntity.getOwner().getUsername(), (taskEntity1) -> taskEntity1.setOwner());

    return null;
    }

    private Object createGrid() {
        Grid<TaskEntity> grid = new Grid<>();
        Crud.addEditColumn(grid);
        grid.addColumn(TaskEntity::getTitle).setHeader("Title");
        grid.addColumn(TaskEntity::getStartDate,"Start Date");
        grid.addColumn(TaskEntity::getDeadline,"Deadline");
        grid.addColumn(TaskEntity::isCompleted,"Completed");
        grid.addColumns("owner");
        return grid;
    }*/

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
        filter.setPlaceholder("Filter by title");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.LAZY);
        filter.addValueChangeListener(e -> uppdateFromFilter());
        Button addTaskBtn = new Button("Add task");
        return new HorizontalLayout(filter,addTaskBtn);
    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.setColumns("title","startDate","deadline","completed");
        grid.addColumn(u->u.getOwner().getUsername()).setHeader("Owner");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

    }


}


