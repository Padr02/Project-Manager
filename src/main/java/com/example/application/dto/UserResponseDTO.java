package com.example.application.dto;

import com.example.application.data.RoleEnum;
import java.util.UUID;

public record UserResponseDTO(String username, RoleEnum role, UUID id) { }
