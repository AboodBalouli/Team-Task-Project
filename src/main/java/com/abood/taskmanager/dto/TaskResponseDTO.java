package com.abood.taskmanager.dto;

import com.abood.taskmanager.entity.Task;
import com.abood.taskmanager.entity.TaskStatus;
import lombok.*;

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
    private ProjectResponseDTO project;

    public static TaskResponseDTO fromEntity(Task task) {
        return TaskResponseDTO.
                builder().
                id(task.getId()).
                title(task.getTitle()).
                description(task.getDescription()).
                taskStatus(task.getTaskStatus()).
                project(ProjectResponseDTO.fromEntity(task.getProject())).
                build();
    }
}
