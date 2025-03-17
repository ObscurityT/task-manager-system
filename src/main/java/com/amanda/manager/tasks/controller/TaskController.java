package com.amanda.manager.tasks.controller;

import com.amanda.manager.tasks.dto.TaskDTO;
import com.amanda.manager.tasks.entity.Task;
import com.amanda.manager.tasks.repository.TaskRepository;
import com.amanda.manager.tasks.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
    @RequestMapping("/tasks")
    public class TaskController {


    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskDTO>> getAllTasks()
    {
        List<Task>tasks = taskService.getAllTasks();
        List<TaskDTO> taskDTOS = tasks.stream().map(TaskDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(taskDTOS);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<TaskDTO>getTaskbyId(@PathVariable Long id)
    {
        Task task = taskService.findById(id);

        TaskDTO taskDTO = new TaskDTO(task);
        return ResponseEntity.ok(taskDTO);
    }

    @PostMapping
    public Task addTask(@RequestBody Task task)
    {
       return taskService.addTask(task);
    }

    @PutMapping("/{id}")
    public TaskDTO updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO)
    {
        Task updateTask = taskService.updateTask
                (id,
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
