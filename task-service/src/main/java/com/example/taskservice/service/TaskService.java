package com.example.taskservice.service;

import com.example.taskservice.model.Task;

import java.util.List;

public interface TaskService {

    List<Task> tasks();

    Task createTask(Task task);

    Task getTaskById(Long taskId);
}
