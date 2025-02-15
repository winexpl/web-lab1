package com.lab1.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab1.entity.Project;
import com.lab1.repository.ProjectRepository;

@Service
public class TODOService {
    @Autowired
    private ProjectRepository projectRepository;

    public Project save(Project project) {
        return projectRepository.save(project);
    }

    public int update(Project project) {
        return projectRepository.update(project);
    }

    public int remove(int id) {
        return projectRepository.remove(id);
    }

    public Project findById(int id) {
        return projectRepository.findById(id);
    }

    public List<Project> findByRangeOfDates(LocalDate start_date, LocalDate end_date) {
        return projectRepository.findByRangeOfDates(start_date, end_date);
    }

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

}
