package com.amanda.manager.tasks.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
public class Task {

    private String user;

    @Column(nullable = false, length = 100)
    private String title;
    private String description;
    private String status;

    @CreationTimestamp @Column
    private LocalDateTime createdAt;
    private LocalDateTime deadlineDate;
    private boolean done;


    public Task(String user, String title, String description, String status, LocalDateTime deadlineDate)
    {
        this.user = user;
        this.title = title;
        this.description = description;
        this.status = status;
        this.deadlineDate = deadlineDate;
        this.done = false;
    }


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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

}
