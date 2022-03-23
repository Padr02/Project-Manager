package com.example.application.data.views;

import com.example.application.security.Authenticate;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.context.annotation.Bean;


@PageTitle("Login")
@Route("/login")
public class LoginView extends Div {


    public LoginView(Authenticate authenticate) {
        setId("login-view");
        var username = new TextField("Username");
        var password = new PasswordField("Password");

        add(new H1("Welcome to Project Manager 1.0"),
                new Paragraph("Project created by PCS"),
                username,
                password,
                new Button("Login"));

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
