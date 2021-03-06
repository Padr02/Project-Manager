package com.example.application.data.views;

import com.example.application.security.SecurityUtils;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabVariant;
import com.vaadin.flow.component.tabs.Tabs;

public class Navbar extends AppLayout {
    Span tasks = new Span("All tasks");
    Span logout = new Span("Logout");
    Span admin = new Span("Admin view");
    Tab LIST_SELECT = new Tab(VaadinIcon.LIST_SELECT.create(), tasks);
    Tab ADMIN = new Tab(VaadinIcon.USER.create(), admin);
    Tab SIGN_OUT = new Tab(VaadinIcon.SIGN_OUT.create(), logout);
    Dialog dialog = new Dialog();

    public Navbar() {
        configureSpan();
        configTabs();
        configDialog();
        addToNavbar(horizontalConfig());
    }

    private void configureSpan() {
        tasks.getStyle().set("font-family", "Ubuntu, sans-serif");
        tasks.getStyle().set("color", "#40E0D0");
        logout.getStyle().set("font-family", "Ubuntu, sans-serif");
        logout.getStyle().set("color", "#40E0D0");
    }

    private Component horizontalConfig() {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        Tabs tabs = tabContainer();
        Image image = new Image("/images/pcs.png","PCS-logo");
        image.addClassName("corner-logo");
        horizontalLayout.add(image, tabs);
        horizontalLayout.setSizeFull();
        horizontalLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        horizontalLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        horizontalLayout.setClassName("navbar");
        return horizontalLayout;
    }

    private Tabs tabContainer() {
        Tabs tabs = new Tabs(LIST_SELECT, ADMIN, SIGN_OUT);
        LIST_SELECT.setClassName("tasks");
        ADMIN.setClassName("admin");
        SIGN_OUT.setClassName("signout");

        tabs.addSelectedChangeListener(event -> {
            if (tabs.getSelectedTab().hasClassName("admin")) {
                if (SecurityUtils.isAdmin()) {
                    UI.getCurrent().navigate(AdminView.class);
                } else {
                    UI.getCurrent().navigate(TaskView.class);
                    Notification.show("You are not allowed access as a regular user");
                }
            }
        });

        tabs.addSelectedChangeListener(event -> {
          if (tabs.getSelectedTab().hasClassName("signout")) {
              dialog.open();
          }
          else if (tabs.getSelectedTab().hasClassName("tasks")) {
              UI.getCurrent().navigate("/tasks");
          }
        });
        if (dialog.isOpened()) {
            if (dialog.isCloseOnOutsideClick()) {
                UI.getCurrent().getPage().reload();
            }
        }
        tabs.getStyle().set("margin", ".5rem");
        tabs.getStyle().set("font-size", "1rem");
        tabs.getStyle().set("box-shadow", "none");

        if (!SecurityUtils.isAdmin()) {
             ADMIN.setVisible(false);
        }
        return tabs;
    }

    private void configTabs() {
        for (Tab tab : new Tab[] { LIST_SELECT, SIGN_OUT, ADMIN }) {
            tab.addThemeVariants(TabVariant.LUMO_ICON_ON_TOP);
        }
    }

    private void configDialog() {
        Button cancelBtn = new Button("Cancel");
        Button logoutBtn = new Button("Log out");
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.add(cancelBtn, logoutBtn);
        Paragraph paragraph = new Paragraph("Are you sure you want to logout?");
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.add(paragraph, horizontalLayout);
        dialog.add(verticalLayout);
        dialog.setModal(false);

        logoutBtn.addClickListener(event -> SecurityUtils.logout());
        cancelBtn.addClickListener(event -> {
            dialog.close();
            UI.getCurrent().getPage().reload();
        });
    }
}
