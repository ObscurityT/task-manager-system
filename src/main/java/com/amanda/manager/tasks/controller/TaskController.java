package com.amanda.manager.tasks.controller;

import com.amanda.manager.tasks.dto.TaskDTO;
import com.amanda.manager.tasks.entity.Task;
import com.amanda.manager.tasks.enums.TaskPriority;
import com.amanda.manager.tasks.enums.TaskStatus;
import com.amanda.manager.tasks.repository.TaskRepository;
import com.amanda.manager.tasks.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
    @RequestMapping("/tasks")
    public class TaskController {


    @Autowired
    private TaskService taskService;


    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks()
    {
        List<Task>tasks = taskService.getAllTasks();
        List<TaskDTO> taskDTOS = tasks.stream().map(TaskDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(taskDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO>getTaskbyId(@PathVariable Long id)
    {
        Task task = taskService.findById(id);

        TaskDTO taskDTO = new TaskDTO(task);
        return ResponseEntity.ok(taskDTO);
    }

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

    @PostMapping
    public Task addTask(@RequestBody Task task)
    {
       return taskService.addTask(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO)
    {
        Task updateTask = taskService.updateTask
                (id,
                        taskDTO.getTitle(),
                        taskDTO.getDeadlineDate(),
                        taskDTO.getStatus());

        TaskDTO responseDTO = new TaskDTO();

        responseDTO.setTitle(updateTask.getTitle());
        responseDTO.setDeadlineDate(updateTask.getDeadlineDate());
        responseDTO.setStatus(updateTask.getStatus());

        return ResponseEntity.ok(responseDTO);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id)
    {
        taskService.removeTask(id);
        return ResponseEntity.noContent().build();
    }


}
