package com.example.ppmtool.repositories;

import java.util.List;

import com.example.ppmtool.domain.ProjectTask;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectTaskRepository extends CrudRepository<ProjectTask, Long> {
    public List<ProjectTask> findByProjectIdentifierOrderByPriority(String id);    
}
