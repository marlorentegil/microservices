package com.aula.microservices.notifications.dto;

import java.time.LocalDateTime;

public record NotificationResponse(
        String status,
        String detail,
        LocalDateTime timestamp
) {
}