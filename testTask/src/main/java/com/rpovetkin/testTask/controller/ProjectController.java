package com.rpovetkin.testTask.controller;

import com.rpovetkin.testTask.model.Project;
import com.rpovetkin.testTask.service.ProjectService;
import com.rpovetkin.testTask.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/project")
@RestController
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private TaskService taskService;


    @GetMapping("/getProjectById")
    @ResponseBody
    public ResponseEntity<Project> getProjectById(@RequestParam(required = true) Long id) {
        return new ResponseEntity<>(projectService.findProjectById(id)
                .orElseThrow(() -> new NotFoundException(id.toString())), HttpStatus.OK);
    }

    @GetMapping(value = "/getAllProject", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<Iterable<Project>> getAllProject() {
        return new ResponseEntity<>(projectService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/saveProject")
    @ResponseBody
    public ResponseEntity<Project> saveProject(@RequestParam(required = true) String projectName,
                                               @RequestParam(required = false) String descriptionText) {
        Project project = new Project();
        if (!projectName.isEmpty()) {
            project.setProjectName(projectName);
        }
        if (!descriptionText.isEmpty()) {
            project.setDescriptionText(descriptionText);
        }
        projectService.saveProject(project);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @GetMapping(value = "/updateProject")
    @ResponseBody
    public ResponseEntity<Project> updateProject(@RequestParam(name = "projectId", required = true) Long projectId,
                                                 @RequestParam(name = "projectName", required = true) String projectName,
                                                 @RequestParam(name = "description", required = false) String description) {
        Project project = projectService.findProjectById(projectId)
                .orElseThrow(() -> new NotFoundException(projectId.toString()));

        Boolean isUpdate = projectService.updateProject(project, projectName, description);
        if (isUpdate) {
            return new ResponseEntity<>(project, HttpStatus.OK);
        } else {
            throw new IllegalArgumentException("updateFailed");
        }
    }

    @DeleteMapping("/deleteProject")
    @ResponseBody
    public ResponseEntity deleteProject(@RequestParam(name = "projectId", required = true) Long id) {
        projectService.deleteProject(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/getAllProjectWithSorted", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<Iterable<Project>> getAllProjectWithSorted(@RequestParam(name = "valueToSorted", required = true) String valueToSorted) {
        return new ResponseEntity<>(projectService.findAll(valueToSorted), HttpStatus.OK);
    }

    @GetMapping(value = "/getPageProjects", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<Iterable<Project>> getPageProjects(@RequestParam(name = "valueToSorted", required = true) String valueToSorted,
                                                             @RequestParam(name = "pageSize", required = true) int pageSize) {
        return new ResponseEntity<>(projectService.findAll(valueToSorted, pageSize), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected static class NotFoundException extends RuntimeException {

        protected NotFoundException(String Id) {
            super("could not find id" + Id + "'.");
        }
    }
}
