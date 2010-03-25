package com.sitewidesystems.backlog.model;

import com.sitewidesystems.backlog.model.org.OrgUnit;
import com.sitewidesystems.backlog.model.org.Group;

import javax.persistence.*;
import java.util.Date;

/**
 * The Project object contains the basic descriptors for a project. The Project object is also
 * responsible for tying together the pieces of the project. This includes it's backlog and team members
 * (which will come later)
 * User: gerwood
 * Date: 07/04/2009
 * Time: 7:00:18 AM
 */
@Entity
@Table(name = "BL_PERSONS")
public class Project {

    private String projectId;
    private String title;
    private String description;
    private Date created;
    private OrgUnit owner;
    private Group team;
    private String state = "new";

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "TITLE")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Id
    @Column(name = "PROJECTID")
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Column(name = "CREATED")
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Column(name = "OWNER")
    @Transient
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

    public Group getTeam() {
        return team;
    }

    public void setTeam(Group team) {
        this.team = team;
    }
}
