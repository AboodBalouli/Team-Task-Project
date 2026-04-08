package com.abood.taskmanager.service;

import com.abood.taskmanager.dto.WorkspaceDTO;
import com.abood.taskmanager.dto.WorkspaceResponseDTO;
import com.abood.taskmanager.dto.WorkspaceUserDTO;
import com.abood.taskmanager.entity.Role;
import com.abood.taskmanager.entity.User;
import com.abood.taskmanager.entity.Workspace;
import com.abood.taskmanager.entity.WorkspaceUser;
import com.abood.taskmanager.repository.UserRepository;
import com.abood.taskmanager.repository.WorkspaceRepository;
import com.abood.taskmanager.repository.WorkspaceUserRepository;
import com.abood.taskmanager.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class WorkspaceService {

    private final WorkspaceRepository workspaceRepository;
    private final UserRepository userRepository;
    private final WorkspaceUserRepository workspaceUserRepository;
    private final CustomUserDetailsService userDetailsService;

    // Constructor injection
    public WorkspaceService(WorkspaceRepository workspaceRepository,
                           UserRepository userRepository,
                           WorkspaceUserRepository workspaceUserRepository,
                           CustomUserDetailsService userDetailsService) {
        this.workspaceRepository = workspaceRepository;
        this.userRepository = userRepository;
        this.workspaceUserRepository = workspaceUserRepository;
        this.userDetailsService = userDetailsService;
    }

    public List<WorkspaceResponseDTO> getAllWorkspaces() {
        return workspaceRepository.findAll()
                .stream()
                .map(WorkspaceResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    /**
     * create a new workspace
     * the creator automatically becomes an ADMIN
     */
    @Transactional
    public WorkspaceResponseDTO createWorkspace(WorkspaceDTO workspaceDTO) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User creator = userDetailsService.getUserByEmail(email);

        Workspace workspace = Workspace.builder()
                .name(workspaceDTO.getName())
                .description(workspaceDTO.getDescription())
                .build();

        workspace = workspaceRepository.save(workspace);

        WorkspaceUser workspaceUser = WorkspaceUser.builder()
                .workspace(workspace)
                .user(creator)
                .role(Role.ADMIN)
                .build();

        workspaceUserRepository.save(workspaceUser);

        return WorkspaceResponseDTO.fromEntity(workspaceRepository.findById(workspace.getId()).get());
    }

    @Transactional
    public WorkspaceResponseDTO addUserToWorkspace(Long workspaceId, WorkspaceUserDTO workspaceUserDTO) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userDetailsService.getUserByEmail(email);

        WorkspaceUser currentUserWorkspace = workspaceUserRepository
                .findByUserIdAndWorkspaceId(currentUser.getId(), workspaceId)
                .orElseThrow(() -> new RuntimeException("You are not a member of this workspace"));

        if (currentUserWorkspace.getRole() != Role.ADMIN) {
            throw new RuntimeException("Only workspace admins can add users");
        }
        Workspace workspace = workspaceRepository.findById(workspaceId)
                .orElseThrow(() -> new RuntimeException("Workspace not found"));

        User userToAdd = userRepository.findById(workspaceUserDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (workspaceUserRepository.existsByUserIdAndWorkspaceId(userToAdd.getId(), workspaceId)) {
            throw new RuntimeException("User is already in this workspace");
        }

        WorkspaceUser workspaceUser = WorkspaceUser.builder()
                .workspace(workspace)
                .user(userToAdd)
                .role(workspaceUserDTO.getRole())
                .build();

        workspaceUserRepository.save(workspaceUser);

        return WorkspaceResponseDTO.fromEntity(workspaceRepository.findById(workspaceId).get());
    }

    public WorkspaceResponseDTO getWorkspaceById(Long id) {
        Workspace workspace = workspaceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Workspace not found"));
        return WorkspaceResponseDTO.fromEntity(workspace);
    }
}
