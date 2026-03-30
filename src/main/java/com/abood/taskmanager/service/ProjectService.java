package com.abood.taskmanager.service;

import com.abood.taskmanager.dto.ProjectDTO;
import com.abood.taskmanager.dto.ProjectResponseDTO;
import com.abood.taskmanager.entity.Project;
import com.abood.taskmanager.entity.Workspace;
import com.abood.taskmanager.repository.ProjectRepository;
import com.abood.taskmanager.repository.WorkspaceRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final WorkspaceRepository workspaceRepository;

    public ProjectService(ProjectRepository projectRepository, WorkspaceRepository workspaceRepository) {
        this.projectRepository = projectRepository;
        this.workspaceRepository = workspaceRepository;
    }

    public ProjectResponseDTO createProject(ProjectDTO projectDTO) {
        Workspace workspace = workspaceRepository.findById(projectDTO.getWorkspaceId()).orElseThrow(() -> new RuntimeException("Workspace not found"));

        Project project = Project.builder().
                name(projectDTO.getName()).
                description(projectDTO.getDescription()).workspace(workspace).build();

        Project savedProject = projectRepository.save(project);

        // Convert the entity to DTO before returning
        return ProjectResponseDTO.fromEntity(savedProject);
    }
}
