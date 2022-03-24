package com.example.application.data.views;

import com.example.application.security.Authenticate;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Login")
@Route("/login")
public class LoginView extends Composite<VerticalLayout> {


    public LoginView(Authenticate authenticate) {
        VerticalLayout vertical = getContent();
        setId("login-view");
        vertical.add(new H1("Welcome to Project Manager 1.0"));
        vertical.add(new Paragraph("Project created by PCS"));
        LoginForm loginForm= new LoginForm();
        loginForm.addLoginListener(event -> {
                try{
                    authenticate.authenticate(event.getUsername(), event.getPassword());
                    UI.getCurrent().navigate("/tasks");
                }catch (Exception e){
                    Notification.show("wroooooong!");
                }

        });

        vertical.add(loginForm);


       /* var username = new TextField("Username");
        var password = new PasswordField("Password");

        vertical.add(new H1("Welcome to Project Manager 1.0"),
                new Paragraph("Project created by PCS"),
                username,
                password,
                new Button("Login"));*/
        vertical.setSizeFull();
        vertical.setAlignItems(FlexComponent.Alignment.CENTER);
        vertical.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
                     /*
                        event -> {
                    try{
                        authenticate.authenticate(username.getValue(),password.getValue());
                        UI.getCurrent().navigate("login");
                    }catch (Exception e){
                        Notification.show("wroooooong!");
                    }
                }));*/
    }
}
