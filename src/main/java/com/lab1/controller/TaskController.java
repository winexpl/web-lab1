package com.lab1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lab1.repository.TaskRepository;
import com.lab1.service.TODOService;

import lombok.AllArgsConstructor;

@RestController
// @RequestMapping("/api/tasks")
@AllArgsConstructor
public class TaskController {
    private TODOService service;

    
}
