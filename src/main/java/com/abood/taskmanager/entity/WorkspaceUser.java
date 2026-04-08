package com.abood.taskmanager.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * WorkspaceUser is a join entity that represents the Many-to-Many relationship
 * between Users and Workspaces, with an additional 'role' field.
 *
 * this allows users to have different roles in different workspaces:
 * - User A can be ADMIN in Workspace 1
 * - User A can be MEMBER in Workspace 2
 */
@Entity
@Table(name = "workspace_users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkspaceUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "workspace_id", nullable = false)
    private Workspace workspace;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
}
