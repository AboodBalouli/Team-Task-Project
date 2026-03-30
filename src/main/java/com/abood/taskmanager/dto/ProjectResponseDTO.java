package com.abood.taskmanager.dto;

import com.abood.taskmanager.entity.Project;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectResponseDTO {
    private Long id;
    private String name;
    private String description;
    private WorkspaceResponseDTO workspace;

    // Static factory method to convert Project entity to ProjectResponseDTO
    public static ProjectResponseDTO fromEntity(Project project) {
        return ProjectResponseDTO.builder()
                .id(project.getId())
                .name(project.getName())
                .description(project.getDescription())
                .workspace(WorkspaceResponseDTO.fromEntity(project.getWorkspace()))
                .build();
    }
}
