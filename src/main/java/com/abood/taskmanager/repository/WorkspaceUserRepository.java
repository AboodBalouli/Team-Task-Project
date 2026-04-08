package com.abood.taskmanager.repository;

import com.abood.taskmanager.entity.WorkspaceUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkspaceUserRepository extends JpaRepository<WorkspaceUser, Long> {


    List<WorkspaceUser> findByUserId(Long userId);


    List<WorkspaceUser> findByWorkspaceId(Long workspaceId);

    Optional<WorkspaceUser> findByUserIdAndWorkspaceId(Long userId, Long workspaceId);

    boolean existsByUserIdAndWorkspaceId(Long userId, Long workspaceId);
}
