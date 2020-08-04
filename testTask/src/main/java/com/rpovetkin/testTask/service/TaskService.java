package com.rpovetkin.testTask.service;

import com.rpovetkin.testTask.model.Task;

import java.util.List;

public interface TaskService {
    void saveTask(Task task);
    void deleteTask(long id);
    List<Task> findAllTaskInProjectId(Long projectId);
}
