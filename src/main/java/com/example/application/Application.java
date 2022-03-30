package com.example.application;

import com.example.application.data.RoleEnum;
import com.example.application.data.entity.TaskEntity;
import com.example.application.data.entity.UserEntity;
import com.example.application.data.repository.TaskRepository;
import com.example.application.data.repository.UserRepository;
import com.example.application.data.views.LoginView;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.AppShellSettings;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.spring.security.VaadinWebSecurityConfigurerAdapter;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.time.LocalDate;

/**
 * The entry point of the Spring Boot application.
 *
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 *
 */
@SpringBootApplication
@Theme(value = "myapp", variant = Lumo.DARK)
@PWA(name = "My App", shortName = "My App", offlineResources = {"images/logo.png"})
@NpmPackage(value = "line-awesome", version = "1.3.0")
public class Application extends VaadinWebSecurityConfigurerAdapter implements AppShellConfigurator {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        setLoginView(http,LoginView.class);
    }

    @Override
    @Bean
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return new InMemoryUserDetailsManager(
                User.withUsername("pavel").password("{noop}hejsan")
                        .roles("USER")
                        .build()
        );
    }

    @Bean
    public CommandLineRunner run(UserRepository userRepository, TaskRepository taskRepository){
        return args -> {

            //taskRepository.save(new TaskEntity(false,"Testigen",LocalDate.now(),LocalDate.now(),userRepository.save(new UserEntity("connie","hejsan",RoleEnum.ADMIN))));
            //taskRepository.save(new TaskEntity(false,"Testigen2",LocalDate.now(),LocalDate.now(),userRepository.save( new UserEntity("sebbe","hejsan",RoleEnum.USER))));
            //taskRepository.save(new TaskEntity(false,"Testigen3",LocalDate.now(),LocalDate.now(),userRepository.save( new UserEntity("pavel","hejsan",RoleEnum.USER))));

        };
    }
}
