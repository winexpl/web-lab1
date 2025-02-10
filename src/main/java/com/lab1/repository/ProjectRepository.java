package com.lab1.repository;

import java.util.Date;
import java.util.List;

import com.lab1.entity.Project;

public interface ProjectRepository {
    Project save(Project project);
    Project update(Project project);
    void remove(int id);
    Project findById(int id);
    List<Project> findByRangeOfDates(Date start_date, Date end_date);
    List<Project> findAll();
}
