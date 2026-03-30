package com.abood.taskmanager.controller;

import com.abood.taskmanager.dto.WorkspaceDTO;
import com.abood.taskmanager.dto.WorkspaceResponseDTO;
import com.abood.taskmanager.entity.Workspace;
import com.abood.taskmanager.service.UserService;
import com.abood.taskmanager.service.WorkspaceService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workspaces")
public class WorkspaceController {
    private final WorkspaceService workspaceService;

    public WorkspaceController(WorkspaceService workspaceService) {
        this.workspaceService = workspaceService;
    }

    @GetMapping
    public List<WorkspaceResponseDTO> getAllWorkspaces() {
        return workspaceService.getAllWorkspaces();
    }

    @PostMapping
    public Workspace createWorkspace(@Valid @RequestBody WorkspaceDTO workspaceDTO) {
        return workspaceService.createWorkSpace(workspaceDTO);
    }

    @PostMapping("/{workspaceId}/users/{userId}")
    public Workspace addUserToWorkspace(@PathVariable Long workspaceId,
                                        @PathVariable Long userId) {
        return workspaceService.addUserToWorkspace(userId, workspaceId);
    }
}


