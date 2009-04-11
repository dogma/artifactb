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
}
