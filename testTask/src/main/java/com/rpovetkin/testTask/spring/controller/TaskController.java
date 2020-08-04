package com.rpovetkin.testTask.spring.controller;

import com.rpovetkin.testTask.model.Project;
import com.rpovetkin.testTask.model.Task;
import com.rpovetkin.testTask.service.ProjectService;
import com.rpovetkin.testTask.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

//TODO: переписать согласно REST спецификации(по аналогии с entity project)
@RequestMapping("/project")
@RestController
public class TaskController {

    private final ProjectService projectService;
    private final TaskService taskService;

    @Autowired
    public TaskController(ProjectService projectService, TaskService taskService) {
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @GetMapping(value = "/saveTask")
    @ResponseBody
    public ResponseEntity<Task> saveTask(@RequestParam(name = "projectId") Long projectId,
                                         @RequestParam(name = "taskName") String taskName,
                                         @RequestParam(name = "taskDescription", required = false) String taskDescription) {
        //TODO:должно быть написано в service уровне
        Project project = projectService.findById(projectId);
        Task task = new Task();
//        task.setProject(project);
        task.setTaskName(taskName);
        task.setDescription(taskDescription);
        taskService.saveTask(task);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @DeleteMapping("/deleteTask")
    @ResponseBody
    public ResponseEntity deleteTask(@RequestParam Long id) {
        taskService.deleteTask(id);
        return new ResponseEntity(HttpStatus.OK);
    }

//    @GetMapping(value = "/getAllTask", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
//    @ResponseBody
//    //TODO: добавить паджинацию
//    public ResponseEntity<Iterable<Task>> getAllTask() {
//
//        try {
//            return new ResponseEntity<>(taskService.findAll(), HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }

    @GetMapping(value = "/getAllTaskInProject", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> getAllTaskInProject(@RequestParam Long projectId) {

        try {
            return new ResponseEntity<>(taskService.findAllTaskInProjectId(projectId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/updateTask")
    @ResponseBody
    public ResponseEntity<Task> updateTask(@RequestParam(name = "taskId") Long taskId,
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
