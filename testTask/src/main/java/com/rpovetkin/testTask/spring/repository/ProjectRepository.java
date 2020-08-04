package com.rpovetkin.testTask.spring.repository;

import com.rpovetkin.testTask.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

    Project findById(long id);

    List<Project> findAll();
    List<Project> findAll(Sort sort);
    Page<Project> findAll(Pageable pageable);
}
