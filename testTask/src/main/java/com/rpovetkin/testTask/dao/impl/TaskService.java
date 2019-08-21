package com.rpovetkin.testTask.dao.impl;

import com.rpovetkin.testTask.dao.TaskDao;
import com.rpovetkin.testTask.model.Task;
import com.rpovetkin.testTask.spring.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService implements TaskDao {

    private TaskRepository taskRepository;

    private static final int PRIORITY_MAX = 5;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
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

    @Override
    public Task findById(long id) {
        return taskRepository.findByTaskId(id);
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public void save(Task task) {
        taskRepository.save(task);
    }

    @Override
    public void delete(long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public List<Task> findAll(String valueToSort) {
        return null;
    }

    @Override
    public List<Task> findAll(String valueToSort, int pageSize) {
        return null;
    }

    @Override
    public List<Task> findAllTaskInProjectId(Long projectId) {
        return taskRepository.findAllTaskInProject(projectId);
    }
}
