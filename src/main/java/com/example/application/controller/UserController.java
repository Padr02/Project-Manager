package com.example.application.controller;

import com.example.application.data.entity.UserEntity;
import com.example.application.data.service.TaskService;
import com.example.application.data.service.UserService;
import com.example.application.dto.DtoConverter;
import com.example.application.dto.UserRequestDTO;
import com.example.application.dto.UserResponseDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    TaskService taskService;

    @Autowired
    DtoConverter dtoConverter;

    @GetMapping
    public List<UserResponseDTO> getUsers(@RequestParam(required = false)String username) {
               return userService.findAll(username)
                       .stream()
                       .map(user -> dtoConverter.entityToResponseDTO(user))
                       .toList();
    }

    @PostMapping
    public UserResponseDTO addUser(@RequestBody UserRequestDTO userRequestDTO) {
        UserEntity userIn = dtoConverter.RequestDtoToEntity(userRequestDTO);
        userService.saveUser(userIn);
        UserEntity userOut = userService.saveUser(userIn);
        // TODO: If sats där vi returnerar en Notification om att användare redan finns. Under huven görs redan en kontroll för tabellen tillåter bara unika användare
        return  dtoConverter.entityToResponseDTO(userOut);
    }

    @DeleteMapping("/{id}")
    public void deleteUser( @PathVariable("id") UUID id) {
       userService.delete(id);

    }

    @Data
    public static class UserForm { //formulär för skapande av user eftersom attributerna har hash o salt
        String username;
        String password;
    }
}

