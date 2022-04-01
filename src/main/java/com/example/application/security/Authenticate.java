package com.example.application.security;

import com.example.application.data.RoleEnum;
import com.example.application.data.entity.UserEntity;
import com.example.application.data.repository.UserRepository;
import com.example.application.data.service.UserService;
import com.example.application.data.views.LoginView;
import com.example.application.data.views.MainView;
import com.example.application.data.views.TaskView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class Authenticate {

    private static final String LOGIN = "/";
    private static final String TASKS = "/tasks";
    private static final String LOGOUT = "/logout";


    public record AuthorizedRoutes(String route, String name, Class<? extends Component> view) {
    }

    private final UserService userService;
    private final UserRepository userRepository;

    public Authenticate(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    public void authenticate(String username, String password) throws Exception {
        UserEntity user = userRepository.getUserByUsername(username);
        if (user != null && user.checkPassword(password)) {
            VaadinSession.getCurrent().setAttribute(UserEntity.class, user);
            createRoutes(user.getRole());

        } else {
            throw new Exception();
        }
    }

    private void createRoutes(RoleEnum role) {
        getAuthorizedRoutes(role)
                .forEach(route -> RouteConfiguration.forSessionScope()
                        .setRoute(route.route, route.view, MainView.class));
    }

    private List<AuthorizedRoutes> getAuthorizedRoutes(RoleEnum role) {
        var routes = new ArrayList<AuthorizedRoutes>();
        if (role.equals(RoleEnum.USER)) {
            routes.add(new AuthorizedRoutes("/login", "Login", LoginView.class));
            routes.add(new AuthorizedRoutes("/tasks", "Tasks", TaskView.class));
        } else if (role.equals(RoleEnum.ADMIN)) {
            routes.add(new AuthorizedRoutes("/login", "Login", LoginView.class));
            routes.add(new AuthorizedRoutes("/tasks", "Tasks", TaskView.class));
        }
        return routes;
    }

    public void register(String username, String password) {
        userRepository.save(new UserEntity(username, password, RoleEnum.USER ));
    }

}

