package com.rpovetkin.testTask.dao;

import com.rpovetkin.testTask.api.ProjectApi;
import com.rpovetkin.testTask.model.Project;

public interface ProjectDao extends GeneralDao<Project> {
    Project updateProject(long projectId, String projectName, String projectDescription);
    Project saveProject(ProjectApi projectApi);
}
