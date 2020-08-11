package com.rpovetkin.testTask.spring.controller;

import com.rpovetkin.testTask.api.ProjectApi;
import com.rpovetkin.testTask.model.CsvSimpleBean;
import com.rpovetkin.testTask.model.Project;
import com.rpovetkin.testTask.model.Task;
import com.rpovetkin.testTask.service.ProjectService;
import com.rpovetkin.testTask.service.TaskService;
import com.rpovetkin.testTask.service.impl.BeanCsvServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@Slf4j
public class ProjectController {

    private final ProjectService projectService;
    private final TaskService taskService;
    private final BeanCsvServiceImpl beanCsvService;


    @Autowired
    public ProjectController(ProjectService projectService, TaskService taskService, BeanCsvServiceImpl beanCsvService) {
        this.projectService = projectService;
        this.taskService = taskService;
        this.beanCsvService = beanCsvService;
    }


    //TODO: добавить проверки на исключения, сделать один try/catch
    @GetMapping(value = "/project")
    @ResponseBody
    public ResponseEntity<?> getAllProject(@RequestParam(name = "sort", required = false) String valueToSort) {
        if (valueToSort != null && !valueToSort.isEmpty()) {
            try {
                return new ResponseEntity<>(projectService.findAll(valueToSort), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            try {
                return new ResponseEntity<>(projectService.findAll(), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @GetMapping(value = "/project/{projectId}")
    @ResponseBody
    public ResponseEntity<?> getProjectById(@PathVariable(name = "projectId") long id) {
        try {
            Project project = projectService.findById(id);
            return new ResponseEntity<>(project, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("badRequest, id is not exist\n" + e, HttpStatus.NOT_FOUND);
        }
    }
//    @PostMapping(value = "/project", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, headers = "Accept=application/json")

    @PostMapping(value = "/project")
    @ResponseBody
    public ResponseEntity<?> saveProject(@RequestBody ProjectApi projectApi) {
        try {
//            ProjectApi projectObject = new ProjectApi();
//            projectObject.setProjectName(projectApi.getProjectName());
//            projectObject.setProjectDescription(projectApi.getProjectDescription());
//            projectObject.setTaskSet(projectApi.getTaskSet());
            Project resultProject = projectService.saveProject(projectApi);
            return new ResponseEntity<>("Project is created, project = " + resultProject, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("I can't create a project with such data\n" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping(value = "/project/{projectId}")
    @ResponseBody
    public ResponseEntity<?> updateProject(@PathVariable(name = "projectId") long projectId,
                                           @RequestParam(name = "projectName") String projectName,
                                           @RequestParam(name = "description", required = false) String description) {
        try {
            Project resultProject = projectService.updateProject(projectId, projectName, description);
            return new ResponseEntity<>("Project is updated, projectId = " + resultProject.getId(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("I can't update a project with such data\n" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/project/{projectId}")
    @ResponseBody
    public ResponseEntity<?> deleteProject(@PathVariable(name = "projectId") Long projectId) {
        try {
            projectService.delete(projectId);
            return new ResponseEntity<>("Project deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("I can't delete a project with such data\n" + e, HttpStatus.NO_CONTENT);
        }
    }

    //    @GetMapping(value = "/project", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
    @GetMapping(value = "/project", params = {"page", "size"})
    @ResponseBody
    public ResponseEntity<Iterable<Project>> getPageProjects(@RequestParam(name = "page") String valueToSorted,
                                                             @RequestParam(name = "pageSize") int pageSize) {
        return new ResponseEntity<>(projectService.findAll(valueToSorted, pageSize), HttpStatus.OK);
    }

    @GetMapping(value = "/projectXml")
    @ResponseBody
    public ResponseEntity<String> getProjectXml() {
        ClassLoader cl = getClass().getClassLoader();

        try (InputStream inputStream = cl.getResourceAsStream("xml/2020-03-21_00-05-27.NBCH.2006068836.xml")) {

            String result = IOUtils.toString(inputStream, Charset.forName("windows-1251"));
            return new ResponseEntity<>("Response project xml:\n" + result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/saveProjectXml")
    @ResponseBody
    public ResponseEntity<?> saveProjectXml() {

        try {
            Project projectOutput;
            File file = new File("testTask/src/main/resources/project.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Project.class, Task.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            projectOutput = (Project) unmarshaller.unmarshal(file);
            log.debug("projectXml: {}", projectOutput);

            projectService.saveXmlProject(projectOutput);
            return new ResponseEntity<>("Response project xml ok\n", HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>(e.toString(), HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "project/csvFile")
    @ResponseBody
    public ResponseEntity<String> getCsvFile() {
        try {
            Path csvReaderPath = Paths.get(ClassLoader.getSystemResource("csv/testFullData.csv").toURI());
//            Writer writer = new FileWriter("csv/writerTest.csv");

//            Path csvWriterPath = Paths.get(ClassLoader.getSystemResource("csv").toURI());
            log.info("csvReaderPath={}", csvReaderPath);
            List<CsvSimpleBean> csvTestBeansList = beanCsvService.beanBuilderExample(csvReaderPath, CsvSimpleBean.class);
//            beanCsvService.writeCsvFromBean(csvTestBeansList);
//            String agreementId = csvTestBeansList.get(0).getAgreementId();
//            String agreementDate = csvTestBeansList.get(0).getAgreementDate();
//            log.info("agreementId: {} - agreementDate: {}", agreementId, agreementDate);
            beanCsvService.mappingCsvSimpleBeanToCsvResultBean(csvTestBeansList);
            System.out.println("DONE!");

            return new ResponseEntity<>("Response project xml ok\n", HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.toString());
            e.printStackTrace();
            return new ResponseEntity<>(e.toString(), HttpStatus.NO_CONTENT);
        }
    }

//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    protected static class NotFoundException extends RuntimeException {
//        protected NotFoundException(String Id) {
//            super("could not find id" + Id + "'.");
//        }
//    }
}
