package com.example.application.data;

import com.example.application.data.entity.TaskEntity;
import com.example.application.data.views.TaskForm;
import com.vaadin.flow.component.ComponentEvent;

public abstract class FormEvent extends ComponentEvent<TaskForm> {

    private TaskEntity task;

    public FormEvent(TaskForm source, TaskEntity task) {
        super(source, false);
        this.task = task;
    }

    public TaskEntity getTask() {
        return task;
    }

    public static class SaveEvent extends FormEvent {
        public SaveEvent(TaskForm source, TaskEntity task) {
            super(source, task);

        }
    }

    public static class DeleteEvent extends FormEvent {
        public DeleteEvent(TaskForm source, TaskEntity task) {
            super(source, task);
        }
    }

    public static class CloseEvent extends FormEvent {
        public CloseEvent(TaskForm source) {
            super(source, null);
        }
    }

}

