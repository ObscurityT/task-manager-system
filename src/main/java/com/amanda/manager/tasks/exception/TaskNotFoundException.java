package com.amanda.manager.tasks.exception;

public class TaskNotFoundException extends RuntimeException{
    public TaskNotFoundException(String message)
    {
        super(message);
    }
}
