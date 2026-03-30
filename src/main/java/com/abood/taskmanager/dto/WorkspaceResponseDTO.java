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
    private List<UserResponseDTO> users;

    // Static factory method to convert Workspace entity to WorkspaceResponseDTO
    public static WorkspaceResponseDTO fromEntity(Workspace workspace) {
        return WorkspaceResponseDTO.builder()
                .id(workspace.getId())
                .name(workspace.getName())
                .users(workspace.getUsers().stream()
                        .map(UserResponseDTO::fromEntity)
                        .collect(Collectors.toList()))
                .build();
    }
}
