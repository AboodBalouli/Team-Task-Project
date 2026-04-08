package com.abood.taskmanager.controller;

import com.abood.taskmanager.dto.TaskDTO;
import com.abood.taskmanager.dto.TaskResponseDTO;
import com.abood.taskmanager.entity.TaskStatus;
import com.abood.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public TaskResponseDTO createTask(@Valid @RequestBody TaskDTO taskDTO) {
        return taskService.createTask(taskDTO);
    }

    @GetMapping
    public List<TaskResponseDTO> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PutMapping("/{taskId}/status")
    public TaskResponseDTO updateTaskStatus(@PathVariable Long taskId,
                                            @RequestParam("status") TaskStatus taskStatus) {
        return taskService.updateTaskStatus(taskId, taskStatus);
    }

    @DeleteMapping("/{taskId}")
    public String deleteTask(@PathVariable Long taskId) {

        taskService.deleteTask(taskId);

        return "Task deleted successfully";
    }
    @GetMapping("/project/{projectId}")
    public List<TaskResponseDTO> getTasksByProject(@PathVariable Long projectId) {
        return taskService.getTasksByProject(projectId);
    }
}
