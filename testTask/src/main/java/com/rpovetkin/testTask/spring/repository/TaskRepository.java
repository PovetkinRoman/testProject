package com.rpovetkin.testTask.spring.repository;

import com.rpovetkin.testTask.model.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

//    List<Task> findByProject(Long projectId);
//    Optional<Task> findById(Long id);
    Task findById(long id);
    List<Task> findAll();

    @Query(value = "select * from Task t where t.id = :id", nativeQuery = true)
    List<Task> findAllTaskInProject(@Param("id") Long id);
}
