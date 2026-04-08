package com.abood.taskmanager.dto;

import com.abood.taskmanager.entity.Workspace;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkspaceResponseDTO {
    private Long id;
    private String name;
    private String description;
    private List<WorkspaceUserResponseDTO> members;


    public static WorkspaceResponseDTO fromEntity(Workspace workspace) {
        return WorkspaceResponseDTO.builder()
                .id(workspace.getId())
                .name(workspace.getName())
                .description(workspace.getDescription())
                .members(workspace.getWorkspaceUsers() != null ?
                        workspace.getWorkspaceUsers().stream()
                                .map(WorkspaceUserResponseDTO::fromEntity)
                                .collect(Collectors.toList())
                        : List.of())
                .build();
    }
}
