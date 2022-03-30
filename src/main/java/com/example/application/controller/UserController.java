package com.example.application.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.application.data.entity.UserEntity;
import com.example.application.data.service.TaskService;
import com.example.application.data.service.UserService;
import com.example.application.dto.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;



@RequiredArgsConstructor
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    TaskService taskService;

    @Autowired
    DtoConverter dtoConverter;

    @GetMapping()
    public List<DtoConverter.UserResponseDTO> getUsers(@RequestParam(required = false) String username) {
               return   userService.findAll(username)
                       .stream()
                       .map(user -> dtoConverter.entityToResponseDTO(user))
                       .toList();
    }

    @PostMapping
    public DtoConverter.UserResponseDTO addUser(@RequestBody DtoConverter.UserRequestDTO userRequestDTO) {
        UserEntity userIn = dtoConverter.RequestDtoToEntity(userRequestDTO);
        userService.saveUser(userIn);
        UserEntity userOut = userService.saveUser(userIn);

        return  dtoConverter.entityToResponseDTO(userOut);
    }

    @DeleteMapping("/{id}")
    public void deleteUser( @PathVariable("id") UUID id) {
       userService.delete(id);
    }

    @PutMapping("/{id}")
    public DtoConverter.UserResponseDTO updateOwnerById(@PathVariable("id") UUID id, @RequestBody DtoConverter.UserRequestDTO changeUserDTO){
        UserEntity changedUserIn = dtoConverter.RequestDtoToEntity(changeUserDTO);
        UserEntity changedUserOut = userService.updateOwner(id, changedUserIn);
        return dtoConverter.entityToResponseDTO(changedUserOut);
    }

    @Data
    public static class UserForm { //formulär för skapande av user eftersom attributerna har hash o salt
        String username;
        String password;
    }
}

