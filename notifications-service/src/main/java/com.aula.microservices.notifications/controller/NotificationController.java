package com.aula.microservices.notifications.controller;

import com.aula.microservices.common.response.ApiResponse;
import com.aula.microservices.notifications.dto.NotificationRequest;
import com.aula.microservices.notifications.dto.NotificationResponse;
import com.aula.microservices.notifications.service.NotificationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService service;

    public NotificationController(NotificationService service) {
        this.service = service;
    }

    @PostMapping
    public ApiResponse<NotificationResponse> send(@Valid @RequestBody NotificationRequest request) {
        return new ApiResponse<>(true, "Notificación procesada", service.send(request));
    }
}