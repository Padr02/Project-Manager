package com.example.application.data.views;

import com.example.application.data.service.UserService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("/activate")

public class Activation extends Composite implements BeforeEnterObserver {

    private VerticalLayout layout;

    @Autowired
    private UserService userService;



    @Override
    protected Component initContent() {
        layout = new VerticalLayout();
        return layout;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {

    }
}
