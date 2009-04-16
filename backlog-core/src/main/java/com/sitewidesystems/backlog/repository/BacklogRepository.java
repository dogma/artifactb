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

    /**
     * Fetch a full backlog from the datasource. This method extracts the project id from the
     * project object and then hands it to the getBacklog(String) method.
     * @param project The project object that you want the backlog for.
     * @return Returns a populated backlog object.
     * @throws DataAccessException A DataAccessException indicates some form of error with the data layer. I.E. the data can't be reached.
     * @throws BacklogNotFoundException This means that the requested backlog is not available. Will be thrown depending on the way the data layer implements it.
     */
    public Backlog getBacklog(Project project) throws DataAccessException;

    /**
     * Fetch a full backlog from the datasource.
     * @param project A string containing the project id for which you want the backlog
     * @return Returns a populated backlog object.
     * @throws DataAccessException A DataAccessException indicates some form of error with the data layer. I.E. the data can't be reached.
     * @throws BacklogNotFoundException This means that the requested backlog is not available. Will be thrown depending on the way the data layer implements it.
     */
    public Backlog getBacklog(String project) throws DataAccessException;

    /**
     * Save the order of the stories back to the datasource. Set Backlog does not save individual stories.
     * @param backlog Takes a backlog object and saves it back to the datasource
     * @throws DataAccessException A DataAccessException indicates some form of error with the data layer. I.E. the data can't be reached.
     * @throws BacklogNotFoundException This means that the requested backlog is not available. Will be thrown depending on the way the data layer implements it.
     */
    public void setBacklog(Backlog backlog) throws DataAccessException, BacklogNotFoundException;
}
