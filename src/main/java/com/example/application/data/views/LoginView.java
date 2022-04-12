package com.example.application.data.views;

import com.example.application.security.SecurityUtils;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.login.AbstractLogin;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.auth.AnonymousAllowed;


@PageTitle("PCS Login")
@AnonymousAllowed
@Route(value = "/login")
public class LoginView extends Composite<VerticalLayout> implements ComponentEventListener<AbstractLogin.LoginEvent> {

    private static final String LOGIN_SUCCESSFUL_URL = "/tasks";

    private LoginForm loginForm = new LoginForm();

    public LoginView() {
        VerticalLayout vertical = getContent();
        vertical.add(new H1("Project Manager 1.0"));
        vertical.add(new Paragraph("PCS Portal"));
        loginForm.addLoginListener(this);
        RouterLink link = new RouterLink("Not registered? Click here", RegisterView.class);
        link.setClassName("login-link");
        vertical.add(loginForm, link);
        vertical.setSizeFull();
        vertical.setAlignItems(FlexComponent.Alignment.CENTER);
        vertical.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        loginForm.setForgotPasswordButtonVisible(false);
    }

    /**
    * Check if user is authenticated by checking whether the URL contains "error" as parameter before granting access to the requested resource
    * @param event
    */
 /*  @Override
    public void beforeEnter(BeforeEnterEvent event) {
        System.out.println(" 1 beforeEnterevent");
       System.out.println(event.getLocation().getQueryParameters().getParameters());
        if (event.getLocation()
                .getQueryParameters()
                .getParameters()
                .containsKey("error")) {
            loginForm.setError(true);
        }
    }*/

     /**
     * Check if the user is authenticated and redirect to a resource depending on the outcome and provides a CSRF token after successful login
     *
     * @param event
     */
     @Override
     public void onComponentEvent(AbstractLogin.LoginEvent event) {
         boolean authenticated = SecurityUtils.authenticate(event.getUsername(), event.getPassword());
         if (authenticated) {
             Notification.show("Welcome to PCS " + SecurityUtils.getName());
             UI.getCurrent().getPage().setLocation(LOGIN_SUCCESSFUL_URL);
         } else {
             loginForm.setError(true);
         }
     }
}
