package com.example.application.data.views;

import com.example.application.data.RoleEnum;
import com.example.application.data.entity.UserEntity;
import com.example.application.data.service.UserService;
import com.example.application.security.SecurityUtils;
import com.example.application.security.UserDetailsImpl;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.login.AbstractLogin;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.vote.AbstractAccessDecisionManager;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;


import javax.annotation.security.RolesAllowed;
import java.util.List;



@PageTitle("Admin View")
@Route(value = "/admin", layout = Navbar.class)
@RolesAllowed("ROLE_ADMIN")
public class AdminView extends HorizontalLayout {

   @Autowired
    UserService userService;


    BeanValidationBinder<UserEntity> binder = new BeanValidationBinder<>(UserEntity.class);
    RadioButtonGroup<RoleEnum> radioGroup = new RadioButtonGroup<>();
    Select<UserEntity> owner = new Select<>();

    public AdminView(UserService userService) {
       this.userService = userService;
        setSizeFull();
        add(verticalConfig());
    }

    private Component displayUsers(List<UserEntity> users) {
        owner.addValueChangeListener(event -> radioGroup.setValue(event.getValue().getRole()));
        owner.setLabel("Name of user/admin:");
        owner.setItemLabelGenerator(UserEntity::getUsername);
        owner.setItems(users);
        return owner;
    }

    private Component radioConfig() {
        radioGroup.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        radioGroup.setItems(RoleEnum.USER,RoleEnum.ADMIN);
        radioGroup.setId("usersId");
        binder.bind(radioGroup, UserEntity::getRole, UserEntity::setRole);
        return radioGroup;
    }

    private Component verticalConfig() {
        H2 header = new H2("Admin");
        H3 subheader = new H3("Change access level:");
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setSizeFull();
        verticalLayout.setJustifyContentMode(JustifyContentMode.START);
        verticalLayout.setAlignItems(Alignment.CENTER);
        verticalLayout.add(header, subheader, displayUsers(userService.getUsers()), radioConfig(), saveButton());
        return verticalLayout;
    }

    private Component saveButton() {
        Button button = new Button("Save");
        button.addClickListener(event -> {
            owner.getValue().setRole(radioGroup.getValue());
            userService.saveUser(owner.getValue());
            VaadinSession.getCurrent().getSession().invalidate();
            Notification.show("The access level is now changed for " + owner.getValue().getUsername() + " to level: " + owner.getValue().getRole() + ". Please log in again");
        });
        return button;
    }

}

