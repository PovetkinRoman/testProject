package com.rpovetkin.testTask.service;

import com.rpovetkin.testTask.model.Project;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProjectService {
//    Project findProjectById(Long id);
    void saveProject(Project project);
    Boolean updateProject(Project project, String projectName, String description);
    void deleteProject(Long id);
    Iterable<Project> findAll();
    Iterable<Project> findAll(String valueToSort);
    Iterable<Project> findAll(String valueToSort, int pageSize);
    Optional<Project> findProjectById(Long id);
}
