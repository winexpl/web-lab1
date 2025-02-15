package com.lab1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab1.entity.Project;
import com.lab1.repository.ProjectRepository;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public Project save(Project project) {
        return projectRepository.save(project);
    }

    public Project update(Project project) {
        return projectRepository.update(project);
    }

    public void remove(int id) {
        projectRepository.remove(id);
    }

    public Project findById(int id) {
        return projectRepository.findById(id);
    }

    public List<Project> findByRangeOfDates(String start_date, String end_date) {
        return projectRepository.findByRangeOfDates(start_date, end_date);
    }

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

}
