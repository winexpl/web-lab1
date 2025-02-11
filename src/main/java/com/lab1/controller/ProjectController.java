package com.lab1.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lab1.entity.Project;
import com.lab1.repository.ProjectRepository;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.PutMapping;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ProjectController {
    private ProjectRepository projectRepository;

    @PostMapping("/projects")
    Project save(@RequestBody Project project) {
        return projectRepository.save(project);
    }

    @PutMapping("/projects/{id}")
    public Project update(@PathVariable int id, @RequestBody Project project) {
        project.setId(id);
        return projectRepository.update(project);
    }

    @DeleteMapping("/projects/{id}")
    public void remove(@PathVariable int id) {
        projectRepository.remove(id);
    }

    @GetMapping("/projects/{id}")
    public Project findById(@PathVariable int id) {
        return projectRepository.findById(id);
    }

    @GetMapping("/projects")
    List<Project> findByRangeOfDates(@RequestParam String start_date, @RequestParam String end_date) {
        
        return projectRepository.findByRangeOfDates(start_date, end_date);
    }

    @GetMapping("/projects/all")
    public List<Project> findAll() {
        return projectRepository.findAll();
    }
    
}
