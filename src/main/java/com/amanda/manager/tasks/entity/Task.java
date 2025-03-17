package com.amanda.manager.tasks.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import com.amanda.manager.tasks.enums.TaskStatus;
import com.amanda.manager.tasks.enums.TaskPriority;
import java.time.LocalDateTime;


@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "username", nullable = false, length = 100)
    private String user;

    @Column(name = "title",nullable = false, length = 200)
    private String title;

    @Column(name = "description",nullable = false, length = 500)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TaskStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority")
    private TaskPriority priority;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "deadline_date", nullable = true)
    private LocalDateTime deadlineDate;

    @Column(name = "done", nullable = false)
    private boolean done = false;


    public Task (){}

    public Task(String user, String title, String description, TaskStatus status,TaskPriority priority ,LocalDateTime deadlineDate)
    {
        this.user = user;
        this.title = title;
        this.description = description;
        this.status = status != null ? status : TaskStatus.PENDING; // Se status não for passado, começa como PENDING
        this.deadlineDate = deadlineDate;
    }

    public Long getId(){return id;}

    public void setId(Long id){this.id =id;}

    public String getUser()
    {
        return  user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        if (status != null){
            this.status = status;
        }
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void SetPriority(TaskPriority priority)
    {
        this.priority = priority;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(LocalDateTime deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public boolean getDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

}
