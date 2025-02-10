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
        String sqlRequest = "INSERT INTO project (p_name, p_descr, p_begin_date, p_end_date) VALUES (?, ?, ?, ?) RETURNING p_id";
        int id = jdbcTemplate.queryForObject(sqlRequest, Integer.class,
            project.getName(),
            project.getDescr(),
            project.getBeginDate(),
            project.getEndDate()).intValue();
        project.setId(id);
        return project;
    }

    @Override
    public Project update(Project project) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void remove(int id) {
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public Project findById(int id) {
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public List<Project> findByRangeOfDates(Date startDate, Date end_date) {
        throw new UnsupportedOperationException("Unimplemented method 'findByRangeOfDates'");
    }

    @Override
    public List<Project> findAll() {
        return jdbcTemplate.query("select * from project", projectMapper);
    }
    
}
