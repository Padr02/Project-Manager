package com.example.application.data.views;

import com.example.application.data.RoleEnum;
import com.example.application.data.entity.UserEntity;
import com.example.application.data.service.UserService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@PageTitle("Register")
@Route(value = "/register")
@AnonymousAllowed

public class RegisterView extends Composite {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserService userService;

    TextField username = new TextField("Username");
    EmailField email = new EmailField("Enter your email address");
    PasswordField password1 = new PasswordField("Create a password");
    PasswordField password2 = new PasswordField("Confirm the password");

    public RegisterView() {
      initContent();

    }

    @Override
    protected Component initContent() {
        username.isRequired();
        username.setLabel("Username");
        username.setSizeFull();
        username.setAutofocus(true);
        email.setLabel("Email");
        email.setPattern("^(.+)@(.+)$");
        email.setSizeFull();
        email.setAutofocus(true);
        password1.setLabel("Password");
        password1.isRequired();
        password1.setHelperText("A password must be at least 10 characters. It has to have at least one letter and one digit.");
        password1.setPattern("^(?=.*[0-9])(?=.*[a-zA-Z]).{10}.*$");
        password1.setSizeFull();
        password2.setLabel("Password");
        password2.setSizeFull();

        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        verticalLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        verticalLayout.setWidth("25rem");
        verticalLayout.setClassName("registerform");

        H2 registerText = new H2("Register");

        Button registerBtn = new Button("Confirm");
        registerBtn.setSizeFull();
        registerBtn.setHeight("2.5rem");
        registerBtn.addClickListener(event -> {
            register(username.getValue(),email.getValue() , password1.getValue(), password2.getValue());
            saveUser(username.getValue(), email.getValue(), password1.getValue(), password2.getValue());
        });
        verticalLayout.add(registerText, username, email, password1, password2, registerBtn);

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setSizeFull();
        horizontalLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        horizontalLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        horizontalLayout.add(verticalLayout);

        return horizontalLayout;
    }

    private void saveUser(String username, String email, String password1, String password2) {
        try {
          if (password1.equals(password2) && password1.matches("^(?=.[0-9])(?=.[a-zA-Z]).{10}.*$")) {
              userService.saveUser(new UserEntity(username, email, RoleEnum.USER, passwordEncoder.encode(password1)));
              UI.getCurrent().navigate("/login");
              Notification.show("Successful registration! Welcome " + username);
          }
        } catch (Exception e) {
            Notification.show("Registration failed");
        }
    }

    private void register(String username1, String email, String password1, String password2) {
        if (username1.trim().isEmpty()) {
            Notification.show("You have to enter a username");
        } else if (userService.getUsername(username1) ){
            Notification.show("The username is not unique");
        } else if (password1.length() < 10) {
            Notification.show("The password must be at least 10 characters long");
        } else if (!password1.matches("^(?=.*[0-9])(?=.*[a-zA-Z]).{10}.*$")) {
            Notification.show("The password format is wrong!");
        } else if (password1.isEmpty()) {
            Notification.show("You have to enter a password");
        } else if (!password1.equals(password2)) {
            Notification.show("The passwords do not match");
        } else if (!email.matches("^(.+)@(.+)$")) {
            Notification.show("The email format is incorrect, you need to include @");
        }
    }
}