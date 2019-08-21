package com.rpovetkin.testTask.spring.repository;

import com.rpovetkin.testTask.model.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

//    List<Task> findByProject(Long projectId);
    Task findByTaskId(long id);

    List<Task> findAll();

    @Query(value = "select * from Task t where t.project_id = :projectId", nativeQuery = true)
    List<Task> findAllTaskInProject(@Param("projectId") Long projectId);
}
