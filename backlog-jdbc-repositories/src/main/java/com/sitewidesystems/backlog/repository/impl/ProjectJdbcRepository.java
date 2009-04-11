package com.sitewidesystems.backlog.repository.impl;

import com.sitewidesystems.backlog.repository.jdbc.AbstractJdbcRepository;
import com.sitewidesystems.backlog.repository.ProjectRepository;
import com.sitewidesystems.backlog.model.Project;
import com.sitewidesystems.backlog.exceptions.DataAccessException;
import com.sitewidesystems.backlog.exceptions.ProjectNotFoundException;
import com.sitewidesystems.backlog.exceptions.ProjectAlreadyExistsException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * This ProjectJdbcRepository implements the ProjectRepository with a JDBC datasource.
 * User: gerwood
 * Date: 11/04/2009
 * Time: 2:20:01 PM
 */
public class ProjectJdbcRepository extends AbstractJdbcRepository implements ProjectRepository {

    ParameterizedRowMapper<Project> projMapper = new ParameterizedRowMapper<Project>() {
        @Override
        public Project mapRow(ResultSet resultSet, int i) throws SQLException {
            Project p = new Project();
            p.setDescription(resultSet.getString("DESCRIPTION"));
            p.setProjectId(resultSet.getString("PROJECTID"));
            p.setTitle("TITLE");

            return p;
        }
    };

    @Override
    public Project getProject(String projectId) throws DataAccessException, ProjectNotFoundException {
        String query = "SELECT PROJECTID, TITLE, DESCRIPTION FROM BL_PROJECTS WHERE PROJECTID=?";

        try {
            return getJdbc().queryForObject(query,projMapper,projectId);
        } catch (EmptyResultDataAccessException e) {
            ProjectNotFoundException pnfe = new ProjectNotFoundException("No records returned for id "+projectId);
            pnfe.setStackTrace(e.getStackTrace());
            throw pnfe;
        } catch (org.springframework.dao.DataAccessException e) {
            DataAccessException dae = new DataAccessException(e.getMessage());
            dae.setStackTrace(e.getStackTrace());
            throw dae;
        }
    }

    @Override
    public void setProject(Project project) throws DataAccessException, ProjectNotFoundException {
        String query = "UPDATE BL_PROJECTS SET TITLE=?, DESCRIPTION=? WHERE PROJECTID=?";

        try {
            getJdbc().update(query,
                project.getTitle(),
                project.getDescription(),
                project.getProjectId());
        } catch (org.springframework.dao.DataAccessException e) {
            DataAccessException dae = new DataAccessException(e.getMessage());
            dae.setStackTrace(e.getStackTrace());
            throw dae;
        }
    }

    @Override
    public void addProject(Project project) throws DataAccessException, ProjectAlreadyExistsException {
        String query = "INSERT INTO BL_PROJECTS (TITLE,DESCRIPTION,PROJECTID) VALUES (?,?,?)";

        try {
            getJdbc().update(query,
                project.getTitle(),
                project.getDescription(),
                project.getProjectId());
        } catch (org.springframework.dao.DataAccessException e) {
            DataAccessException dae = new DataAccessException(e.getMessage());
            dae.setStackTrace(e.getStackTrace());
            throw dae;
        }
    }

    @Override
    public List<Project> getProjects() throws DataAccessException {
        String query = "SELECT TITLE,DESCRIPTION,PROJECTID FROM BL_PROJECTS";

        try {
            return getJdbc().query(query,projMapper);
        } catch (org.springframework.dao.DataAccessException e) {
            DataAccessException dae = new DataAccessException(e.getMessage());
            dae.setStackTrace(e.getStackTrace());
            throw dae;
        }
    }

    @Override
    public List<Project> getProjects(String owner) throws DataAccessException {
        return getProjects();
    }
}
