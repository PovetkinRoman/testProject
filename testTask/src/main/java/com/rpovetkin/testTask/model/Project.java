package com.rpovetkin.testTask.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XmlRootElement(name = "project")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "project")
public class Project {

//    @XmlAttribute(name = "id")
    @XmlTransient
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //    @Column(name = "projectName", length = 32)
    @XmlElement(name = "projectName")
    private String projectName;

    //    @Column(name = "descriptionText")
    @XmlElement(name = "descriptionText")
    private String descriptionText;


    //    @CreationTimestamp
    @XmlElement(name = "dateOfCreation")
    private Date dateOfCreation = new Date();

    //    @Column(name = "dateOfLastModification")
    @UpdateTimestamp
    @XmlElement(name = "dateOfLastModification")
    private Date dateOfLastModification;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_ID")
    @XmlElement(name = "task")
    private List<Task> task;

    public Project() {
    }

    public Project(String projectName, String descriptionText, List<Task> task) {
        this.projectName = projectName;
        this.descriptionText = descriptionText;
        this.task = task;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDescriptionText() {
        return descriptionText;
    }

    public void setDescriptionText(String descriptionText) {
        this.descriptionText = descriptionText;
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

    public List<Task> getTask() {
        return task == null ? new ArrayList<>() : this.task;
    }

    public void setTask(List<Task> task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", projectName='" + projectName + '\'' +
                ", descriptionText='" + descriptionText + '\'' +
                ", dateOfCreation=" + dateOfCreation +
                ", dateOfLastModification=" + dateOfLastModification +
                ", task=" + task +
                '}';
    }
}
