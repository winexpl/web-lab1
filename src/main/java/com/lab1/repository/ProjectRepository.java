package com.lab1.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.lab1.entity.Project;

public interface ProjectRepository {
    Project save(Project project);
    int update(Project project);
    int remove(int id);
    Project findById(int id);
    List<Project> findByRangeOfDates(LocalDate start_date, LocalDate end_date);
    List<Project> findAll();
}
