package com.rpovetkin.testTask.dao;

import com.rpovetkin.testTask.model.Task;

import java.util.List;

public interface TaskDao extends GeneralDao<Task> {
    List<Task> findAllTaskInProjectId(Long projectId);
}
