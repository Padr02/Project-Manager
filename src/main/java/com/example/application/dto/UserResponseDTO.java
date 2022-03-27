package com.example.application.dto;

import com.example.application.data.RoleEnum;
import com.example.application.data.entity.TaskEntity;

import java.util.Set;

public record UserResponseDTO(String username, RoleEnum role) {
}
