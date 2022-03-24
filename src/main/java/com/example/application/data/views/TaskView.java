package com.example.application.data.views;

import com.example.application.data.entity.TaskEntity;
import com.example.application.data.service.TaskService;
import com.example.application.data.views.validation.ValidationMessage;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.editor.Editor;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Route("/tasks")
@PageTitle("Tasks")

public class TaskView extends VerticalLayout {

    @Autowired
    TaskService taskService;

    public TaskView(TaskService taskService)  {

        this.taskService = taskService;
        ValidationMessage nameValidationmessage = new ValidationMessage();
        ValidationMessage ownerValidationmessage = new ValidationMessage();

        Grid<TaskEntity> grid = new Grid<>(TaskEntity.class, false);

        Editor<TaskEntity> editor = grid.getEditor();

        Grid.Column<TaskEntity> checkColumn = grid
                .addColumn(TaskEntity::isCompleted).setHeader("Completed");

        Grid.Column<TaskEntity> titleColumn = grid
                .addColumn(TaskEntity::getTitle).setHeader("Title");

        Grid.Column<TaskEntity> ownerColumn = grid
                .addColumn(TaskEntity::getOwner).setHeader("Owner");

        Grid.Column<TaskEntity> startDateColumn = grid
                .addColumn(TaskEntity::getStartDate).setHeader("Start Date");

        Grid.Column<TaskEntity> deadlineColumn = grid
                .addColumn(TaskEntity::getDeadline).setHeader("Deadline");

        Grid.Column<TaskEntity> editColumn = grid.addComponentColumn(task -> {
            Button editBtn = new Button("Edit");
            editBtn.addClickListener(e -> {
                if (editor.isOpen())
                    editor.cancel();
                grid.getEditor().editItem(task);
            });
            return editBtn;
        });

        Grid.Column<TaskEntity> delColumn = grid.addComponentColumn(task -> {
            Button delBtn = new Button("Delete");
            delBtn.addClickListener(e -> {
                System.out.println("Clicked");
               // TODO: Hämta ut id på person och skicka med som parameter till deleteTask
            });
            return delBtn;
        });

        Binder<TaskEntity> binder = new Binder<>(TaskEntity.class);
        editor.setBinder(binder);
        editor.setBuffered(true);

        Checkbox checkbox = new Checkbox(); //storlek??
        binder.forField(checkbox)
                .bind(TaskEntity::isCompleted, TaskEntity::setCompleted);
        checkColumn.setEditorComponent(checkbox);
        // TODO: if-sats: Om checkbox filled (event) - skicka true som parameter till isCompleted()

        TextField titleField = new TextField();
        titleField.setWidthFull();
        binder.forField(titleField)
                .asRequired("You have to provide a first name")
                .withStatusLabel(nameValidationmessage)
                .bind(TaskEntity::getTitle, TaskEntity::setTitle);
        titleColumn.setEditorComponent(titleField);

        TextField ownerField = new TextField();
        ownerField.setWidthFull();
        // TODO: Hämta ut förnamn och efternamn på användare som skapar task
        binder.forField(ownerField)
                .asRequired("Owner must be provided")
                .withStatusLabel(ownerValidationmessage)
                .bind(TaskEntity::getOwner, TaskEntity::setOwner);
        ownerColumn.setEditorComponent(ownerField);

        DatePicker StartDateField = new DatePicker();
        StartDateField.setWidthFull();
        binder.forField(StartDateField)
                .bind(TaskEntity::getStartDate, TaskEntity::setStartDate);
        startDateColumn.setEditorComponent(StartDateField);

        DatePicker Deadline = new DatePicker();
        Deadline.setWidthFull();
        binder.forField(Deadline)
                .bind(TaskEntity::getDeadline, TaskEntity::setDeadline);
        startDateColumn.setEditorComponent(deadlineColumn);

        Button saveBtn = new Button("Save", e -> editor.save());
        Button cancelBtn = new Button(VaadinIcon.CLOSE.create(),
                e -> editor.cancel());
        cancelBtn.addThemeVariants(ButtonVariant.LUMO_ICON, ButtonVariant.LUMO_ERROR);
        HorizontalLayout actions = new HorizontalLayout(saveBtn,cancelBtn);
        actions.setPadding(false);
        editColumn.setEditorComponent(actions);

        editor.addCancelListener(e -> {
            nameValidationmessage.setText("");
            ownerValidationmessage.setText("");
        });

        List<TaskEntity> tasks = taskService.getTasks();
        grid.setItems(tasks);

        getThemeList().clear();
        getThemeList().add("spacing-s");
        add(grid,nameValidationmessage,ownerValidationmessage);
    }
}


