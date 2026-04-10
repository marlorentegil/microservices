package com.aula.microservices.notifications.service;

import com.aula.microservices.notifications.dto.NotificationRequest;
import com.aula.microservices.notifications.dto.NotificationResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationService {

    public NotificationResponse send(NotificationRequest request) {
        return new NotificationResponse(
                "SENT",
                "Notificación enviada para el proyecto " + request.projectId(),
                LocalDateTime.now()
        );
    }
}