package com.example.application.dto;


import com.example.application.data.entity.TaskEntity;
import com.example.application.data.entity.UserEntity;
import com.example.application.data.repository.TaskRepository;
import com.example.application.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DtoConverter  {

    @Autowired
    UserRepository userRepository;

    public TaskEntity RequestDtoToEntity(TaskRequestDTO taskRequestDTO) {

        UserEntity owner = userRepository
                .findById(taskRequestDTO.ownerId())
                .orElseThrow();

        return new TaskEntity(
                taskRequestDTO.completed(),
                taskRequestDTO.title(),
                taskRequestDTO.startDate(),
                taskRequestDTO.deadline(),
                owner
        );
    }

    public TaskResponseDTO entityToResponseDTO(TaskEntity task) {
        return new TaskResponseDTO(
                task.isCompleted(),
                task.getTitle(),
                task.getStartDate(),
                task.getDeadline(),
                task.getOwner()
        );
    }
}
