package com.example.application.dto;

import com.example.application.data.entity.TaskEntity;
import com.example.application.data.entity.UserEntity;
import com.example.application.data.repository.TaskRepository;
import com.example.application.data.repository.UserRepository;
import com.example.application.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DtoConverter {

    /*@Autowired
    UserRepository userRepository;

    public TaskEntity RequestDtoEntity(TaskRequestDTO taskRequestDTO) {

        UserEntity userEntity = userRepository
                .findById(taskRequestDTO
                        .ownerId()).orElseThrow();

        return new TaskEntity(
                taskRequestDTO.completed(),
                taskRequestDTO.title(),
                taskRequestDTO.startDate(),
                taskRequestDTO.deadline(),
                userEntity);
    }*/



    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    public TaskEntity RequestDtoToEntity(TaskRequestDTO taskRequestDTO) {

        /* UserEntity userEntity = userRepository
                .findByUsername(taskRequestDTO
                        .username()).orElseThrow();*/
        UserEntity owner = userService.getUsername(taskRequestDTO.owner());

        return new TaskEntity(taskRequestDTO.completed(),
                taskRequestDTO.title(),
                taskRequestDTO.startDate(),
                taskRequestDTO.deadline(),
                owner);

    }

    public TaskResponseDTO entityToResponseDTO(TaskEntity taskEntity) {
        return new TaskResponseDTO(
                taskEntity.isCompleted(),
                taskEntity.getTitle(),
                taskEntity.getStartDate(),
                taskEntity.getDeadline(),
                taskEntity.getOwner().getUsername()

        );
    }

    public UserResponseDTO entityToResponseDTO(UserEntity userEntity) {
        return new UserResponseDTO(
                userEntity.getUsername(),
                userEntity.getRole()
        );
    }
}
