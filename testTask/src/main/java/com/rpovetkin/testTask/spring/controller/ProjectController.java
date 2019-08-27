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

@RestController
public class ProjectController {

    private final ProjectDao projectDao;
    private final TaskDao taskDao;

    @Autowired
    public ProjectController(ProjectDao projectDao, TaskDao taskDao) {
        this.projectDao = projectDao;
        this.taskDao = taskDao;
    }

    //TODO: добавить проверки на исключения

    @GetMapping(value = "/project")
    @ResponseBody
    public ResponseEntity<?> getAllProject() {
        try {
            return new ResponseEntity<>(projectDao.findAll(), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/project/{projectId}")
    @ResponseBody
    public ResponseEntity<?> getProjectById(@PathVariable(name = "projectId") long id) {

        try{
            Project project = projectDao.findById(id);
            return new ResponseEntity<>(project, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("badRequest, id is not exist\n" + e, HttpStatus.NOT_FOUND);
        }
    }

//    @PostMapping(value = "/project", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
    @PostMapping(value = "/project")
    @ResponseBody
    public ResponseEntity<?> saveProject(@RequestBody ProjectApi projectApi) {
        try{
            Project resultProject = projectDao.saveProject(projectApi);
            return new ResponseEntity<>("Project is created, projectId = " + resultProject.getProjectId(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("I can't create a project with such data\n" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping(value = "/project/{projectId}")
    @ResponseBody
    public ResponseEntity<?> updateProject(@PathVariable(name = "projectId") long projectId,
                                                 @RequestParam(name = "projectName") String projectName,
                                                 @RequestParam(name = "description", required = false) String description) {
        try{
            //TODO: почитать как работают аннотации nonnull или nullable
            Project resultProject = projectDao.updateProject(projectId, projectName, description);
            return new ResponseEntity<>("Project is updated, projectId = " + resultProject.getProjectId(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("I can't update a project with such data\n" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/project/{projectId}")
    @ResponseBody
    public ResponseEntity<?> deleteProject(@PathVariable(name = "projectId") Long projectId) {
        try{
            projectDao.delete(projectId);
            return new ResponseEntity<>("Project deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("I can't delete a project with such data\n" + e, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/project/{sort}")
    @ResponseBody
    public ResponseEntity<?> getAllProjectWithSorted(@PathVariable(name = "sort") String valueToSorted) {
        try{
            List<Project> projectListWithSorted = projectDao.findAll(valueToSorted);
            return new ResponseEntity<>(projectListWithSorted, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>("I can't search a data, valueToSorted = " + valueToSorted + "\n" + e, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/getPageProjects", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<Iterable<Project>> getPageProjects(@RequestParam(name = "valueToSorted") String valueToSorted,
                                                             @RequestParam(name = "pageSize") int pageSize) {
        return new ResponseEntity<>(projectDao.findAll(valueToSorted, pageSize), HttpStatus.OK);
    }

//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    protected static class NotFoundException extends RuntimeException {
//        protected NotFoundException(String Id) {
//            super("could not find id" + Id + "'.");
//        }
//    }
}
