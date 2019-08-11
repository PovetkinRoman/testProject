package com.rpovetkin.testTask.service;

import com.rpovetkin.testTask.model.Project;
import com.rpovetkin.testTask.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepository;

    @Autowired
    public void setProjectRepository(ProjectRepository repository) {
        this.projectRepository = repository;
    }


    @Override
    public Optional<Project> findProjectById(Long id) {
        return projectRepository.findById(id);
    }

    @Override
    public void saveProject(Project project) {
        projectRepository.save(project);
    }

    @Override
    public Boolean updateProject(Project project, String projectName, String description) {
        if (projectName.isEmpty()) {
            return Boolean.FALSE;
        } else {
            project.setProjectName(projectName);
        }
        if (description != null && !description.isEmpty()) {
            project.setDescriptionText(description);
            project.setDateOfLastModification(LocalDateTime.now());
            projectRepository.save(project);
            return Boolean.TRUE;
        } else {
            project.setDateOfLastModification(LocalDateTime.now());
            projectRepository.save(project);
            return Boolean.TRUE;
        }
    }

    @Override
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    @Override
    public Iterable<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public Iterable<Project> findAll(String valueToSort) {
        return projectRepository.findAll(Sort.by(valueToSort));
    }

    @Override
    public Iterable<Project> findAll(String valueToSort, int pageSize) {
        Pageable pageableConf = PageRequest.of(0, pageSize, Sort.by(valueToSort).ascending());
        //TODO: сделать проверку на пустой возврат
        //TODO: уточнить что конкретно необходимо возвращать
        return projectRepository.findAll(pageableConf).getContent();
    }
}
