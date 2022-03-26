package com.example.application.controller;

import com.example.application.data.RoleEnum;
import com.example.application.data.entity.UserEntity;
import com.example.application.data.repository.UserRepository;
import com.example.application.data.service.UserService;
import com.example.application.dto.DtoConverter;
import com.example.application.dto.UserResponseDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    DtoConverter dtoConverter;

    @GetMapping
    public List<UserResponseDTO> getUsers(@RequestParam(required = false)String username) {
               return userService.findAll(username)
                       .stream()
                       .map(user -> dtoConverter.entityToResponseDTO(user))
                       .toList();
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
        UserEntity user1 =new UserEntity(userForm.username, userForm.password,RoleEnum.USER);
       return userService.saveUser(user1);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") UUID id){
        userService.delete(id);
    }

    @Data
    public static class UserForm { //formulär för skapande av user eftersom attributerna har hash o salt
        String username;
        String password;
    }

}

