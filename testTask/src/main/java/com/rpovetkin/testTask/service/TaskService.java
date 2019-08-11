package com.rpovetkin.testTask.service;

import com.rpovetkin.testTask.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    Optional<Task> findTaskById(Long id);
    void saveTask(Task task);
    Boolean updateTask(Task task, String description, String taskName, int priority, String status);
    void deleteTask(Long id);
    Iterable<Task> findAll();
    List<Task> findAllTaskInProjectId(Long projectId);
}
