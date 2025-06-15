package com.amanda.manager.tasks.dto;

import com.amanda.manager.tasks.entity.Task;
import com.amanda.manager.tasks.enums.TaskPriority;
import com.amanda.manager.tasks.enums.TaskStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import javax.swing.text.DateFormatter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TaskDTO {

    @Schema(description = " ID of the task(read-only)")
    private Long id;

    @Schema(description = "Username responsible for the task")
    @NotBlank(message = "User can't be blank")
    private String user;

    @Schema(description = "Title of the task")
    @NotBlank(message = "Title can't be blank")
    private String title;

    @Schema(description = "Creation timestamp(read-only)")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;

    @Schema(description = "Deadline of the task(yyyy-MM-ddTHH:mm)")
    @NotNull(message = "Please set a valid deadline")
    private LocalDateTime deadlineDate;

    @Schema(description = "Status of the task")
    @NotNull(message = "Status cannot be null")
    private TaskStatus status;

    @Schema(description = "Priority level of the task")
    @NotNull(message = "Priority cannot be null")
    private TaskPriority priority;

    @Schema(description = "Additional details or notes")
    private String description;


    public TaskDTO() {
    }

    public TaskDTO(Task task) {

        this.id = task.getId();
        this.user = task.getUser();
        this.title = task.getTitle();
        this.createdAt = task.getCreatedAt();
        this.deadlineDate = task.getDeadlineDate();
        this.status = task.getStatus();
        this.priority = task.getPriority();
        this.description = task.getDescription();

    }

    public Task toEntity() {
        Task task = new Task();
        task.setUser(this.user);
        task.setTitle(this.title);
        task.setDeadlineDate(this.deadlineDate);
        task.setStatus(this.status);
        task.setPriority(this.priority);
        task.setDescription(this.description);
        return task;
    }

    public String getUser() {
        return user;
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

    public LocalDateTime getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(LocalDateTime deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("createdHour")
    public String getCreatedHour()
    {
        if(createdAt == null) return null;

        String timePattern = "dd-MM-yyyy HH:mm";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(timePattern);
        return createdAt.format(formatter);
    }
}
