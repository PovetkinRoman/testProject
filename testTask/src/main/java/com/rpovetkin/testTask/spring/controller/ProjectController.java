package com.rpovetkin.testTask.spring.controller;

import com.rpovetkin.testTask.api.ProjectApi;
import com.rpovetkin.testTask.model.*;
import com.rpovetkin.testTask.service.ProjectService;
import com.rpovetkin.testTask.service.TaskService;
import com.rpovetkin.testTask.service.impl.BeanCsvServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

//@RestController
@Controller
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

    @GetMapping(value = "/testPostRequest")
    @ResponseBody
    public ResponseEntity<String> getTestPostRequest() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/v1/calculate";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("clientId", "2006308217");
        map.add("loanId", "2002903574");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        log.info("request={} / url={}", request, url);


        ResponseEntity<DebtRatio> response = restTemplate.postForEntity(url, request , DebtRatio.class);
        DebtRatio body = response.getBody();
        log.info("response={}", body);
        return new ResponseEntity<>(response.toString(), response.getStatusCode());
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
            Path csvReaderPath = Paths.get(ClassLoader.getSystemResource("csv/Input_ver1.csv").toURI());
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

    /**
     * Временное api для заполнения таблицы каникулярщиков, после выполнения выпилить
     */
    @RequestMapping(value = "/addHolidaysRecord", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addHolidaysRecord(@RequestParam(value = "file") MultipartFile file) throws IOException {

        try {
            InputStream is = file.getInputStream();
            List<String> doc = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.toList());

            doc.stream().parallel().forEach(el -> {
                String[] strs = el.split(";");
                LoanRepaymentHolidayAgreement holidayAgreement = new LoanRepaymentHolidayAgreement();

                holidayAgreement.setLoanId(Long.valueOf(strs[6]));
                holidayAgreement.setClientId(Long.valueOf(strs[7]));
                holidayAgreement.setCreateDate(new DateTime(strs[1]).toDate());
                holidayAgreement.setConfirmationDate(new DateTime(strs[1]).toDate());
                holidayAgreement.setPeriodOpenDate(new DateTime(strs[2]).toDate());
                holidayAgreement.setPeriodCloseDate(new DateTime(strs[3]).toDate());
                holidayAgreement.setCloseDate(new DateTime(strs[4]).toDate());
                holidayAgreement.setActionStake(new BigDecimal(strs[5]));
//                holidayAgreementDao.save(holidayAgreement);
                log.info("SystemController: holidayAgreement is saved ={}", holidayAgreement);
            });

            return new ResponseEntity<>("holiday record is added", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("holiday record is not added\n" + e, HttpStatus.CONFLICT);
        }
    }

//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    protected static class NotFoundException extends RuntimeException {
//        protected NotFoundException(String Id) {
//            super("could not find id" + Id + "'.");
//        }
//    }
}
