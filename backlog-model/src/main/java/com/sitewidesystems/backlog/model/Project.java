package com.sitewidesystems.backlog.model;

import com.sitewidesystems.backlog.model.org.OrgUnit;

import java.util.Date;

/**
 * The Project object contains the basic descriptors for a project. The Project object is also
 * responsible for tying together the pieces of the project. This includes it's backlog and team members
 * (which will come later)
 * User: gerwood
 * Date: 07/04/2009
 * Time: 7:00:18 AM
 */
public class Project {

    private String projectId;
    private String title;
    private String description;
    private Date date;
    private OrgUnit owner;
    private String state;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public OrgUnit getOwner() {
        return owner;
    }

    public void setOwner(OrgUnit owner) {
        this.owner = owner;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
