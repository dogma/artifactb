package com.sitewidesystems.backlog.repository;

import com.sitewidesystems.backlog.model.Backlog;
import com.sitewidesystems.backlog.model.Project;
import com.sitewidesystems.backlog.exceptions.DataAccessException;
import com.sitewidesystems.backlog.exceptions.BacklogNotFoundException;

/**
 * The basic BacklogRepository interface provides the required methods for an object
 * to be considered a BacklogRepository.
 * User: gerwood
 * Date: 07/04/2009
 * Time: 6:59:10 AM
 */
public interface BacklogRepository {

    public Backlog getBacklog(Project project) throws DataAccessException, BacklogNotFoundException;
    public Backlog getBacklog(String project) throws DataAccessException, BacklogNotFoundException;


    public void setBacklog(Backlog backlog) throws DataAccessException, BacklogNotFoundException;
}
