package com.example.application.data.service;

import com.example.application.data.entity.RoleEntity;
import com.example.application.data.entity.TaskEntity;
import com.example.application.data.entity.UserEntity;
import com.example.application.data.repository.RoleRepository;
import com.example.application.data.repository.TaskRepository;
import com.example.application.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PcsService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final RoleRepository roleRepository;

    public List<TaskEntity> getTasks() {
    return taskRepository.findAll();
    }
    public List<UserEntity> getUsers(){
        return userRepository.findAll();
    }

    public UserEntity saveUser(UserEntity user){
        return userRepository.save(user);
    }
    public TaskEntity saveTask(TaskEntity task){
        return taskRepository.save(task);
    }
    public RoleEntity saveRole(RoleEntity role){
        return roleRepository.save(role);
    }

    public void deleteTask(Integer id) {
        taskRepository.deleteById(id);
    }

}


