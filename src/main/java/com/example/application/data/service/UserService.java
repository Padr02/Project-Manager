package com.example.application.data.service;

import com.example.application.data.entity.UserEntity;
import com.example.application.data.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {


    private final UserRepository userRepository;

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
    public void delete(UUID id) {
        userRepository.deleteById(id);
    }

    public int count() {
        return (int) userRepository.count();
    }

    public UserEntity getByUsername(String username){
      return userRepository.findAll().stream()
              .filter(user -> user.getUsername().equals(username)).findFirst().get();
    }
    // TODO delete method

    // TODO update method


}


