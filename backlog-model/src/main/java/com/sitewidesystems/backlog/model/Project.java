package com.sitewidesystems.backlog.model;

/**
 * The Project object contains the basic descriptors for a project. The Project object is also
 * responsible for tying together the pieces of the project. This includes it's backlog and team members
 * (which will come later)
 * User: gerwood
 * Date: 07/04/2009
 * Time: 7:00:18 AM
 */
public class Project {

    private String project;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }
}
