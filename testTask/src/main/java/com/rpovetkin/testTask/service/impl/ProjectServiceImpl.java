package com.rpovetkin.testTask.service.impl;

import com.rpovetkin.testTask.api.ProjectApi;
import com.rpovetkin.testTask.model.Project;
import com.rpovetkin.testTask.model.Task;
import com.rpovetkin.testTask.service.ProjectService;
import com.rpovetkin.testTask.spring.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project findById(long id) {
        Project project = projectRepository.findById(id);
        if (project == null) {
            throw new IllegalArgumentException("projectId is null");
        }
        return project;
    }

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public void save(Project obj) {
        projectRepository.save(obj);
    }

    public Project saveProject(ProjectApi projectApi) {
        Project project = new Project();
        if (!projectApi.getProjectName().isEmpty() && projectApi.getProjectDescription() != null) {
            project.setProjectName(projectApi.getProjectName());
        } else {
            throw new IllegalArgumentException("projectName is empty\n" + projectApi.toString());
        }
        if (!projectApi.getProjectDescription().isEmpty() && projectApi.getProjectDescription() != null) {
            project.setDescriptionText(projectApi.getProjectDescription());
        }
        Task taskTest = new Task();
        Set<Task> taskSetTest = projectApi.getTaskSet();
//        taskTest.setTaskName(projectApi.getTaskSet().);
//        project.setTask();
        save(project);
        return project;
    }

    public void delete(long id) {
        projectRepository.deleteById(id);
    }

    public List<Project> findAll(String valueToSort) {
        return projectRepository.findAll(Sort.by(valueToSort));
    }

    public List<Project> findAll(String valueToSort, int pageSize) {
        Pageable pageableConf = PageRequest.of(0, pageSize, Sort.by(valueToSort).descending());
        return projectRepository.findAll(pageableConf).getContent();
    }

    public Project updateProject(long projectId, String projectName, String projectDescription) {
        Project project = findById(projectId);
        Assert.notNull(project, "project does not exist");

        if (!projectName.isEmpty()) project.setProjectName(projectName);
        if (!projectDescription.isEmpty()) project.setDescriptionText(projectDescription);
        save(project);
        return project;
    }

    public Project saveXmlProject(Project projectXml) {
        return projectRepository.save(projectXml);
    }
}
