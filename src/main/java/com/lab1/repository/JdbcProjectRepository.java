package com.lab1.repository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.lab1.entity.Project;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class JdbcProjectRepository implements ProjectRepository {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private RowMapper<Project> projectMapper = (rs, rowNum) -> {
        Project project = new Project();
        project.setId(rs.getInt("p_id"));
        project.setName(rs.getString("p_name"));
        project.setDescr(rs.getString("p_descr"));
        var beginDate = rs.getDate("p_begin_date");
        project.setBeginDate(beginDate != null ? beginDate.toLocalDate() : null);
        var endDate = rs.getDate("p_begin_date");
        project.setEndDate(endDate != null ? endDate.toLocalDate() : null);
        return project;
    };

    @Override
    public Project save(Project project) {
        System.out.println(project.getId());
        String sqlRequest = "INSERT INTO project (p_name, p_descr, p_begin_date, p_end_date) VALUES (?, ?, ?, ?) RETURNING p_id";
        var id = jdbcTemplate.queryForObject(sqlRequest, Integer.class,
            project.getName(),
            project.getDescr(),
            project.getBeginDate(),
            project.getEndDate());
        if(id == null) {
            throw new IllegalStateException("The query returned null ID for the project.");
        }
        project.setId(id);
        return project;
    }

    @Override
    public int update(Project project) {
        String sqlRequest = "UPDATE project " + //
                        "SET p_name=?, p_descr=?, p_begin_date=?, p_end_date=? " + //
                        "WHERE p_id=?; ";
        return jdbcTemplate.update(sqlRequest,
            project.getName(),
            project.getDescr(),
            project.getBeginDate(),
            project.getEndDate(),
            project.getId());
    }

    @Override
    public int remove(int id) {
        String sqlRequest = "DELETE FROM project WHERE p_id=:id";
        return namedParameterJdbcTemplate.update(sqlRequest, Collections.singletonMap("id", id));
    }

    @Override
    public Project findById(int id) {
        String sqlRequest = "SELECT * FROM project WHERE p_id=?";
        try {
            return this.jdbcTemplate.queryForObject(sqlRequest, projectMapper, id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Project> findByRangeOfDates(LocalDate start_date, LocalDate end_date) {
        String sqlRequest = "SELECT * FROM project WHERE " +
            "(p_begin_date BETWEEN ? AND ?) " +
            "AND (p_end_date BETWEEN ? AND ?)";
        List<Project> project = this.jdbcTemplate.query(sqlRequest, projectMapper, start_date, end_date, start_date, end_date);
        return project;
    }

    @Override
    public List<Project> findAll() {
        String sqlRequest = "SELECT * FROM project";
        return namedParameterJdbcTemplate.query(sqlRequest, projectMapper);
    }
    
}
