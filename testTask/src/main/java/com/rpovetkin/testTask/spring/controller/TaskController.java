package com.rpovetkin.testTask.spring.controller;

import com.rpovetkin.testTask.dao.ProjectDao;
import com.rpovetkin.testTask.dao.TaskDao;
import com.rpovetkin.testTask.model.Project;
import com.rpovetkin.testTask.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/project")
@RestController
public class TaskController {

    private final ProjectDao projectDao;
    private final TaskDao taskDao;

    @Autowired
    public TaskController(ProjectDao projectDao, TaskDao taskDao) {
        this.projectDao = projectDao;
        this.taskDao = taskDao;
    }

    @GetMapping(value = "/saveTask")
    @ResponseBody
    public ResponseEntity<Task> saveTask(@RequestParam(name = "projectId", required = true) Long projectId,
                                         @RequestParam(name = "taskName", required = true) String taskName,
                                         @RequestParam(name = "taskDescription", required = false) String taskDescription) {

        Project project = projectDao.findById(projectId);
        Task task = new Task();
        task.setProject(project);
        task.setTaskName(taskName);
        task.setDescription(taskDescription);
        taskDao.save(task);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @DeleteMapping("/deleteTask")
    @ResponseBody
    public ResponseEntity deleteTask(@RequestParam(required = true) Long id) {
        taskDao.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/getAllTask", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
    @ResponseBody
    //TODO: добавить пагинацию
    public ResponseEntity<Iterable<Task>> getAllTask() {

        try {
            return new ResponseEntity<>(taskDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/getAllTaskInProject", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<List<Task>> getAllTaskInProject(@RequestParam(required = true) Long projectId) {

        try {
            return new ResponseEntity<>(taskDao.findAllTaskInProjectId(projectId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/updateTask")
    @ResponseBody
    public ResponseEntity<Task> updateTask(@RequestParam(name = "taskId", required = true) Long taskId,
                                           @RequestParam(name = "taskDescription", required = false) String taskDescription,
                                           @RequestParam(name = "taskName", required = false) String taskName,
                                           @RequestParam(name = "priority", required = false) int priority,
                                           @RequestParam(name = "taskStatus", required = false) String taskStatus) {

//        Task task = taskDao.findById(taskId);
//        if(task.getTaskStatus() == TaskStatus.CLOSED) {
//            throw new IllegalArgumentException("taskStatus is closed!");
//        }
//
//        Boolean isUpdate = taskDao.updateTask(task, taskDescription, taskName, priority, taskStatus);
//        if(isUpdate) {
//            return new ResponseEntity<>(task, HttpStatus.OK);
//        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
