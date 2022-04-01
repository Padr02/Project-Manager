package com.example.application.data.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;

@PageTitle("Main page")
@Route("main")

public class MainView extends AppLayout {

    public MainView() {
        HorizontalLayout navbarLayout = new HorizontalLayout();
        navbarLayout.setWidthFull();

        H1 navbarTitle = new H1("PCS Project Manager 1.0");
        Div divButtons = new Div();
        Button loginButton = new Button("Login", event -> UI.getCurrent().navigate("login"));
        Button signupButton = new Button("Signup", event -> UI.getCurrent().navigate("register"));
        divButtons.add(loginButton, signupButton);

        navbarLayout.add(navbarTitle, divButtons);
        navbarLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        navbarLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        addToNavbar(navbarLayout);
    }
}
