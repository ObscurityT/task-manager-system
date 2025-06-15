package com.amanda.manager.tasks.service;

import com.amanda.manager.tasks.dto.TaskDTO;
import com.amanda.manager.tasks.entity.Task;
import com.amanda.manager.tasks.enums.TaskPriority;
import com.amanda.manager.tasks.enums.TaskStatus;
import com.amanda.manager.tasks.exception.InvalidTaskException;
import com.amanda.manager.tasks.exception.TaskNotFoundException;
import com.amanda.manager.tasks.repository.TaskRepository;
import com.amanda.manager.tasks.specifications.TaskSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class TaskService {

    private final TaskRepository taskRepository;

    //constructor injector
    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }


    public Task findById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));
    }


    public List<Task> filterByDeadlineDateBetween(LocalDateTime start, LocalDateTime end) {
        return taskRepository.findByDeadlineDateBetween(start, end);
    }

    public Task addTask(Task task) {
        if (task.getTitle() == null || task.getTitle().isEmpty()) {
            throw new InvalidTaskException("Task must have a title");
        }

        if (task.getDeadlineDate() != null && task.getDeadlineDate().isBefore(LocalDateTime.now())) {
            throw new InvalidTaskException("Deadline cannot be in the past");
        }

        if (task.getUser() == null)
        {
            throw new InvalidTaskException("Task must have a user");
        }

        return taskRepository.save(task);
    }


    public void removeTask(Long taskId) {
        if (!taskRepository.existsById(taskId)) {
            throw new TaskNotFoundException(("Task with ID") + taskId + "does not exist");
        }

        taskRepository.deleteById(taskId);
    }

    public Task updateTask(Long taskId, String user, String title, LocalDateTime deadlineDate, TaskStatus status, String description) {

        Task existingTask = findById(taskId);

        if (title != null && !title.isEmpty()) {
            existingTask.setTitle(title);
        }

        if (user != null && !user.isEmpty()) {
            existingTask.setUser(user);
        }

        if (deadlineDate != null) {
            existingTask.setDeadlineDate(deadlineDate);
        }

        if (status != null) {
            existingTask.setStatus(status);
        }
        if (description != null)
        {
            existingTask.setDescription(description);
        }

     return taskRepository.save(existingTask);

    }

    public List<Task> searchTasks(TaskStatus status, String user, LocalDateTime start, LocalDateTime end, TaskPriority priority)
    {
        Specification<Task> spec = Specification
                .where(TaskSpecification.hasStatus(status))
                .and(TaskSpecification.hasUser(user))
                .and(TaskSpecification.deadlineAfter(start))
                .and(TaskSpecification.deadlineAfter(end))
                .and(TaskSpecification.hasPriority(priority));

        return  taskRepository.findAll(spec);
    }

}