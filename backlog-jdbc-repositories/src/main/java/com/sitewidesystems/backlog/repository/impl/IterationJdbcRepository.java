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
            iter.setCompleted(resultSet.getDate("COMPLETEDATE"));
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
        String query = "SELECT ITERATIONID, PROJECTID, STARTDATE, ACTIVEDAYS, COMPLETEDDATE, TITLE, STATE FROM BL_ITERATION WHERE ITERATIONID=?";

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
        String query = "UPDATE BL_ITERATION SET STARTDATE=?, ACTIVEDAYS=?, COMPLETEDDATE=?, TITLE=? WHERE ITERATIONID=?";

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
        String query = "INSERT INTO BL_INTERATION (STARTDATE, COMPLETEDDATE, ACTIVEDAYS, TITLE, STATE, PROJECTID, ITERATIONID) VALUES (?,?,?,?,?,?,?)";

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
        String query = "SELECT STARTDATE, COMPLETEDDATE, ACTIVEDAYS, TITLE, STATE, PROJECTID, ITERATIONID WHERE PROJECTID=?";
        return getJdbc().query(query,itMapper,projectId);
    }

    @Override
    public List<Iteration> getIterationsByState(String state) throws DataAccessException {
        String query = "SELECT STARTDATE, COMPLETEDDATE, ACTIVEDAYS, TITLE, STATE, PROJECTID, ITERATIONID WHERE STATE=?";
        return getJdbc().query(query,itMapper,state);
    }

    public Integer getNextId () {
        String query = "SELECT BL_ITERATION_SEQ.NEXTVAL FROM DUAL";
        return getJdbc().queryForInt(query);
    }

    @Override
    public Iteration getCurrentIteration(String project) throws DataAccessException {
        String query = "SELECT STARTDATE, COMPLETEDDATE, ACTIVEDAYS, TITLE, STATE, PROJECTID, ITERATIONID WHERE PROJECTID=? and STATE=?";

        try {
            return getJdbc().queryForObject(query,itMapper,project,"current");
        } catch (org.springframework.dao.DataAccessException e) {
            DataAccessException dae = new DataAccessException(e.getMessage());
            dae.setStackTrace(e.getStackTrace());
            throw dae;
        }
    }
}
