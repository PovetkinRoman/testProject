package com.rpovetkin.testTask.dao.impl;

import com.rpovetkin.testTask.api.ProjectApi;
import com.rpovetkin.testTask.dao.ProjectDao;
import com.rpovetkin.testTask.model.Project;
import com.rpovetkin.testTask.spring.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
@Transactional
public class ProjectService implements ProjectDao {

    private ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project findById(long id) {
        Project project = projectRepository.findByProjectId(id);
        if(project == null) {
            throw new IllegalArgumentException("projectId is null");
        }
        return project;
    }

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public void save(Project obj) {
        projectRepository.save(obj);
    }

    @Override
    public Project saveProject(ProjectApi projectApi) {
        Project project = new Project();
        if(!projectApi.getProjectName().isEmpty() && projectApi.getProjectDescription() != null) {
            project.setProjectName(projectApi.getProjectName());
        } else {
            throw new IllegalArgumentException("projectName is empty");
        }
        if(!projectApi.getProjectDescription().isEmpty() && projectApi.getProjectDescription() != null) {
            project.setDescriptionText(projectApi.getProjectDescription());
        }
        save(project);
        return project;
    }

    @Override
    public void delete(long id) {
        projectRepository.deleteById(id);
    }

    @Override
    public List<Project> findAll(String valueToSort) {
        return projectRepository.findAll(Sort.by(valueToSort));
    }

    @Override
    public List<Project> findAll(String valueToSort, int pageSize) {
        Pageable pageableConf = PageRequest.of(0, pageSize, Sort.by(valueToSort).ascending());
        return projectRepository.findAll(pageableConf).getContent();
    }

    @Override
    public Project updateProject(long projectId, String projectName, String projectDescription) {
        Project project = findById(projectId);
        Assert.notNull(project, "project does not exist");

        if(!projectName.isEmpty()) project.setProjectName(projectName);
        if(!projectDescription.isEmpty()) project.setDescriptionText(projectDescription);
        save(project);
        return project;
    }
}
