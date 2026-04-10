package com.aula.microservices.projects.service;

import com.aula.microservices.common.exception.ResourceNotFoundException;
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
    private final NotificationOrchestratorService notificationOrchestratorService;

    public ProjectService(ProjectRepository repository,
                          UsersClient usersClient,
                          NotificationOrchestratorService notificationOrchestratorService) {
        this.repository = repository;
        this.usersClient = usersClient;
        this.notificationOrchestratorService = notificationOrchestratorService;
    }

    public ProjectCreationResult create(CreateProjectRequest request) {

        // Validar que el usuario existe (llamada al microservicio users)
        usersClient.findUserById(request.ownerId());

        Project project = new Project(
                request.name(),
                request.description(),
                "NEW",
                request.ownerId()
        );

        Project saved = repository.save(project);

        // Enviar notificación
        NotificationResponseDto notificationResult =
                notificationOrchestratorService.sendNotification(
                        new NotificationRequestDto(
                                saved.getId(),
                                saved.getOwnerId(),
                                "Se ha creado el proyecto " + saved.getName()
                        )
                );

        return new ProjectCreationResult(
                toResponse(saved),
                notificationResult.status(),
                notificationResult.detail()
        );
    }

    public List<ProjectResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ProjectResponse findById(String id) {
        Project project = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Proyecto no encontrado con id " + id));

        return toResponse(project);
    }

    public List<ProjectResponse> findByOwnerId(String ownerId) {
        return repository.findByOwnerId(ownerId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ProjectDetailsResponse findDetailedById(String id) {

        Project project = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Proyecto no encontrado con id " + id));

        RemoteUserResponse user = usersClient.findUserById(project.getOwnerId());

        return new ProjectDetailsResponse(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getStatus(),
                project.getOwnerId(),
                user.fullName(),
                user.email(),
                project.getCreatedAt()
        );
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