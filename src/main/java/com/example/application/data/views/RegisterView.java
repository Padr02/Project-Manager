package com.example.application.data.views;
/*
import com.example.application.security.Authenticate;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Register")
@Route(value = "/register", layout = MainView.class)
public class RegisterView extends Composite {

    private  Authenticate authenticate;

    @Override
    protected Component initContent() {
                TextField username = new TextField("Username");
                EmailField email = new EmailField("Enter your email address");
                PasswordField password1 = new PasswordField("Create a password");
                PasswordField password2 = new PasswordField("Confirm the password");

        return new VerticalLayout(
                new H2("Register"),
                new Button("Register", event -> register(username.getValue(), password1.getValue(), password2.getValue())));
    }

    private void register(String username, String password1, String password2) {
        if (username.trim().isEmpty()) {
            Notification.show("You have to enter a username");
        } else if (password1.length() < 10) {
            Notification.show("The password mst be at least 10 characters long");
        } else if (password1.isEmpty()) {
            Notification.show("You have to enter a password");
        } else if (!password1.equals(password2)) {
            Notification.show("The passwords do not match");
        } else {
            authenticate.register(username, password1);
            Notification.show("Successful registration!");
        } // kan också använda Beanvalidation
    }
}
*/