package com.rpovetkin.testTask.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

//@Setter
//@Getter
@Entity
@Table(name = "Task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
//    @ManyToOne(cascade = CascadeType.ALL)
    @Column(name = "project_ID")
    private Long projectId;
    private String taskName;
    private String description;

//    @Column(name = "priority", length = 1)
    private int priority = 0;
    private Date dateOfCreation = new Date();
    private Date dateOfLastModification;

    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus = TaskStatus.NEW;

    public Task() {
    }

    public Task(String description, String taskName) {
        this.taskName = taskName;
        this.description = description;
    }

    public Task(int priority, Date dateOfCreation, Date dateOfLastModification, TaskStatus taskStatus) {
        this.priority = priority;
        this.dateOfCreation = dateOfCreation;
        this.dateOfLastModification = dateOfLastModification;
        this.taskStatus = taskStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public Date getDateOfLastModification() {
        return dateOfLastModification;
    }

    public void setDateOfLastModification(Date dateOfLastModification) {
        this.dateOfLastModification = dateOfLastModification;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + id +
//                ", project=" + project +
                ", taskName='" + taskName + '\'' +
                ", dateOfCreation=" + dateOfCreation +
                '}';
    }
}

