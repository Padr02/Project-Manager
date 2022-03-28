package com.example.application.data.service;

import com.example.application.data.entity.TaskEntity;
import com.example.application.data.entity.UserEntity;
import com.example.application.data.repository.TaskRepository;
import com.example.application.data.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    TaskService taskService;
    TaskRepository taskRepository;

    private UserService(@Autowired UserRepository userRepository){
        this.userRepository= userRepository;
    }

    public List<UserEntity> getUsers(){
        return userRepository.findAll();
    }

    public Optional<UserEntity> getUserById(UUID id){
        return userRepository.findById(id);
    }

    public UserEntity saveUser(UserEntity user){
        return userRepository.save(user);
    }

    // TODO: Felhantering. Måste meddela användare att de inte kan ta bort task ifall de har icke-avslutade tasks. Något att lösa eller skippa?
    // Databasen kommer att sätta stopp för att det finns constraint,men användaren vet ju inte det.
    public void delete(UUID id) {
        userRepository.deleteById(id);
    }

    public List<UserEntity> findAll(String username) {
        if (username == null) {
            return userRepository.findAll();
        } else {
            return this.getByUsername(username);
        }
    }

    public UserEntity getUsername(String username){
        return userRepository.findAll()
                .stream()
                .filter(u->u.getUsername()
                        .equals(username))
                .findFirst()
                .get();
    }

    public List<UserEntity> getByUsername(String username){
      return userRepository.findAll().stream()
              .filter(user -> user.getUsername().equals(username)).toList();
    }


    // TODO delete method

    // TODO update method
}


