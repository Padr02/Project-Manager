package com.example.application.security;

import com.example.application.data.RoleEnum;
import com.example.application.data.entity.UserEntity;
import com.example.application.data.service.UserService;
import com.example.application.data.views.TaskView;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class Authenticate {

    @Autowired
    UserService userService;

    public class AuthException extends Exception {
    }

    public Authenticate(UserService userService) {
        this.userService = userService;
    }

    public void authenticate (String username, String password) throws AuthException {

        UserEntity user = userService.getUsername(username);
        System.out.println("Användare " + username + " Lösenord " + password);
        if (user.getUsername() != null && user.checkPassword(password)){
            System.out.println(user);
            VaadinSession.getCurrent().setAttribute(UserEntity.class,user);
            createRoute(user.getRole());
        } else {
            System.out.println("Detta är else!");
            throw new AuthException();
        }
    }

    public void createRoute (RoleEnum role) {
        getAuthorizedRoutes(role)
                .stream()
                .forEach(x-> RouteConfiguration.forSessionScope()
                        .setRoute(x.route,x.view));
    }

    public List<Route> getAuthorizedRoutes(RoleEnum role) {
        var route = new ArrayList<Route>();
        if (role.equals(RoleEnum.USER)) {
            route.add(new Route("tasks","Tasks",TaskView.class));
        }
        if (role.equals(RoleEnum.ADMIN)) {
            route.add(new Route("tasks","Tasks", TaskView.class));
        }
        return route;
    }
}
