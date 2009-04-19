package com.sitewidesystems.backlog.repository;

import com.sitewidesystems.backlog.model.Iteration;
import com.sitewidesystems.backlog.exceptions.DataAccessException;
import com.sitewidesystems.backlog.exceptions.IterationNotFoundException;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Creator: gerwood
 * Created: 17/04/2009 11:37:55 PM
 */
public interface IterationRepository {

    /**
     * getIteration returns the Iteration object
     * @param iteration
     * @return
     * @throws DataAccessException
     * @throws IterationNotFoundException
     */
    public Iteration getIteration (Integer iteration) throws DataAccessException, IterationNotFoundException;
    public void setIteration (Iteration iteration) throws DataAccessException, IterationNotFoundException;
    public void addIteration (Iteration iteration) throws DataAccessException;
    public List<Iteration> getProjectIterations (String projectId) throws DataAccessException;

    public List<Iteration> getIterationsByState (String state) throws DataAccessException;
}
