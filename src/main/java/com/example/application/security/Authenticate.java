package com.example.application.security;

import com.example.application.data.entity.RoleEnum;
import com.example.application.data.entity.UserEntity;
import com.example.application.data.repository.UserRepository;
import com.example.application.data.views.LoginView;
import com.example.application.data.views.TaskView;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class Authenticate {

    @Autowired
    UserRepository userRepository;
    List<Route> route = new ArrayList<>();

    public Authenticate(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void authenticate (String username, String password ){

        UserEntity user = userRepository.getByUsername(username);
        if (user != null && user.checkPassword(password)){
            VaadinSession.getCurrent().setAttribute(UserEntity.class,user);
            createRoute(user.getRole());
        }
    }

    public void createRoute (RoleEnum role){
        getAuthorizedRoutes(role)
                .forEach(x-> RouteConfiguration.forSessionScope()
                        .setRoute(x.route,x.view));
    }

    public List<Route> getAuthorizedRoutes(RoleEnum role){

        if (role.equals(RoleEnum.USER)){
            route.add(new Route("/tasks","Tasks",TaskView.class));
        }
        if (role.equals(RoleEnum.ADMIN)){
            route.add(new Route("/tasks","Tasks", TaskView.class));
        }
        return route;
    }
}
