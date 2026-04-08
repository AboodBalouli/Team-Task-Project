package com.abood.taskmanager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Workspace entity represents a team workspace.
 * a workspace contains projects and has users with specific roles.
 */
@Entity
@Table(name = "workspaces")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Workspace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    /**
     * WorkspaceUsers - the users in this workspace along with their roles
     */
    @OneToMany(mappedBy = "workspace", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<WorkspaceUser> workspaceUsers = new HashSet<>();

    /**
     * projects belonging to this workspace
     */
    @OneToMany(mappedBy = "workspace", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Project> projects = new HashSet<>();
}
