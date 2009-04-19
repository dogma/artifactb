package com.sitewidesystems.backlog.repository.impl;

import com.sitewidesystems.backlog.model.IterationTask;
import com.sitewidesystems.backlog.repository.IterationTaskRepository;
import com.sitewidesystems.backlog.repository.jdbc.AbstractJdbcRepository;
import com.sitewidesystems.backlog.exceptions.IterationTaskNotFoundException;
import com.sitewidesystems.backlog.exceptions.DataAccessException;

import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

/**
 * Created by IntelliJ IDEA.
 * Creator: gerwood
 * Created: 18/04/2009 8:17:02 AM
 */
public class IterationTaskJdbcRepository extends AbstractJdbcRepository implements IterationTaskRepository {

    ParameterizedRowMapper<IterationTask> iTaskMapper = new ParameterizedRowMapper<IterationTask>() {
        @Override
        public IterationTask mapRow(ResultSet resultSet, int i) throws SQLException {
            IterationTask iT = new IterationTask();

            iT.setTaskId(resultSet.getInt("TASKID"));
            iT.setIterationId(resultSet.getInt("ITERATIONID"));
            iT.setStoryId(resultSet.getInt("STORYID"));
            iT.setPoints(resultSet.getInt("POINTS"));
            iT.setState(resultSet.getString("STATE"));
            iT.setTitle(resultSet.getString("TITLE"));
            iT.setDescription(resultSet.getString("DESCRIPTION"));
            iT.setStarted(resultSet.getDate("STARTDATE"));
            iT.setCompleted(resultSet.getDate("COMPLETEDDATE"));
            iT.setAssigned(resultSet.getString("ASSIGNED"));

            return iT;
        }
    };
    @Override
    public IterationTask getTask(Integer taskId) throws IterationTaskNotFoundException, DataAccessException {
        String query = "SELECT DESCRIPTION, POINTS, STATE, TITLE, STARTDATE, COMPLETEDDATE, ASSIGNED ITERATIONID, STORYID, TASKID WHERE TASKID=?";

        try {
            return getJdbc().queryForObject(query,iTaskMapper, taskId);
        } catch (org.springframework.dao.DataAccessException e) {
            DataAccessException dae = new DataAccessException(e.getMessage());
            dae.setStackTrace(e.getStackTrace());
            throw dae;
        }
    }

    @Override
    public List<IterationTask> getTasks(Integer iterationId) throws DataAccessException {
        String query = "SELECT DESCRIPTION, POINTS, STATE, TITLE, STARTDATE, COMPLETEDDATE, ASSIGNED, ITERATIONID, STORYID, TASKID WHERE ITERATIONID=?";

        try {
            return getJdbc().query(query,iTaskMapper, iterationId);
        } catch (org.springframework.dao.DataAccessException e) {
            DataAccessException dae = new DataAccessException(e.getMessage());
            dae.setStackTrace(e.getStackTrace());
            throw dae;
        }
    }

    @Override
    public void setTask(IterationTask task) throws DataAccessException, IterationTaskNotFoundException {
        String query = "UPDATE BL_ITERATION_TASK SET DESCRIPTION=?, POINTS=?, STATE=?, TITLE=?, STARTDATE=?, COMPLETEDDATE=?, ASSIGNED=?, STORYID=?, ITERATIONID=? WHERE TASKID=?";
        getJdbc().update(query,
                task.getDescription(),
                task.getPoints(),
                task.getState(),
                task.getTitle(),
                task.getStarted(),
                task.getCompleted(),
                task.getAssigned(),
                task.getStoryId(),
                task.getIterationId(),
                task.getTaskId()
        );
    }

    @Override
    public void addTask(IterationTask task) throws DataAccessException {
        String query = "INSERT INTO BL_ITERATION_TASK (DESCRIPTION, POINTS, STATE, TITLE, STARTDATE, COMPLETEDDATE, ASSIGNED, STORYID, ITERATIONID, TASKID) VALUES (?,?,?,?,?,?,?)";
        getJdbc().update(query,
                task.getDescription(),
                task.getPoints(),
                task.getState(),
                task.getTitle(),
                task.getStarted(),
                task.getCompleted(),
                task.getAssigned(),
                task.getStoryId(),
                task.getIterationId(),
                getNextId()
        );
    }

    @Override
    public List<IterationTask> getIterationTasksByState(Integer iteration, String state) throws DataAccessException {
        String query = "SELECT DESCRIPTION, POINTS, STATE, TITLE, STARTDATE, COMPLETEDDATE, ASSIGNED, ITERATIONID, STORYID, TASKID WHERE ITERATIONID=? and STATE=?";
        try {
            return getJdbc().query(query,iTaskMapper,iteration, state);
        } catch (org.springframework.dao.DataAccessException e) {
            DataAccessException dae = new DataAccessException(e.getMessage());
            dae.setStackTrace(e.getStackTrace());
            throw dae;
        }
    }

    @Override
    public List<IterationTask> getStoryTasksByState(String storyId , String state) throws DataAccessException {
        String query = "SELECT DESCRIPTION, POINTS, STATE, TITLE, STARTDATE, COMPLETEDDATE, ASSIGNED, ITERATIONID, STORYID, TASKID WHERE STORYID=? and STATE=?";
        try {
            return getJdbc().query(query,iTaskMapper,storyId, state);
        } catch (org.springframework.dao.DataAccessException e) {
            DataAccessException dae = new DataAccessException(e.getMessage());
            dae.setStackTrace(e.getStackTrace());
            throw dae;
        }
    }

    @Override
    public List<IterationTask> getTasksByState(String state) throws DataAccessException {
        String query = "SELECT DESCRIPTION, POINTS, STATE, TITLE, STARTDATE, COMPLETEDDATE, ASSIGNED, ITERATIONID, STORYID, TASKID WHERE STATE=?";
        try {
            return getJdbc().query(query,iTaskMapper,state);
        } catch (org.springframework.dao.DataAccessException e) {
            DataAccessException dae = new DataAccessException(e.getMessage());
            dae.setStackTrace(e.getStackTrace());
            throw dae;
        }
    }

    public Integer getNextId () {
        String query = "SELECT BL_ITER_TASK_SEQ.NEXTVAL FROM DUAL";
        return getJdbc().queryForInt(query);
    }
}
