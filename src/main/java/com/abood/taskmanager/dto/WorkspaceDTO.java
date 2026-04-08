package com.abood.taskmanager.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkspaceDTO {

    @NotBlank(message = "Workspace name is required")
    private String name;

    private String description;
}
