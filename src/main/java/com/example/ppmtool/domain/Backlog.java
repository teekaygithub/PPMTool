package com.example.ppmtool.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Backlog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer PTSequence = 0;
    private String projectIdentifier;

    // One to one with project
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id", nullable = false)
    @JsonIgnore
    private Project project;

    // One to many with project tasks
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "backlog", orphanRemoval = true)
    List<ProjectTask> projectTasks = new ArrayList<>();

    public Project getProject() {
        return project;
    }

    public Integer getPTSequence() {
        return PTSequence;
    }

    public void setPTSequence(Integer pTSequence) {
        this.PTSequence = pTSequence;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String PID) {
        this.projectIdentifier = PID;
    }

    public Backlog() {}

    public Long getId() {
        return id;
    }
}