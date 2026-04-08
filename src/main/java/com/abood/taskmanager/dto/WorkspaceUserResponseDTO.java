package com.abood.taskmanager.dto;

import com.abood.taskmanager.entity.Role;
import com.abood.taskmanager.entity.WorkspaceUser;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkspaceUserResponseDTO {
    private Long id;
    private UserResponseDTO user;
    private Role role;

    public static WorkspaceUserResponseDTO fromEntity(WorkspaceUser workspaceUser) {
        return WorkspaceUserResponseDTO.builder()
                .id(workspaceUser.getId())
                .user(UserResponseDTO.fromEntity(workspaceUser.getUser()))
                .role(workspaceUser.getRole())
                .build();
    }
}
