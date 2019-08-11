package com.rpovetkin.testTask.controller;

import com.rpovetkin.testTask.model.Project;
import com.rpovetkin.testTask.model.Task;
import com.rpovetkin.testTask.model.TaskStatus;
import com.rpovetkin.testTask.service.ProjectService;
import com.rpovetkin.testTask.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/project")
@RestController
public class TaskController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private TaskService taskService;

    @GetMapping(value = "/saveTask")
    @ResponseBody
    public ResponseEntity<Task> saveTask(@RequestParam(name = "projectId", required = true) Long projectId,
                                         @RequestParam(name = "taskName", required = true) String taskName,
                                         @RequestParam(name = "taskDescription", required = false) String taskDescription) {

        Project project = projectService.findProjectById(projectId).orElseThrow(() -> new ProjectController.NotFoundException(projectId.toString()));
        Task task = new Task();
        task.setProject(project);
        task.setTaskName(taskName);
        task.setDescription(taskDescription);
        taskService.saveTask(task);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @DeleteMapping("/deleteTask")
    @ResponseBody
    public ResponseEntity deleteTask(@RequestParam(required = true) Long id) {
        taskService.deleteTask(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/getAllTask", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
    @ResponseBody
    //TODO: добавить пагинацию
    public ResponseEntity<Iterable<Task>> getAllTask() {

        try {
            return new ResponseEntity<>(taskService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/getAllTaskInProject", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<List<Task>> getAllTaskInProject(@RequestParam(required = true) Long projectId) {

        try {
            return new ResponseEntity<>(taskService.findAllTaskInProjectId(projectId), HttpStatus.OK);
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

        Task task = taskService.findTaskById(taskId).orElseThrow(() -> new ProjectController.NotFoundException(taskId.toString()));
        if(task.getTaskStatus() == TaskStatus.CLOSED) {
            throw new IllegalArgumentException("taskStatus is closed!");
        }

        Boolean isUpdate = taskService.updateTask(task, taskDescription, taskName, priority, taskStatus);
        if(isUpdate) {
            return new ResponseEntity<>(task, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
