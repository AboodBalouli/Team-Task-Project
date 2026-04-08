package com.abood.taskmanager.service;

import com.abood.taskmanager.dto.TaskDTO;
import com.abood.taskmanager.dto.TaskResponseDTO;
import com.abood.taskmanager.entity.Project;
import com.abood.taskmanager.entity.Task;
import com.abood.taskmanager.entity.TaskStatus;
import com.abood.taskmanager.repository.ProjectRepository;
import com.abood.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    TaskService(TaskRepository taskRepository, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }

    public TaskResponseDTO createTask(TaskDTO taskDTO) {
        Project project = projectRepository.findById(taskDTO.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found"));

        Task task = Task.builder().
                title(taskDTO.getTitle())
                .description(taskDTO.getDescription())
                .taskStatus(taskDTO.getTaskStatus() != null ? taskDTO.getTaskStatus() : TaskStatus.TODO)
                .project(project)
                .build();

        Task savedTask = taskRepository.save(task);

        return TaskResponseDTO.fromEntity(savedTask);
    }

    public List<TaskResponseDTO> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(TaskResponseDTO::fromEntity)
                .toList();
    }

    public Task getTaskEntityById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public TaskResponseDTO updateTaskStatus(Long taskId, TaskStatus taskStatus) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setTaskStatus(taskStatus);
        return TaskResponseDTO.fromEntity(taskRepository.save(task));
    }

    public void deleteTask(Long taskId) {

        Task task = getTaskEntityById(taskId);

        taskRepository.delete(task);
    }

    public List<TaskResponseDTO> getTasksByProject(Long projectId) {

        projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Project not found"));


        return taskRepository.findByProjectId(projectId).stream()
                .map(TaskResponseDTO::fromEntity)
                .toList();
    }
}
