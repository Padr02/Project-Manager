package com.example.application;

import com.example.application.data.RoleEnum;
import com.example.application.data.entity.TaskEntity;
import com.example.application.data.entity.UserEntity;
import com.example.application.data.repository.TaskRepository;
import com.example.application.data.repository.UserRepository;
import com.example.application.data.views.LoginView;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

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
public class Application extends SpringBootServletInitializer implements AppShellConfigurator {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner run(UserRepository userRepository, TaskRepository taskRepository) {
        return args -> {

           //taskRepository.save(new TaskEntity(false,"Testigen", LocalDate.now(),LocalDate.now(),userRepository.save(new UserEntity("connie",RoleEnum.ADMIN,passwordEncoder.encode("hejsan")))));
           // taskRepository.save(new TaskEntity(false,"Testigen2",LocalDate.now(),LocalDate.now(),userRepository.save( new UserEntity("sebbe",RoleEnum.USER, passwordEncoder.encode(("hejsan"))))));
            //taskRepository.save(new TaskEntity(false,"Testigen3", LocalDate.now(),LocalDate.now(),userRepository.save( new UserEntity("pavel","hejsan", RoleEnum.USER))));

        };
    }
}
