package com.abood.taskmanager.service;

import com.abood.taskmanager.dto.WorkspaceDTO;
import com.abood.taskmanager.dto.WorkspaceResponseDTO;
import com.abood.taskmanager.entity.User;
import com.abood.taskmanager.entity.Workspace;
import com.abood.taskmanager.repository.UserRepository;
import com.abood.taskmanager.repository.WorkspaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkspaceService {

    private final WorkspaceRepository workspaceRepository;
    private final UserRepository userRepository;

    public WorkspaceService(WorkspaceRepository workspaceRepository, UserRepository userRepository) {
        this.workspaceRepository = workspaceRepository;
        this.userRepository = userRepository;
    }

    public List<WorkspaceResponseDTO> getAllWorkspaces() {
        return workspaceRepository.findAll().
                stream().
                map(workspace -> WorkspaceResponseDTO.builder()
                        .id(workspace.getId()).
                        name(workspace.getName()).
                        build()).
                toList();
    }

    public Workspace createWorkSpace(WorkspaceDTO workspaceDTO) {
        Workspace workspace = Workspace.
                builder().
                name(workspaceDTO.getName()).
                build();

        return workspaceRepository.save(workspace);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Workspace addUserToWorkspace(Long user_id, Long workspace_id) {
        Workspace workspace = workspaceRepository.findById(workspace_id).orElseThrow(() -> new RuntimeException("workspace not found"));

        User user = userRepository.findById(user_id).orElseThrow(() -> new RuntimeException("user not found"));

        workspace.getUsers().add(user);
        return workspaceRepository.save(workspace);
    }
}
