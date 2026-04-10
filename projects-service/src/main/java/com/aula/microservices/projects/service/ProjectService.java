package com.aula.microservices.projects.service;

import com.aula.microservices.common.exception.ResourceNotFoundException;
import com.aula.microservices.projects.client.NotificationsClient;
import com.aula.microservices.projects.client.UsersClient;
import com.aula.microservices.projects.dto.*;
import com.aula.microservices.projects.entity.Project;
import com.aula.microservices.projects.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository repository;
    private final UsersClient usersClient;
    private final NotificationsClient notificationsClient;

    public ProjectService(ProjectRepository repository,
                          UsersClient usersClient,
                          NotificationsClient notificationsClient) {
        this.repository = repository;
        this.usersClient = usersClient;
        this.notificationsClient = notificationsClient;
    }

    public ProjectResponse create(CreateProjectRequest request) {
        usersClient.findUserById(request.ownerId());

        Project project = new Project(
                request.name(),
                request.description(),
                "NEW",
                request.ownerId()
        );

        Project saved = repository.save(project);

        notificationsClient.sendNotification(
                new NotificationRequestDto(
                        saved.getId(),
                        saved.getOwnerId(),
                        "Se ha creado el proyecto " + saved.getName()
                )
        );

        return toResponse(saved);
    }

    public List<ProjectResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ProjectResponse findById(String id) {
        Project project = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado con id " + id));
        return toResponse(project);
    }

    private ProjectResponse toResponse(Project project) {
        return new ProjectResponse(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getStatus(),
                project.getOwnerId(),
                project.getCreatedAt()
        );
    }
}