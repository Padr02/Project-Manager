package com.example.application.data.views;

import com.example.application.security.Authenticate;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Login")
@Route(value = "login")
public class LoginView extends Composite<VerticalLayout> {

    Authenticate authenticate;
    public LoginView(Authenticate authenticate) {
        this.authenticate=authenticate;
        VerticalLayout vertical = getContent();
        vertical.add(new H1("Project Manager 1.0"));
        vertical.add(new Paragraph("PCS Portal"));
        LoginForm loginForm = new LoginForm();
        loginForm.addLoginListener(event -> {
            try {
                authenticate.authenticate(event.getUsername(), event.getPassword());
                UI.getCurrent().navigate("/tasks");
            } catch (Exception e) {
                UI.getCurrent().navigate("/");
                Notification.show("You are not authorized access to the PCS Portal");
            }
        });
        vertical.add(loginForm);
        vertical.setSizeFull();
        vertical.setAlignItems(FlexComponent.Alignment.CENTER);
        vertical.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
    }
}
