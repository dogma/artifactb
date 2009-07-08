package com.sitewidesystems.backlog.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * Creator: gerwood
 * Created: 17/04/2009 11:39:49 PM
 */
@Table(name = "BL_ITERATION")
@Entity()
@SequenceGenerator(name = "iteration_sequence_gen", sequenceName = "BL_ITERATION_SEQ")
public class Iteration {

    private Integer iterationId;
    private String projectId;
    private String title;
    private String state;
    private Date started;
    private Date completed;
    private Integer activeDays;

    @Id
    @Column(name = "ITERATIONID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "iteration_sequence_gen")
    public Integer getIterationId() {
        return iterationId;
    }

    public void setIterationId(Integer iterationId) {
        this.iterationId = iterationId;
    }

    @Column(name = "PROJECTID")
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Column(name = "TITLE")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "STATE")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Column(name = "STARTDATE")
    public Date getStarted() {
        return started;
    }

    public void setStarted(Date started) {
        this.started = started;
    }

    @Column(name = "COMPLETED_DATE")
    public Date getCompleted() {
        return completed;
    }

    public void setCompleted(Date completed) {
        this.completed = completed;
    }

    @Column(name = "ACTIVEDAYS")
    public Integer getActiveDays() {
        return activeDays;
    }

    public void setActiveDays(Integer activeDays) {
        this.activeDays = activeDays;
    }
}
