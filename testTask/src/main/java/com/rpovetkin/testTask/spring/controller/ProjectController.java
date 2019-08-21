package com.rpovetkin.testTask.spring.controller;

import com.rpovetkin.testTask.api.ProjectApi;
import com.rpovetkin.testTask.dao.ProjectDao;
import com.rpovetkin.testTask.dao.TaskDao;
import com.rpovetkin.testTask.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/project")
@RestController
public class ProjectController {

    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private TaskDao taskDao;


    @GetMapping("/getProjectById")
    @ResponseBody
    public ResponseEntity<Project> getProjectById(@RequestParam(required = true) long id) {

        try{
            Project project = projectDao.findById(id);
            return new ResponseEntity<>(project, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value = "/getAllProject", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<List<Project>> getAllProject() {
        //TODO: добавить проверку на исключения
        return new ResponseEntity<>(projectDao.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/saveProject")
    @ResponseBody
    public ResponseEntity<Project> saveProject(@RequestParam(required = true) ProjectApi projectApi) {
        try{
            Project resultProject = projectDao.saveProject(projectApi);
            return new ResponseEntity<>(resultProject, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value = "/updateProject")
    @ResponseBody
    public ResponseEntity<Project> updateProject(@RequestParam(name = "projectId", required = true) long projectId,
                                                 @RequestParam(name = "projectName") String projectName,
                                                 @RequestParam(name = "description", required = false) String description) {

        try{
            projectDao.updateProject(projectId, projectName, description);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/deleteProject")
    @ResponseBody
    public ResponseEntity deleteProject(@RequestParam(name = "projectId", required = true) Long id) {
        //TODO: добавить проверку на исключения
        projectDao.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/getAllProjectWithSorted", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<Iterable<Project>> getAllProjectWithSorted(@RequestParam(name = "valueToSorted", required = true) String valueToSorted) {
        //TODO: добавить проверку на исключения
        return new ResponseEntity<>(projectDao.findAll(valueToSorted), HttpStatus.OK);
    }

    @GetMapping(value = "/getPageProjects", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<Iterable<Project>> getPageProjects(@RequestParam(name = "valueToSorted", required = true) String valueToSorted,
                                                             @RequestParam(name = "pageSize", required = true) int pageSize) {
        //TODO: добавить проверку на исключения
        return new ResponseEntity<>(projectDao.findAll(valueToSorted, pageSize), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected static class NotFoundException extends RuntimeException {
        protected NotFoundException(String Id) {
            super("could not find id" + Id + "'.");
        }
    }
}
