package com.example.application.service;

import com.example.application.repository.RoleRepository;
import com.example.application.repository.TaskRepository;
import com.example.application.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PcsService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final RoleRepository roleRepository;




}


