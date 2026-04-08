package com.abood.taskmanager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * users can belong to multiple workspaces with different roles.
 * users can be assigned to multiple tasks.
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @JsonIgnore
    private String password;

    /**
     * WorkspaceUsers represents the user's membership in workspaces
     * along with their role in each workspace (ADMIN or MEMBER)
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<WorkspaceUser> workspaceUsers = new HashSet<>();

    /**
     * Tasks that are assigned to this user
     */
    @ManyToMany(mappedBy = "assignedUsers")
    @JsonIgnore
    private Set<Task> assignedTasks = new HashSet<>();
}
