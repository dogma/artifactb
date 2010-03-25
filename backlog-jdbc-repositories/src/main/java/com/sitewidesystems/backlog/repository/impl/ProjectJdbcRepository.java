package com.sitewidesystems.backlog.repository.impl;

import com.sitewidesystems.backlog.repository.jdbc.AbstractJdbcRepository;
import com.sitewidesystems.backlog.repository.ProjectRepository;
import com.sitewidesystems.backlog.model.Project;
import com.sitewidesystems.backlog.model.org.OrgUnit;
import com.sitewidesystems.backlog.exceptions.DataAccessException;
import com.sitewidesystems.backlog.exceptions.ProjectNotFoundException;
import com.sitewidesystems.backlog.exceptions.ProjectAlreadyExistsException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

    private Log logger = LogFactory.getLog(getClass());

    ParameterizedRowMapper<Project> projMapper = new ParameterizedRowMapper<Project>() {
        @Override
        public Project mapRow(ResultSet resultSet, int i) throws SQLException {
            Project p = new Project();
            p.setDescription(resultSet.getString("DESCRIPTION"));
            p.setProjectId(resultSet.getString("PROJECTID"));
            p.setTitle(resultSet.getString("TITLE"));
            p.setOwner(new OrgUnit(resultSet.getString("OWNER")));
            p.setState(resultSet.getString("STATE"));
//            p.setTeam();

            return p;
        }
    };

    @Override
    public Project getProject(String projectId) throws DataAccessException, ProjectNotFoundException {
        String query = "SELECT PROJECTID, TITLE, DESCRIPTION, STATE, OWNER FROM BL_PROJECTS WHERE PROJECTID=?";

        try {
            return getJdbc().queryForObject(query, projMapper, projectId);
        } catch (EmptyResultDataAccessException e) {
            ProjectNotFoundException pnfe = new ProjectNotFoundException("No records returned for id " + projectId);
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
        String query = "UPDATE BL_PROJECTS SET TITLE=?, DESCRIPTION=?,STATE=?, OWNER=? WHERE PROJECTID=?";

        try {
            getJdbc().update(query,
                    project.getTitle(),
                    project.getDescription(),
                    project.getState(),
                    project.getOwner().getId(),
                    project.getProjectId());
        } catch (org.springframework.dao.DataAccessException e) {
            DataAccessException dae = new DataAccessException(e.getMessage());
            dae.setStackTrace(e.getStackTrace());
            throw dae;
        }
    }

    @Override
    public void addProject(Project project) throws DataAccessException, ProjectAlreadyExistsException {
        String query = "INSERT INTO BL_PROJECTS (TITLE,DESCRIPTION,PROJECTID,STATE,OWNER) VALUES (?,?,?,?,?)";

        logger.debug("Adding "+
                project.getTitle()+", "+
                project.getDescription()+", "+
                project.getState()+", "+
                project.getOwner()+", "+
                project.getProjectId()
        );

        System.out.println("Adding "+
                project.getTitle()+", "+
                project.getDescription()+", "+
                project.getState()+", "+
                project.getOwner()+", "+
                project.getProjectId());

        String owner = null;
        if(project.getOwner() != null) {
            owner = project.getOwner().getId();
        }
        try {
            getJdbc().update(query,
                    project.getTitle(),
                    project.getDescription(),
                    project.getState(),
                    owner,
                    project.getProjectId());

        } catch (org.springframework.dao.DataAccessException e) {
            DataAccessException dae = new DataAccessException(e.getMessage());
            dae.setStackTrace(e.getStackTrace());
            throw dae;
        }
    }

    @Override
    public List<Project> getProjects() throws DataAccessException {
        String query = "SELECT TITLE,DESCRIPTION,PROJECTID,OWNER, STATE FROM BL_PROJECTS";

        try {
            return getJdbc().query(query, projMapper);
        } catch (org.springframework.dao.DataAccessException e) {
            DataAccessException dae = new DataAccessException(e.getMessage());
            dae.setStackTrace(e.getStackTrace());
            throw dae;
        }
    }

    @Override
    public List<Project> getProjects(String owner) throws DataAccessException {
        String query = "SELECT TITLE,DESCRIPTION,PROJECTID,STATE,OWNER FROM BL_PROJECTS WHERE OWNER=? ORDER BY TITLE";

        try {
            return getJdbc().query(query, projMapper, owner);
        } catch (org.springframework.dao.DataAccessException e) {
            DataAccessException dae = new DataAccessException(e.getMessage());
            dae.setStackTrace(e.getStackTrace());
            throw dae;
        }
    }

    @Override
    public List<Project> getProjects(Integer startingFrom, Integer numberToFetch, String order) throws DataAccessException {

        try {
            if (order.equals("title")) {
                String query = "SELECT TITLE,DESCRIPTION,PROJECTID,OWNER, STATE FROM BL_PROJECTS WHERE ROWNUM >= ? and ROWNUM < ? ORDER BY TITLE";
                return getJdbc().query(query, projMapper, startingFrom, startingFrom + numberToFetch);
            } else if (order.equals("titleDesc")) {
                String query = "SELECT TITLE,DESCRIPTION,PROJECTID,OWNER, STATE FROM BL_PROJECTS WHERE ROWNUM >= ? and ROWNUM < ? ORDER BY TITLE DESC";
                return getJdbc().query(query, projMapper, startingFrom, startingFrom + numberToFetch);
            } else if (order.equals("titleDesc")) {
                String query = "SELECT TITLE,DESCRIPTION,PROJECTID,OWNER, STATE FROM BL_PROJECTS WHERE ROWNUM >= ? and ROWNUM < ? ORDER BY OWNER";
                return getJdbc().query(query, projMapper, startingFrom, startingFrom + numberToFetch);
            } else {
                String query = "SELECT TITLE,DESCRIPTION,PROJECTID,OWNER, STATE FROM BL_PROJECTS WHERE ROWNUM >= ? and ROWNUM < ? ORDER BY TITLE PROJECTID";
                return getJdbc().query(query, projMapper, startingFrom, startingFrom + numberToFetch);
            }
        } catch (org.springframework.dao.DataAccessException e) {
            DataAccessException dae = new DataAccessException(e.getMessage());
            dae.setStackTrace(e.getStackTrace());
            throw dae;
        }
    }

    @Override
    public Boolean exists(String projectId) throws DataAccessException {
        String query = "SELECT COUNT(PROJECTID) FROM BL_PROJECTS WHERE PROJECTID=?";

        try {
            Integer counted = getJdbc().queryForInt(query, projectId);
            if (counted > 0) {
                return true;
            }

            return false;
        } catch (org.springframework.dao.DataAccessException e) {
            DataAccessException dae = new DataAccessException(e.getMessage());
            dae.setStackTrace(e.getStackTrace());
            throw dae;
        }

    }

    public List<Project> getProjects(Integer startingFrom, Integer numberToFetch) throws DataAccessException {
        return getProjects(startingFrom, numberToFetch, "title");
    }
}
