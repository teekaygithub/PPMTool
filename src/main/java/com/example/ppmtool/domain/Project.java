package com.example.ppmtool.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Project {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message="Project name is required")
    private String projectName;
    @NotBlank(message="Project identifier is required")
    @Size(min=4, max=5, message="Please use 4 to 5 characters for the project identifier")
    @Column(updatable=false, unique=true)
    private String projectIdentifier;
    @NotBlank(message="Project description is required")
    private String description;
    @JsonFormat(pattern="yyyy-mm-dd")
    private Date start_date;
    @JsonFormat(pattern="yyyy-mm-dd")
    private Date end_date;
    @JsonFormat(pattern="yyyy-mm-dd")
    private Date created_At;
    @JsonFormat(pattern="yyyy-mm-dd")
    private Date updated_At;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "project")
    private Backlog backlog;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    private String projectLeader;

    public Project() {}

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

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return start_date;
    }

    public void setStartDate(Date startDate) {
        this.start_date = startDate;
    }

    public Date getEndDate() {
        return end_date;
    }

    public void setEndDate(Date endDate) {
        this.end_date = endDate;
    }

    public Date getCreatedAt() {
        return created_At;
    }

    public void setCreatedAt(Date createdAt) {
        this.created_At = createdAt;
    }

    public Date getUpdatedAt() {
        return updated_At;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updated_At = updatedAt;
    }

    public Backlog getBacklog() {
        return backlog;
    }

    public void setBacklog(Backlog backlog) {
        this.backlog = backlog;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getProjectLeader() {
        return projectLeader;
    }

    public void setProjectLeader(String projectLeader) {
        this.projectLeader = projectLeader;
    }

    @PrePersist
    protected void onCreate() {
        this.created_At = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updated_At = new Date();
    }
}