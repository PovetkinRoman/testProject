package com.rpovetkin.testTask.service;

import com.rpovetkin.testTask.model.Task;
import com.rpovetkin.testTask.model.TaskStatus;
import com.rpovetkin.testTask.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;

    private static final int PRIORITY_MAX = 5;

    @Autowired
    public void setTaskRepository(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Optional<Task> findTaskById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public void saveTask(Task task) {
        taskRepository.save(task);
    }

    //TODO: Проставить дату модификаци
    //TODO: Отрефакторить if/else
    @Override
    public Boolean updateTask(Task task, String description, String taskName, int priority, String status) {
        if (description != null && !description.isEmpty()
                && taskName != null && !taskName.isEmpty()
                && priority <= PRIORITY_MAX && priority > 0
                && !status.isEmpty()) {
            task.setDescription(description);
            task.setTaskName(taskName);
            task.setPriority(priority);
        } else {return Boolean.FALSE;}

        if (TaskStatus.CLOSED.name().equals(status)) {
            task.setTaskStatus(TaskStatus.CLOSED);
            task.setDateOfLastModification(LocalDateTime.now());
            taskRepository.save(task);
            return Boolean.TRUE;
        } else if(TaskStatus.NEW.name().equals(status)) {
            task.setTaskStatus(TaskStatus.NEW);
            task.setDateOfLastModification(LocalDateTime.now());
            taskRepository.save(task);
            return Boolean.TRUE;
        } else if(TaskStatus.IN_PROGRESS.name().equals(status)) {
            task.setTaskStatus(TaskStatus.IN_PROGRESS);
            task.setDateOfLastModification(LocalDateTime.now());
            taskRepository.save(task);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public Iterable<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> findAllTaskInProjectId(Long projectId) {
        return taskRepository.findAllTaskInProject(projectId);
    }
}
