package com.example.application.dto;

import com.example.application.data.entity.UserEntity;

import java.time.LocalDate;

public record TaskResponseDTO(boolean completed, String title, LocalDate startDate, LocalDate deadline, UserEntity owner) {
}
