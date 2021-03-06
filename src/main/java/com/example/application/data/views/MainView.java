package com.example.application.data.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@PageTitle("Main page")
@Route("/")
@AnonymousAllowed
public class MainView extends VerticalLayout {
    HorizontalLayout horizontalBtns = new HorizontalLayout();
    VerticalLayout verticalTitle = new VerticalLayout();

    public MainView() {
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        horizontalLayoutConfig();
        verticalLayoutConfig();
        add(horizontalBtns, verticalTitle);
    }

    private void verticalLayoutConfig() {
        Image image = new Image("/images/pcs.png","PCS-logo");
        verticalTitle.setSizeFull();
        verticalTitle.setAlignItems(Alignment.CENTER);
        verticalTitle.setJustifyContentMode(JustifyContentMode.CENTER);
        verticalTitle.add(image);
    }

    private void horizontalLayoutConfig() {
        Button loginButton = new Button("Login", event -> UI.getCurrent().navigate("login"));
        Button signupButton = new Button("Signup", event -> UI.getCurrent().navigate("register"));
        horizontalBtns.add(loginButton, signupButton);
        horizontalBtns.setJustifyContentMode(JustifyContentMode.END);
        horizontalBtns.setAlignItems(FlexComponent.Alignment.CENTER);
        horizontalBtns.setWidthFull();
    }
}
