package com.abood.taskmanager.dto;

import com.abood.taskmanager.entity.Project;
import com.abood.taskmanager.entity.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDTO {

    @NotBlank(message="task title is required")
    private String title;

    private String description;

    private TaskStatus taskStatus;

    private Long projectId;
}
