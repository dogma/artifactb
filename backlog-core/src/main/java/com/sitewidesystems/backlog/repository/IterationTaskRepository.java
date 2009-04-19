package com.sitewidesystems.backlog.repository;

import com.sitewidesystems.backlog.exceptions.DataAccessException;
import com.sitewidesystems.backlog.exceptions.IterationNotFoundException;
import com.sitewidesystems.backlog.exceptions.IterationTaskNotFoundException;
import com.sitewidesystems.backlog.model.IterationTask;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Creator: gerwood
 * Created: 17/04/2009 11:38:03 PM
 */
public interface IterationTaskRepository {

    public IterationTask getTask(Integer taskId) throws IterationTaskNotFoundException, DataAccessException;
    public List<IterationTask> getTasks(Integer iterationId) throws DataAccessException;
    public void setTask(IterationTask task) throws DataAccessException, IterationTaskNotFoundException;
    public void addTask(IterationTask task) throws DataAccessException;

    /**
     * getTasksByState returns all tasks in the data store of a given state.
     * This method will return all tasks within the iteration of the requested state.
     * @param iterationId
     * @param state
     * @return
     */
    public List<IterationTask> getIterationTasksByState (Integer iterationId, String state) throws DataAccessException;

    /**
     * 
     * @param storyId
     * @param state
     * @return
     * @throws DataAccessException
     */
    public List<IterationTask> getStoryTasksByState (String storyId, String state) throws DataAccessException;

    /**
     * getTasksByState returns all tasks in the data store of a given state. This is a 'cross-cutting' method.
     * It does not take into account iteration constraints (this is used for Meta reporting).
     * @param state
     * @return
     */
    public List<IterationTask> getTasksByState (String state) throws DataAccessException;
    
}
