package com.example.application.data.views;

import com.example.application.data.entity.UserEntity;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

import java.awt.*;
import java.util.List;

public class TaskForm extends FormLayout {
    TextField title = new TextField("Title");
    TextField deadline = new TextField("Deadline");
    ComboBox<UserEntity> owner = new ComboBox<>("Owner");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button cancel = new Button("Cancel");

    public TaskForm(List<UserEntity> users) {
        owner.setItems(users);
        owner.setItemLabelGenerator(UserEntity::getUsername);

        add(title,deadline,owner, createBtnLayout());
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
