package com.sitewidesystems.backlog.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * A Story is the basic request component of the backlog system
 * User: gerwood
 * Date: Apr 5, 2009
 * Time: 2:11:16 PM
 */
@Entity
@Table(name = "BL_STORIES")
public class Story {

    private String title;
    private String story;
    private String state = "new";
    private String owner;
    private List<String> categories;
    private Date opened;
    private Date closed;
    private Integer storyId;
    private Integer priority;
    private Integer points = 0;
    private String project;


    @Column(name = "TITLE")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "STORY")
    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    @Column(name = "STATE")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Column(name = "OWNER")
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Transient
    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    @Column(name = "OPENED")
    public Date getOpened() {
        return opened;
    }

    public void setOpened(Date opened) {
        this.opened = opened;
    }

    @Column(name = "PRIORITY")
    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @Column(name = "PROJECTID")
    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    @Column(name = "CLOSED")
    public Date getClosed() {
        return closed;
    }

    public void setClosed(Date closed) {
        this.closed = closed;
    }

    @Column(name = "STORYID")
    @Id
    public Integer getStoryId() {
        return storyId;
    }

    public void setStoryId(Integer storyId) {
        this.storyId = storyId;
    }

    @Column(name = "POINTS")
    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
