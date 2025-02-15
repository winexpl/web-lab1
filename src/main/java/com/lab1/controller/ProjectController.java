package com.lab1.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lab1.entity.Project;
import com.lab1.service.TODOService;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.PutMapping;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/api/projects")
@AllArgsConstructor
public class ProjectController {
    
    private TODOService service;

    @PostMapping
    ResponseEntity<Project> save(@RequestBody Project project) {
        Project savedProject = service.save(project);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedProject.getId())
                .toUri();

        return ResponseEntity.created(location).body(savedProject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> update(@PathVariable int id, @RequestBody Project project) {
        project.setId(id);
        int numberOfRowsAffected = service.update(project);
        if (numberOfRowsAffected == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(0);
        } else {
            return ResponseEntity.ok(numberOfRowsAffected);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> remove(@PathVariable int id) {
        int numberOfRowsAffected = service.remove(id);
        if (numberOfRowsAffected == 0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(0);
        } else {
            return ResponseEntity.ok(numberOfRowsAffected);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> findById(@PathVariable int id) {
        Project project = service.findById(id);
        return ResponseEntity.ofNullable(project);
    }

    @GetMapping
    List<Project> findByRangeOfDates(@RequestParam LocalDate start_date, @RequestParam LocalDate end_date) {
        return service.findByRangeOfDates(start_date, end_date);
    }

    @GetMapping("/all")
    public List<Project> findAll() {
        return service.findAll();
    }
    
}
