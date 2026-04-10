package com.aula.microservices.projects.dto;

import java.time.LocalDateTime;

public record NotificationResponseDto(
        String status,
        String detail,
        LocalDateTime timestamp
) {
}