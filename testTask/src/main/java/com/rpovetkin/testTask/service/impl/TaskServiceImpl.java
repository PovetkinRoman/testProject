package com.rpovetkin.testTask.service.impl;

import com.rpovetkin.testTask.model.Task;
import com.rpovetkin.testTask.service.TaskService;
import com.rpovetkin.testTask.spring.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;

    private static final int PRIORITY_MAX = 5;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    //    //TODO: Проставить дату модификаци
//    //TODO: Отрефакторить if/else
//    @Override
//    public Boolean updateTask(Task task, String description, String taskName, int priority, String status) {
//        if (description != null && !description.isEmpty()
//                && taskName != null && !taskName.isEmpty()
//                && priority <= PRIORITY_MAX && priority > 0
//                && !status.isEmpty()) {
//            task.setDescription(description);
//            task.setTaskName(taskName);
//            task.setPriority(priority);
//        } else {
//            return Boolean.FALSE;
//        }
//
//        if (TaskStatus.CLOSED.name().equals(status)) {
//            task.setTaskStatus(TaskStatus.CLOSED);
//            task.setDateOfLastModification(LocalDateTime.now());
//            taskRepository.save(task);
//            return Boolean.TRUE;
//        } else if (TaskStatus.NEW.name().equals(status)) {
//            task.setTaskStatus(TaskStatus.NEW);
//            task.setDateOfLastModification(LocalDateTime.now());
//            taskRepository.save(task);
//            return Boolean.TRUE;
//        } else if (TaskStatus.IN_PROGRESS.name().equals(status)) {
//            task.setTaskStatus(TaskStatus.IN_PROGRESS);
//            task.setDateOfLastModification(LocalDateTime.now());
//            taskRepository.save(task);
//            return Boolean.TRUE;
//        }
//        return Boolean.FALSE;
//    }
    public Task findById(long id) {
        return taskRepository.findById(id);
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public void saveTask(Task task) {
        taskRepository.save(task);
    }

    public void deleteTask(long id) {
        taskRepository.deleteById(id);
    }

    public List<Task> findAll(String valueToSort) {
        return null;
    }

    public List<Task> findAll(String valueToSort, int pageSize) {
        return null;
    }

    public List<Task> findAllTaskInProjectId(Long projectId) {
        return taskRepository.findAllTaskInProject(projectId);
    }
}
