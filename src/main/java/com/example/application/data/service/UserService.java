package com.example.application.data.service;

import com.example.application.data.entity.UserEntity;
import com.example.application.data.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    private UserService(@Autowired UserRepository userRepository) {
        this.userRepository= userRepository;
    }

    public List<UserEntity> getUsers(){
        return userRepository.findAll();
    }

    public Optional<UserEntity> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    public UserEntity saveUser(UserEntity user) {
        return userRepository.save(user);
    }

    public boolean getOnlyUsername(String username) {
       UserEntity currUser = userRepository.findByUsername(username).orElseThrow();
       if (currUser == null) {
           return false;
       }
       return true;
    }

    public boolean getUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

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

    public UserEntity findUser(String username) {
        return userRepository.findAll()
                .stream()
                .filter(user -> user.getUsername()
                        .equals(username))
                .findFirst()
                .orElseThrow();
    }

    public List<UserEntity> getByUsername(String username){
      return userRepository.findAll().stream()
              .filter(user -> user.getUsername().equals(username)).toList();
    }

    public UserEntity updateOwner(UUID id, UserEntity updatedOwnerIn) {
        UserEntity currOwner = userRepository.findById(id).orElseThrow();
        BeanUtils.copyProperties(updatedOwnerIn, currOwner,  "id");
        userRepository.save(currOwner);
        return currOwner;
    }
}


