package com.example.taskservice.service;

import com.example.taskservice.dto.ProjectIdResponse;
import com.example.taskservice.feign.ProjectFeignClient;
import com.example.taskservice.model.Task;
import com.example.taskservice.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;

    private final ProjectFeignClient projectFeignClient;

    public TaskServiceImpl(TaskRepository taskRepository, ProjectFeignClient projectFeignClient) {
        this.taskRepository = taskRepository;
        this.projectFeignClient = projectFeignClient;
    }

    @Override
    public List<Task> tasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task createTask(Task task) {
        ProjectIdResponse projectIdResponse = projectFeignClient.getProjectById(task.getProjectId());

        if (projectIdResponse == null) {
            throw new RuntimeException("Project not found ");
        }

        task.setId(projectIdResponse.getId());
        return taskRepository.save(task);
    }

    @Override
    public Task getTaskById(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found!"));

        ProjectIdResponse response = projectFeignClient.getProjectById(taskId);

        if (response == null) {
            new RuntimeException("Project not found!");
        }

        return task;
    }
}
