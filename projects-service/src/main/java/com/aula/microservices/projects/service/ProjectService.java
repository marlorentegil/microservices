package com.aula.microservices.projects.service;

import com.aula.microservices.common.exception.ResourceNotFoundException;
import com.aula.microservices.projects.client.UsersClient;
import com.aula.microservices.projects.dto.CreateProjectRequest;
import com.aula.microservices.projects.dto.ProjectDetailsResponse;
import com.aula.microservices.projects.dto.ProjectResponse;
import com.aula.microservices.projects.dto.RemoteUserResponse;
import com.aula.microservices.projects.entity.Project;
import com.aula.microservices.projects.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository repository;
    private final UsersClient usersClient;

    public ProjectService(ProjectRepository repository, UsersClient usersClient) {
        this.repository = repository;
        this.usersClient = usersClient;
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

    public ProjectDetailsResponse findDetailedById(String id) {
        Project project = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado con id " + id));

        RemoteUserResponse owner = usersClient.findUserById(project.getOwnerId());

        return new ProjectDetailsResponse(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getStatus(),
                project.getOwnerId(),
                owner.fullName(),
                owner.email(),
                project.getCreatedAt()
        );
    }

    public List<ProjectResponse> findByOwnerId(String ownerId) {
        return repository.findByOwnerId(ownerId)
                .stream()
                .map(this::toResponse)
                .toList();
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