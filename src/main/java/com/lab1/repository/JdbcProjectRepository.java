package com.lab1.repository;

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
        project.setBeginDate(rs.getDate("p_begin_date"));
        project.setEndDate(rs.getDate("p_end_date"));
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
    public Project update(Project project) {
        String sqlRequest = "UPDATE project " + //
                        "SET p_name=?, p_descr=?, p_begin_date=?, p_end_date=? " + //
                        "WHERE p_id=?; ";
        jdbcTemplate.update(sqlRequest, 
            project.getName(),
            project.getDescr(),
            project.getBeginDate(),
            project.getEndDate(),
            project.getId());
        
        //throw new UnsupportedOperationException("Unimplemented method 'update'");
        return project;
    }

    @Override
    public void remove(int id) {
        String sqlRequest = "DELETE FROM project WHERE p_id=?";
        jdbcTemplate.update(sqlRequest, id);
    }

    @Override
    public Project findById(int id) {
        String sqlRequest = "SELECT * FROM project WHERE p_id=?";
        Project project = this.jdbcTemplate.queryForObject(sqlRequest, projectMapper, id);
        return project;
    }

    @Override
    public List<Project> findByRangeOfDates(String start_date, String end_date) {
        String sqlRequest = "SELECT * FROM project WHERE " +
            "(p_begin_date BETWEEN CAST(? AS DATE) AND CAST(? AS DATE)) " +
            "AND (p_end_date BETWEEN CAST(? AS DATE) AND CAST(? AS DATE))";
        List<Project> project = this.jdbcTemplate.query(sqlRequest, projectMapper, start_date, end_date, start_date, end_date);
        return project;
    }

    @Override
    public List<Project> findAll() {
        String sqlRequest = "SELECT * FROM project";
        return jdbcTemplate.query(sqlRequest, projectMapper);
    }
    
}
