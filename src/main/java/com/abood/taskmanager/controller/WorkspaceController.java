package com.abood.taskmanager.controller;

import com.abood.taskmanager.dto.WorkspaceDTO;
import com.abood.taskmanager.dto.WorkspaceResponseDTO;
import com.abood.taskmanager.dto.WorkspaceUserDTO;
import com.abood.taskmanager.service.WorkspaceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workspaces")
public class WorkspaceController {

    private final WorkspaceService workspaceService;

    public WorkspaceController(WorkspaceService workspaceService) {
        this.workspaceService = workspaceService;
    }

    @PostMapping
    public ResponseEntity<WorkspaceResponseDTO> createWorkspace(@Valid @RequestBody WorkspaceDTO workspaceDTO) {
        WorkspaceResponseDTO response = workspaceService.createWorkspace(workspaceDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<WorkspaceResponseDTO>> getAllWorkspaces() {
        List<WorkspaceResponseDTO> workspaces = workspaceService.getAllWorkspaces();
        return ResponseEntity.ok(workspaces);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkspaceResponseDTO> getWorkspaceById(@PathVariable Long id) {
        WorkspaceResponseDTO workspace = workspaceService.getWorkspaceById(id);
        return ResponseEntity.ok(workspace);
    }

    @PostMapping("/{workspaceId}/users")
    public ResponseEntity<WorkspaceResponseDTO> addUserToWorkspace(
            @PathVariable Long workspaceId,
            @Valid @RequestBody WorkspaceUserDTO workspaceUserDTO) {
        WorkspaceResponseDTO response = workspaceService.addUserToWorkspace(workspaceId, workspaceUserDTO);
        return ResponseEntity.ok(response);
    }
}


