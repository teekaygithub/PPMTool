package com.example.ppmtool.exceptions;

public class ProjectNotFoundExceptionResponse {
    
    private String ProjectNotFound;

    public ProjectNotFoundExceptionResponse(String projectNotFound) {
        setProjectNotFound(projectNotFound);
    }

    public String getProjectNotFound() {
        return ProjectNotFound;
    }

    public void setProjectNotFound(String projectNotFound) {
        this.ProjectNotFound = projectNotFound;
    }
}
