package com.amanda.manager.tasks.controller;

import com.amanda.manager.tasks.dto.TaskDTO;
import com.amanda.manager.tasks.entity.Task;
import com.amanda.manager.tasks.repository.TaskRepository;
import com.amanda.manager.tasks.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/tasks")
    public class TaskController {


    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping
    public List<Task> getAllTasks()
    {
        return taskService.getAllTasks();
    }

    @PostMapping
    public Task addTask(@RequestBody Task task)
    {
       return taskService.addTask(task);
    }

    @PutMapping("/{id}")
    public TaskDTO updateTask(@RequestBody TaskDTO taskDTO)
    {
        Task updateTask = taskService.updateTask
                (taskDTO.getId(),
                        taskDTO.getTitle(),
                        taskDTO.getDeadlineDate(),
                        taskDTO.getDone());

        TaskDTO responseDTO = new TaskDTO();

        responseDTO.setId(updateTask.getId());
        responseDTO.setTitle(updateTask.getTitle());
        responseDTO.setDeadlineDate(updateTask.getDeadlineDate());
        responseDTO.setDone(updateTask.getDone());

        return responseDTO;
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id)
    {
        taskService.removeTask(id);
        return ResponseEntity.noContent().build();
    }


}
