package com.rpovetkin.testTask.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "Task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projectId")
    private Project project;

    @Column(name = "taskName")
    private String taskName;

    @Column(name = "description")
    private String description;

    @Column(name = "priority", length = 1)
    private int priority = 0;

    @Column(name = "dateOfCreation")
    private LocalDateTime dateOfCreation = LocalDateTime.now();

    @Column(name = "dateOfLastModification")
    private LocalDateTime dateOfLastModification;

    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus = TaskStatus.NEW;
//    TODO: доделать вывод
    @Override
    public String toString() {return "Task name = " + taskName + "taskId = " + taskId;}
}

