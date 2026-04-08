package com.abood.taskmanager.dto;

import com.abood.taskmanager.entity.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDTO {

    @NotBlank(message = "Task title is required")
    private String title;

    private String description;

    @NotNull(message = "Task status is required")
    private TaskStatus taskStatus;

    @NotNull(message = "Project ID is required")
    private Long projectId;

    private LocalDateTime deadline;

    private Set<Long> assignedUserIds;
}
