package com.example.ppmtool.services;

import com.example.ppmtool.repositories.ProjectRepository;
import com.example.ppmtool.domain.Project;
import com.example.ppmtool.exceptions.ProjectIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project) {
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        } catch (Exception e) {
            throw new ProjectIdException("Project ID '" + project.getProjectIdentifier().toUpperCase() + "' already exists");
        }
    }

    public Project findProjectById(String projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId);

        if (project == null) {
            throw new ProjectIdException("Project with ID '" + projectId + "' not found");
        }

        return project;
    }

    public Iterable<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    public void removeProjectById(String projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId);

        if (project == null) {
            throw new ProjectIdException("Cannot delete, project with ID '" + projectId.toUpperCase() + "' was not found");
        }
        projectRepository.delete(project);
    }
}