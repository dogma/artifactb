package com.sitewidesystems.backlog.repository;

import com.sitewidesystems.backlog.exceptions.DataAccessException;
import com.sitewidesystems.backlog.exceptions.ProjectNotFoundException;
import com.sitewidesystems.backlog.exceptions.ProjectAlreadyExistsException;
import com.sitewidesystems.backlog.model.Project;

import java.util.List;

/**
 * The ProjectRepository provides a business logic layer around the Project datasource.
 * This provides three basic methods: get, set and add. Equivalent to select, update and insert.
 * User: gerwood
 * Date: 11/04/2009
 * Time: 2:20:12 PM
 */
public interface ProjectRepository {

    public Project getProject(String projectId) throws DataAccessException, ProjectNotFoundException;
    public void setProject(Project project) throws DataAccessException, ProjectNotFoundException;
    public void addProject(Project project) throws DataAccessException, ProjectAlreadyExistsException;

    public List<Project> getProjects() throws DataAccessException;
    public List<Project> getProjects(String owner) throws DataAccessException;

    /**
     * This will return a range of projects starting from the position indicated by startingFrom and fetching numberToFetch
     * items.
     * @param startingFrom The position in the data source. This can be somewhat random given that the id is string based
     * @param numberToFetch The number of items that is expected to be returned. It can be less than this if the fetch runs out of matches.
     * @return List of projects within the specified range.
     * @throws DataAccessException Thrown if the Data source is not accessible
     */
    public List<Project> getProjects(Integer startingFrom, Integer numberToFetch) throws DataAccessException;

    /**
     * This performs essentially the same function as the getProjects(Integer startingFrom , Integer numberToFetch ) method
     * except it also includes an ordering preference.
     * @param startingFrom The position in the data source.
     * @param numberToFetch The number of items that is expected to be returned. It can be less than this if the fetch runs out of matches.
     * @param order The field to order by.
     * @return List of projects within the specified range.
     * @throws DataAccessException Thrown if the Data source is not accessible
     */
    public List<Project> getProjects(Integer startingFrom, Integer numberToFetch, String order) throws DataAccessException;
}
