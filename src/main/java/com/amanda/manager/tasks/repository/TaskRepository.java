package com.amanda.manager.tasks.repository;
import  com.amanda.manager.tasks.entity.Task;

import com.sun.source.util.TaskListener;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository <Task, Long>{

    //custom methods
    List<Task> findByDone(boolean done);

    List<Task> findByDeadlineDateBetween(LocalDateTime start, LocalDateTime end);

    List<Task> findByTitle(String title);

    @Query("SELECT t FROM Task t WHERE t.done = :done AND t.deadlineDate BETWEEN :start AND :end")
    List<Task> filterByStatusAndDeadline(@Param("done") boolean done,@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);


}
