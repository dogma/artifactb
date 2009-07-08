package com.sitewidesystems.backlog.repository.impl;

import com.sitewidesystems.backlog.repository.IterationRepository;
import com.sitewidesystems.backlog.repository.jdbc.AbstractJdbcRepository;
import com.sitewidesystems.backlog.model.Iteration;
import com.sitewidesystems.backlog.exceptions.DataAccessException;
import com.sitewidesystems.backlog.exceptions.IterationNotFoundException;

import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;

/**
 * Created by IntelliJ IDEA.
 * Creator: gerwood
 * Created: 18/04/2009 8:17:13 AM
 */
public class IterationJdbcRepository extends AbstractJdbcRepository implements IterationRepository {

    ParameterizedRowMapper<Iteration> itMapper = new ParameterizedRowMapper<Iteration>() {
        @Override
        public Iteration mapRow(ResultSet resultSet, int i) throws SQLException {
            Iteration iter = new Iteration();
            iter.setCompleted(resultSet.getDate("COMPLETED_DATE"));
            iter.setStarted(resultSet.getDate("STARTDATE"));
            iter.setIterationId(resultSet.getInt("ITERATIONID"));
            iter.setProjectId(resultSet.getString("PROJECTID"));
            iter.setState(resultSet.getString("STATE"));
            iter.setTitle(resultSet.getString("TITLE"));

            return iter;
        }
    };

    @Override
    public Iteration getIteration(Integer iteration) throws DataAccessException, IterationNotFoundException {
        String query = "SELECT ITERATIONID, PROJECTID, STARTDATE, ACTIVEDAYS, COMPLETED_DATE, TITLE, STATE FROM BL_ITERATION WHERE ITERATIONID=?";

        try {
            Iteration iter = getJdbc().queryForObject(query, itMapper, iteration);
            return iter;
        } catch (org.springframework.dao.DataAccessException e) {
            DataAccessException dae = new DataAccessException(e.getMessage());
            dae.setStackTrace(e.getStackTrace());
            throw dae;
        }
    }

    @Override
    public void setIteration(Iteration iteration) throws DataAccessException, IterationNotFoundException {
        String query = "UPDATE BL_ITERATION SET STARTDATE=?, ACTIVEDAYS=?, COMPLETED_DATE=?, TITLE=? WHERE ITERATIONID=?";

        try {
            getJdbc().update(query,
                iteration.getStarted(),
                iteration.getActiveDays(),
                iteration.getCompleted(),
                iteration.getTitle()
        );
        } catch (org.springframework.dao.DataAccessException e) {
            DataAccessException dae = new DataAccessException(e.getMessage());
            dae.setStackTrace(e.getStackTrace());
            throw dae;
        }
    }

    @Override
    public void addIteration(Iteration iteration) throws DataAccessException {
        String query = "INSERT INTO BL_INTERATION (STARTDATE, COMPLETED_DATE, ACTIVEDAYS, TITLE, STATE, PROJECTID, ITERATIONID) VALUES (?,?,?,?,?,?,?)";

        iteration.setState("new");
        getJdbc().update(query,
                iteration.getStarted(),
                iteration.getCompleted(),
                iteration.getActiveDays(),
                iteration.getTitle(),
                iteration.getState(),
                iteration.getProjectId(),
                getNextId()
        );
    }

    @Override
    public List<Iteration> getProjectIterations(String projectId) throws DataAccessException {
        String query = "SELECT STARTDATE, COMPLETED_DATE, ACTIVEDAYS, TITLE, STATE, PROJECTID, ITERATIONID FROM BL_ITERATION WHERE PROJECTID=?";
        return getJdbc().query(query,itMapper,projectId);
    }

    @Override
    public List<Iteration> getIterationsByState(String state) throws DataAccessException {
        String query = "SELECT STARTDATE, COMPLETED_DATE, ACTIVEDAYS, TITLE, STATE, PROJECTID, ITERATIONID FROM BL_ITERATION WHERE STATE=?";
        return getJdbc().query(query,itMapper,state);
    }

    @Override
    public List<Iteration> getRecentIteraions(String projectId) throws DataAccessException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Integer getNextId () {
        String query = "SELECT BL_ITERATION_SEQ.NEXTVAL FROM DUAL";
        return getJdbc().queryForInt(query);
    }

    @Override
    public Iteration getCurrentIteration(String project) throws DataAccessException, IterationNotFoundException {
        String query = "SELECT STARTDATE, COMPLETED_DATE, ACTIVEDAYS, TITLE, STATE, PROJECTID, ITERATIONID FROM BL_ITERATION WHERE PROJECTID=? and STATE=?";

        try {
            return getJdbc().queryForObject(query,itMapper,project,"current");
        } catch (EmptyResultDataAccessException e) {
            IterationNotFoundException infe = new IterationNotFoundException("There appears not to be a default iteration (i.e. a currently running one)");
            infe.setStackTrace(e.getStackTrace());
            throw infe;
        } catch (org.springframework.dao.DataAccessException e) {
            DataAccessException dae = new DataAccessException(e.getMessage());
            dae.setStackTrace(e.getStackTrace());
            throw dae;
        }
    }

    @Override
    public List<Iteration> getIterationsByState(String projectId, String state) throws DataAccessException {
        String query = "SELECT STARTDATE, COMPLETED_DATE, ACTIVEDAYS, TITLE, STATE, PROJECTID, ITERATIONID FROM BL_ITERATION WHERE PROJECTID=? and STATE=?";

        try {
            return getJdbc().query(query,itMapper,projectId,state);
        } catch (org.springframework.dao.DataAccessException e) {
            DataAccessException dae = new DataAccessException(e.getMessage());
            dae.setStackTrace(e.getStackTrace());
            throw dae;
        }
    }
}
