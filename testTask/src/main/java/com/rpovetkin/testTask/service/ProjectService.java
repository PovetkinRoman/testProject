package com.rpovetkin.testTask.service;

import com.rpovetkin.testTask.api.ProjectApi;
import com.rpovetkin.testTask.model.Project;

import java.util.List;

public interface ProjectService {
    Project findById(long id);
    Project saveProject(ProjectApi projectApi);
    void delete(long id);
    List<Project> findAll();
    List<Project> findAll(String valueToSort);
    List<Project> findAll(String valueToSort, int pageSize);
    Project updateProject(long projectId, String projectName, String projectDescription);
    Project saveXmlProject(Project projectXml);

}
