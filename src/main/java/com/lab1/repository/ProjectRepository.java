package com.lab1.repository;

import java.util.Date;
import java.util.List;

import com.lab1.entity.Project;

public interface ProjectRepository {
    Project save(Project project);
    Project update(Project project);
    void remove(int id);
    Project findById(int id);
    List<Project> findByRangeOfDates(String start_date, String end_date);
    List<Project> findAll();
}
