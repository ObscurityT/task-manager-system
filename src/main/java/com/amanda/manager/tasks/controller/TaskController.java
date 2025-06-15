package com.amanda.manager.tasks.controller;

import com.amanda.manager.tasks.dto.TaskDTO;
import com.amanda.manager.tasks.entity.Task;
import com.amanda.manager.tasks.enums.TaskPriority;
import com.amanda.manager.tasks.enums.TaskStatus;
import com.amanda.manager.tasks.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Tasks", description = "Endpoints for managing tasks")
@RestController
    @RequestMapping("/tasks")
    public class TaskController {


    @Autowired
    private TaskService taskService;


    @Operation(summary = "get all tasks", description = "Returns all the tasks in the db")
    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks()
    {
        List<Task>tasks = taskService.getAllTasks();
        List<TaskDTO> taskDTOS = tasks.stream().map(TaskDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(taskDTOS);
    }

    @Operation(summary = "get the task by ID", description = "Returns the task that matches the given ID, including all available information.")
    @Parameter(name = "id", description = "Id of the task to retrieve")
    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO>getTaskbyId(@PathVariable Long id) {
        Task task = taskService.findById(id);

        TaskDTO taskDTO = new TaskDTO(task);
        return ResponseEntity.ok(taskDTO);

    }

    @Operation(
            summary = "Search task",
            description = "Returns a list of tasks that match the optional filters: status, user, priority, start and end date.")
    @Parameters ({
        @Parameter(name = "status", description = "Filter by task status"),
        @Parameter(name = "user", description = "Filter by user name"),
        @Parameter(name = "startTime", description = "Filter by start date"),
        @Parameter(name = "endDate", description = "Filter by end date"),
        @Parameter(name = "priority", description = "Filter by priority"),
    })
    @GetMapping("/search")
    public ResponseEntity<List<TaskDTO>> searchTasks(
            @RequestParam(required = false)TaskStatus status,
            @RequestParam(required = false)String user,
            @RequestParam(required = false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime startDate,
            @RequestParam(required = false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime endDate,
            @RequestParam(required = false) TaskPriority priority)
    {
        List<Task> tasks = taskService.searchTasks(status, user, startDate,endDate, priority);
        List<TaskDTO> taskDTOS = tasks.stream().map(TaskDTO::new).toList();
        return ResponseEntity.ok(taskDTOS);
    }


    @Operation(summary = "Add a new Task", description = "Adds a new task on the db")
    @PostMapping
    public ResponseEntity<TaskDTO> addTask(@RequestBody @Valid TaskDTO taskDTO)
    {
        Task task = taskDTO.toEntity();
        Task savedTask = taskService.addTask(task);
       return ResponseEntity.status(201).body(new TaskDTO(savedTask));
    }

    @Operation(
            summary = "Updates an existing task",
            description = "Modifies the fields of a task based on the given ID and the new values provided in the request body")
    @Parameter(name = "id", description = "ID of the task")
    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO)
    {
        Task updateTask = taskService.updateTask
                (id,
                    taskDTO.getUser(),
                    taskDTO.getTitle(),
                    taskDTO.getDeadlineDate(),
                    taskDTO.getStatus(),
                    taskDTO.getDescription());

        TaskDTO responseDTO = new TaskDTO();

        return ResponseEntity.ok(new TaskDTO(updateTask));
    }


    @Operation(summary = "Delete a task", description = "Removes a task from the database based on the provided ID.")
    @Parameter(name = "id", description = "ID of the task to be deleted")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id)
    {
        taskService.removeTask(id);
        return ResponseEntity.noContent().build();
    }


}
