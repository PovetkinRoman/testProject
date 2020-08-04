package com.rpovetkin.testTask.api;

import com.rpovetkin.testTask.model.Task;
import lombok.Data;

import java.util.Set;

@Data
public class ProjectApi {
    private String projectName;
    private String projectDescription;
    private Set<Task> taskSet;

    @Override
    public String toString() {
        return "ProjectApi{" +
                "projectName='" + projectName + '\'' +
                ", projectDescription='" + projectDescription + '\'' +
                ", taskSet=" + taskSet +
                '}';
    }
}
