package com.amanda.manager.tasks.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
public class Task {


    @Id
    @GeneratedValue
    private Long id;

    @Column(name= "username", nullable = false, length = 100)
    private String user;

    @Column(name = "title",nullable = false, length = 200)
    private String title;

    @Column(name = "description",nullable = false, length = 500)
    private String description;

    @Column(name = "status",nullable = false, length = 50)
    private String status;

    @Column(name = "priority")
    private String priority;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "deadline_date", nullable = true)
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

    public boolean getDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

}
