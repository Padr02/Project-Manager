package com.example.application.data.views;

import com.example.application.security.SecurityUtils;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.login.AbstractLogin;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@PageTitle("PCS Login")
@AnonymousAllowed
@Route(value = "/login")
public class LoginView extends Composite<VerticalLayout> implements BeforeEnterObserver,ComponentEventListener<AbstractLogin.LoginEvent> {

    private static final String LOGIN_SUCCESSFUL_URL = "/tasks";

    private LoginForm loginForm = new LoginForm();

    public LoginView() {
        VerticalLayout vertical = getContent();
        vertical.add(new H1("Project Manager 1.0"));
        vertical.add(new Paragraph("PCS Portal"));
        loginForm.addLoginListener(this);
        vertical.add(loginForm);
        vertical.setSizeFull();
        vertical.setAlignItems(FlexComponent.Alignment.CENTER);
        vertical.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
    }

    /**
    * Check if user is authenticated by checking whether the URL contains "error" as parameter before granting access to the requested resource
    * @param event
    */
    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (event.getLocation()
                .getQueryParameters()
                .getParameters()
                .containsKey("error")) {
            loginForm.setError(true);
            // FRÅGETECKEN: Behöver vi sätta liknande för taskform i taskview för att begränsa behörigheten till resurser till rätt användare/ägare?
        }
    }

     /**
     * Check if the user is authenticated and redirect to a resource depending on the outcome and provides a CSRF token after successful login
     *
     * @param event
     */
     @Override
     public void onComponentEvent(AbstractLogin.LoginEvent event) {
         boolean authenticated = SecurityUtils.authenticate(event.getUsername(), event.getPassword());
         if (authenticated) {
             UI.getCurrent().getPage().setLocation(LOGIN_SUCCESSFUL_URL);
         } else {
             loginForm.setError(true);
         }
     }
}
