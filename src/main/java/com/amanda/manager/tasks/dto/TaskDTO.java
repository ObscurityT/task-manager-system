package com.amanda.manager.tasks.dto;


import com.amanda.manager.tasks.entity.Task;
import com.amanda.manager.tasks.enums.TaskPriority;
import com.amanda.manager.tasks.enums.TaskStatus;

import java.time.LocalDateTime;

public class TaskDTO {

    private Long id;
    private String user;
    private String title;
    private LocalDateTime deadlineDate;
    private TaskStatus status;
    private TaskPriority priority;


    public TaskDTO(){}

    public TaskDTO(Task task){

        this.id = task.getId();
        this.user = task.getUser();
        this.title = task.getTitle();
        this.deadlineDate = task.getDeadlineDate();
        this.status = task.getStatus();
        this.priority = task.getPriority();

    }

    public String getUser() {
        return user;
    }

    public void setUser(String user){this.user = user;}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(LocalDateTime deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public TaskStatus getStatus(){return status;}

    public void setStatus(TaskStatus status)
    {
        this.status = status;
    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public TaskPriority getPriority() {return priority;}

    public void setPriority(TaskPriority priority) {this.priority = priority;}
}
