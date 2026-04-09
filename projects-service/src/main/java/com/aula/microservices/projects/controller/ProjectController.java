package com.aula.microservices.projects.controller;

import com.aula.microservices.common.response.ApiResponse;
import com.aula.microservices.projects.dto.CreateProjectRequest;
import com.aula.microservices.projects.dto.ProjectResponse;
import com.aula.microservices.projects.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService service;

    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ProjectResponse> create(@Valid @RequestBody CreateProjectRequest request) {
        return new ApiResponse<>(true, "Proyecto creado correctamente", service.create(request));
    }

    @GetMapping
    public ApiResponse<List<ProjectResponse>> findAll() {
        return new ApiResponse<>(true, "Listado de proyectos", service.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<ProjectResponse> findById(@PathVariable String id) {
        return new ApiResponse<>(true, "Proyecto encontrado", service.findById(id));
    }

    @GetMapping("/owner/{ownerId}")
    public ApiResponse<List<ProjectResponse>> findByOwnerId(@PathVariable String ownerId) {
        return new ApiResponse<>(true, "Proyectos del usuario", service.findByOwnerId(ownerId));
    }
}