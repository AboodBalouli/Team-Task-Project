package com.abood.taskmanager.dto;

import com.abood.taskmanager.entity.Task;
import com.abood.taskmanager.entity.TaskStatus;
import com.abood.taskmanager.entity.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskResponseDTO {
    private Long id;
    private String title;
    private String description;
    private TaskStatus taskStatus;
    private LocalDateTime deadline;
    private ProjectResponseDTO project;
    private Set<UserResponseDTO> assignedUsers;

    public static TaskResponseDTO fromEntity(Task task) {
        return TaskResponseDTO.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .taskStatus(task.getTaskStatus())
                .deadline(task.getDeadline())
                .project(ProjectResponseDTO.fromEntity(task.getProject()))
                .assignedUsers(task.getAssignedUsers() != null ?
                        task.getAssignedUsers().stream()
                                .map(UserResponseDTO::fromEntity)
                                .collect(Collectors.toSet())
                        : Set.of())
                .build();
    }
}
