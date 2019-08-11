package com.rpovetkin.testTask.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "Project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

    @Column(name = "projectName", length = 32)
    private String projectName;

    @Column(name = "descriptionText")
    private String descriptionText;

    @Column(name = "dateOfCreation")
    private LocalDateTime dateOfCreation = LocalDateTime.now();

    @Column(name = "dateOfLastModification")
    private LocalDateTime dateOfLastModification;

    @JsonIgnore
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private Set<Task> task;

    @Override
    public String toString() {return "Project name = " + projectName + "projectId = " + projectId;}

}
