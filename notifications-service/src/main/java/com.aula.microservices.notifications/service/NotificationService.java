package com.aula.microservices.notifications.service;

import com.aula.microservices.notifications.dto.NotificationRequest;
import com.aula.microservices.notifications.dto.NotificationResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class NotificationService {

    public NotificationResponse send(NotificationRequest request, boolean slow, boolean fail, boolean randomFail) {
        if (slow) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new IllegalStateException("La simulación lenta fue interrumpida", e);
            }
        }

        if (fail) {
            throw new IllegalStateException("Fallo forzado en notifications-service");
        }

        if (randomFail && ThreadLocalRandom.current().nextBoolean()) {
            throw new IllegalStateException("Fallo aleatorio en notifications-service");
        }

        return new NotificationResponse(
                "SENT",
                "Notificación enviada para el proyecto " + request.projectId(),
                LocalDateTime.now()
        );
    }
}