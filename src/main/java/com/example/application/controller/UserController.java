package com.example.application.controller;

import com.example.application.data.entity.RoleEnum;
import com.example.application.data.entity.UserEntity;
import com.example.application.data.service.PcsService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final PcsService userService;
    // CRUD methods
    @GetMapping
    public List<UserEntity> getTasks() {
        return userService.getUsers();
    }
    @GetMapping("/{username}")
    public UserEntity getUserByUsername(@PathVariable("username") String userName){
        return userService.getUsers()
                .stream()
                .filter(user -> Objects.equals(user.getUsername(), userName))
                .findFirst()
                .orElseThrow();
    }
    @PostMapping
    public UserEntity addUser(@RequestBody UserForm userForm){
        //måste skapa kontroll, så samma user inte kan sparas flera gånger eller om den redan finns
        UserEntity user1 =new UserEntity(userForm.username, userForm.password, RoleEnum.USER);
       return userService.saveUser(user1);
    }

    @Data
    public static class UserForm { //formulär för skapande av user eftersom attributerna har hash o salt
        String username;
        String password;
    }



}
