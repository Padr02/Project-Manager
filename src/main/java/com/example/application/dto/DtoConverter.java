
package com.example.application.dto;

import com.example.application.data.RoleEnum;
import com.example.application.data.entity.TaskEntity;
import com.example.application.data.entity.UserEntity;
import com.example.application.data.repository.UserRepository;
import com.example.application.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class DtoConverter {

    public record TaskRequestDTO(String title, LocalDate deadline, UUID ownerId) { }
    public record TaskResponseDTO(UUID id, boolean completed, String title, LocalDate startDate, LocalDate deadline, String owner, UUID ownerId) { }
    public record UserRequestDTO(String username, String email, String password) { }
    public record UserResponseDTO(String username, UUID id, RoleEnum role) { }

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
                userRequestDTO.email,
                RoleEnum.USER,
                userRequestDTO.password());
    }

    public UserResponseDTO entityToResponseDTO(UserEntity userEntity) {
        return new UserResponseDTO(
                userEntity.getUsername(),
                userEntity.getId(),
                userEntity.getRole()
        );
    }
}


