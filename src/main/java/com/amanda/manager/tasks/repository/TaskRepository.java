package com.amanda.manager.tasks.repository;
import  com.amanda.manager.tasks.entity.Task;

import com.amanda.manager.tasks.enums.TaskPriority;
import com.amanda.manager.tasks.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository <Task, Long>, JpaSpecificationExecutor<Task> {

    //custom methods
    List<Task> findByDeadlineDateBetween(LocalDateTime start, LocalDateTime end);

    List<Task> findByTitle(String title);

    @Query("SELECT t FROM Task t WHERE "
            + "(:status IS NULL OR t.status = :status) AND "
            + "(:user IS NULL OR t.user = :user) AND "
            + "(:startDate IS NULL OR t.deadlineDate >= :startDate) AND "
            + "(:endDate IS NULL OR t.deadlineDate <= :endDate) AND "
            + "(:priority IS NULL OR t.priority = :priority)")
    List<Task> searchTasks(@Param("status") TaskStatus status,@Param("user") String user,@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate,
    @Param("priority") TaskPriority priority);



}
