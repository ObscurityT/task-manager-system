package com.amanda.manager.tasks.dto;


import com.amanda.manager.tasks.entity.Task;

import java.time.LocalDateTime;

public class TaskDTO {

    private Long id;
    private String title;
    private LocalDateTime deadlineDate;
    private boolean done;

    public TaskDTO(){}

    public TaskDTO(Task task){

        this.id = task.getId();
        this.title = task.getTitle();
        this.deadlineDate = task.getDeadlineDate();
        this.done = task.getDone();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public boolean getDone()
    {return  done;}

    public void setDone(boolean done) {
        this.done = done;
    }
}
