package com.amanda.manager.tasks.service;

import com.amanda.manager.tasks.entity.Task;
import com.amanda.manager.tasks.exception.InvalidTaskException;
import com.amanda.manager.tasks.exception.TaskNotFoundException;
import com.amanda.manager.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
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


    public List<Task> filterByDone(boolean done) {
        return taskRepository.findByDone(done);
    }

    public List<Task> filterByDeadlineDateBetween(LocalDateTime start, LocalDateTime end) {
        return taskRepository.findByDeadlineDateBetween(start, end);
    }

    public List<Task> filterByStatusAndDeadline(boolean done, LocalDateTime start, LocalDateTime end) {
        return taskRepository.filterByStatusAndDeadline(done, start, end);
    }

    public Task addTask(Task task) {
        if (task.getTitle() == null || task.getTitle().isEmpty()) {
            throw new InvalidTaskException("Task must have a title");
        }

        if (task.getDeadlineDate() != null && task.getDeadlineDate().isBefore(LocalDateTime.now())) {
                throw new InvalidTaskException("Deadline cannot be in the past");
        }

        return taskRepository.save(task);
    }


    public void removeTask(Long taskId) {
        if (!taskRepository.existsById(taskId)) {
            throw new TaskNotFoundException(("Task with ID") + taskId + "does not exist");
        }

        taskRepository.deleteById(taskId);
    }

    public Task updateTask(Long taskId, String title, LocalDateTime deadlineDate, Boolean done) {

      Task existingTask = findById(taskId);

      if (title!= null && !title.isEmpty())
      {
        existingTask.setTitle(title);
      }

      if (deadlineDate != null)
      {
          existingTask.setDeadlineDate(deadlineDate);
      }

      if (done != null)
      {
          existingTask.setDone(done);
      }

     return taskRepository.save(existingTask);

    }

}