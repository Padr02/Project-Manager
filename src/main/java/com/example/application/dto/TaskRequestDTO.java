package com.example.application.dto;

import com.example.application.data.entity.UserEntity;

import java.time.LocalDate;
import java.util.UUID;

public record TaskRequestDTO(boolean completed, String title, LocalDate startDate, LocalDate deadline, String owner) {

}
