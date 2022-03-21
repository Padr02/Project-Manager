package com.example.application.data.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@PageTitle("Welcome to PCS")
@Route("/login")
public class LoginView extends Div {

    public LoginView() {
        setId("login-view");
        var username = new TextField("Username");
        var password = new PasswordField("Password");

        add(new H1("Welcome to Project Manager 1.0"),new Paragraph("Project created by PCS"),username,password,new Button("Login"));

    }
}
