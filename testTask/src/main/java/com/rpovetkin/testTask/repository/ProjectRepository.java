package com.rpovetkin.testTask.repository;

import com.rpovetkin.testTask.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project, Long> {
    Project findByProjectId(Long id);

    Iterable<Project> findAll(Sort sort);
    Page<Project> findAll(Pageable pageable);
}
