package com.example.application.data.views;

import com.example.application.data.FormEvent;
import com.example.application.data.entity.TaskEntity;
import com.example.application.data.entity.UserEntity;
import com.example.application.security.SecurityUtils;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.shared.Registration;
import java.util.List;

public class TaskForm extends FormLayout {

    TextField title = new TextField("Title");
    DatePicker startDate = new DatePicker("Start date");
    DatePicker deadline = new DatePicker("Deadline");
    Select <UserEntity> owner = new Select<>();
    Binder <TaskEntity> binder = new BeanValidationBinder<>(TaskEntity.class);
    Checkbox completed = new Checkbox("Completed");
    TaskEntity task;
    private ComponentEventBus eventBus = null;
    Dialog dialog = new Dialog();

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button cancel = new Button("Cancel");

    public TaskForm(List<UserEntity> users) {
            owner.setItemLabelGenerator(UserEntity::getUsername);
            owner.setLabel("Owner");
            if (!SecurityUtils.isAdmin()) {
                owner.setEnabled(false);
            }
            owner.setItems(users);
            add(title, startDate, deadline, owner, completed, createBtnLayout());
            binder.bindInstanceFields(this);
            configDialog();
    }

    public void setTask(TaskEntity task) {
        this.task = task;
        binder.readBean(task);
    }

    private Component createBtnLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cancel.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);
        completed.addValueChangeListener(e -> save.setEnabled(binder.isValid()));
        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> {
           if (!title.isEmpty() && !deadline.isEmpty()&& !startDate.isEmpty()) {
            dialog.open();
           }
        });
        cancel.addClickListener(event -> fireEvent(new FormEvent.CloseEvent(this)));
        return new HorizontalLayout(save, delete, cancel);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(task);
            if (task.getStartDate().isAfter(task.getDeadline())) {
                Notification.show("Cannot set a start date that is before deadline date");
            }
            else {
                fireEvent(new FormEvent.SaveEvent(this, task));
            }
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    protected ComponentEventBus getEventBus() {
      if (this.eventBus == null) {
          return this.eventBus = new ComponentEventBus(this);
      }
      return this.eventBus;
    }

    protected <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener) {
        return this.getEventBus().addListener(eventType, listener);
    }

    /**
     * Check if there is an event registered for the given event type
     *
     * @param eventType - the one that is fired from the client
     * @return
     */
    protected boolean hasListener(Class<? extends ComponentEvent> eventType) {
        return this.eventBus != null && this.eventBus.hasListener(eventType);
    }

    private void configDialog() {
        Button cancelBtn = new Button("Cancel");
        Button deleteBtn = new Button("Delete");
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.add(cancelBtn, deleteBtn);
        Paragraph paragraph = new Paragraph("Are you sure you want to delete this task?");
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.add(paragraph, horizontalLayout);
        dialog.add(verticalLayout);

        deleteBtn.addClickListener(event -> {
            fireEvent(new FormEvent.DeleteEvent(this, task));
            dialog.close();
        });
        cancelBtn.addClickListener(event -> dialog.close());
    }
}

