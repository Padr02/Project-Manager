package com.example.application.data;

import com.example.application.data.views.TaskForm;
import com.vaadin.flow.component.ComponentEvent;

public abstract class FormEvent extends ComponentEvent<TaskForm> {



    public FormEvent(TaskForm source, boolean fromClient) {
        super(source, fromClient);
    }


    //vi vill!!!


}
