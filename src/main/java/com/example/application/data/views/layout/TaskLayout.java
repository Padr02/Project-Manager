package com.example.application.data.views.layout;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import java.awt.*;

public class TaskLayout extends AppLayout {

    public TaskLayout() {
        createHeader();
    }

    private void createHeader() {
    H1 logo = new H1("Vaadin CRM");
    logo.addClassNames("text-l", "m-m");
    logo.setText("HEEEEEJSAN");

    // logout
    Button logout = new Button("Log out");
    HorizontalLayout header = new HorizontalLayout( new DrawerToggle(), logo);

    header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
    header.expand(logo);
    header.setWidth("100%");
    header.addClassNames("py-0", "px-m");

    addToNavbar(header);
    }
}
