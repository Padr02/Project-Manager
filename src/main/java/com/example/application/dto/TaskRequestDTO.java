package com.example.application.dto;

import java.time.LocalDate;

public record TaskRequestDTO(boolean completed, String title, LocalDate startDate, LocalDate deadline, String owner) { }
