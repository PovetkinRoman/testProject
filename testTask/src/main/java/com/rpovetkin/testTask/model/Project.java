package com.rpovetkin.testTask.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;
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
    @CreationTimestamp
    private OffsetDateTime offsetDateTime;

    @Column(name = "dateOfLastModification")
    @UpdateTimestamp
    private OffsetDateTime dateOfLastModification;

    @JsonIgnore
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private Set<Task> task;

    @Override
    public String toString() {return "Project name = " + projectName + "projectId = " + projectId;}
}
