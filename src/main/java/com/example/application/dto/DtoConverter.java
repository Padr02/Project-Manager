package com.example.application.dto;

import com.example.application.data.RoleEnum;
import com.example.application.data.entity.TaskEntity;
import com.example.application.data.entity.UserEntity;
import com.example.application.data.repository.UserRepository;
import com.example.application.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class DtoConverter {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    public TaskEntity RequestDtoToEntity(TaskRequestDTO taskRequestDTO) {
        return new TaskEntity(false,
                            taskRequestDTO.title(),
                            LocalDate.now(),
                            taskRequestDTO.deadline(),
                            userRepository.findById(taskRequestDTO.ownerId()).orElseThrow());
    }

    public TaskResponseDTO entityToResponseDTO(TaskEntity taskEntity) {
        System.out.println(taskEntity.getOwner().getId());
        return new TaskResponseDTO(
                taskEntity.getId(),
                taskEntity.isCompleted(),
                taskEntity.getTitle(),
                taskEntity.getStartDate(),
                taskEntity.getDeadline(),
                taskEntity.getOwner().getUsername(),
                taskEntity.getOwner().getId()
        );
    }

    public UserEntity RequestDtoToEntity(UserRequestDTO userRequestDTO){
        System.out.println(userRequestDTO.password());
        return new UserEntity(userRequestDTO.username(),
                userRequestDTO.password(),
                RoleEnum.USER);
    }

    public UserResponseDTO entityToResponseDTO(UserEntity userEntity) {
        return new UserResponseDTO(
                userEntity.getUsername(),
                userEntity.getId(),
                userEntity.getRole()
        );
    }
}
