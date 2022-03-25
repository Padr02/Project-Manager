package com.example.application.dto;

import java.time.LocalDate;
import java.util.UUID;

public record TaskRequestDTO(UUID ownerId, boolean completed, String title, LocalDate startDate, LocalDate deadline) {

}
