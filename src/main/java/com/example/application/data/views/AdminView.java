package com.example.application.data.views;

import com.example.application.data.RoleEnum;
import com.example.application.data.entity.UserEntity;
import com.example.application.data.service.UserService;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import javax.annotation.security.PermitAll;
import java.util.List;

@PageTitle("Admin View")
@PermitAll
@Route(value = "/admin", layout = Navbar.class)
public class AdminView extends HorizontalLayout {

    @Autowired
    UserService userService;


    BeanValidationBinder<UserEntity> binder = new BeanValidationBinder<>(UserEntity.class);

    public AdminView(UserService userService) {

        this.userService = userService;
        setSizeFull();
        add(verticalConfig());
    }

    private Component displayUsers(List<UserEntity> users) {
        Select<UserEntity> owner = new Select<>();
        owner.setLabel("Name of user/admin:");
        owner.setItemLabelGenerator(UserEntity::getUsername);
        owner.setItems(users);
        return owner;
    }

    private Component radioConfig() {
        RadioButtonGroup<RoleEnum> radioGroup = new RadioButtonGroup<>();
        radioGroup.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        radioGroup.setItems(RoleEnum.USER,RoleEnum.ADMIN);
        radioGroup.setId("usersId");
        radioGroup.addValueChangeListener(e ->{

    });

        binder.bind(radioGroup,UserEntity::getRole,UserEntity::setRole);

        return radioGroup;
    }

    private Component radioConfige() {
        RadioButtonGroup<RoleEnum> radioGroup = new RadioButtonGroup<>();
        radioGroup.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        radioGroup.setLabel("Role");
        radioGroup.setItems(RoleEnum.ADMIN, RoleEnum.USER);
        radioGroup.setValue(userService.findUser(null).getRole());
        return radioGroup;

        final UserEntity user = new UserEntity();
        Binder<UserEntity> user


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
        return button;
    }



}

