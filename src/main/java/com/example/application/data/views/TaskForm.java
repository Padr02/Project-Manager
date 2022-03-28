package com.example.application.data.views;

import com.example.application.data.entity.TaskEntity;
import com.example.application.data.entity.UserEntity;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import org.springframework.beans.BeanUtils;

import java.util.List;


public class TaskForm extends FormLayout {


    TextField title = new TextField("Title");
    DatePicker deadline = new DatePicker("Deadline");
    ComboBox<UserEntity> owner = new ComboBox<>("Owner");
    Binder<TaskEntity> binder = new BeanValidationBinder<>(TaskEntity.class);
    TaskEntity task;

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button cancel = new Button("Cancel");

    public TaskForm(List<UserEntity> users) {
        owner.setItems(users);
        owner.setItemLabelGenerator(UserEntity::getUsername);
        add(title,deadline,owner, createBtnLayout());
        binder.bindInstanceFields(this);
    }

    public void setTask(TaskEntity task){
        this.task=task;
        binder.readBean(task);

    }

    private Component createBtnLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);
        return new HorizontalLayout(save,delete,cancel);
    }

}

