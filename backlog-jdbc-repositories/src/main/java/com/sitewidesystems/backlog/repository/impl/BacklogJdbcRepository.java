package com.sitewidesystems.backlog.repository.impl;

import com.sitewidesystems.backlog.repository.BacklogRepository;
import com.sitewidesystems.backlog.repository.StoryRepository;
import com.sitewidesystems.backlog.repository.jdbc.AbstractJdbcRepository;
import com.sitewidesystems.backlog.model.Backlog;
import com.sitewidesystems.backlog.model.Project;
import com.sitewidesystems.backlog.model.Story;
import com.sitewidesystems.backlog.exceptions.DataAccessException;
import com.sitewidesystems.backlog.exceptions.BacklogNotFoundException;
import com.sitewidesystems.backlog.exceptions.StoryNotFoundException;
import com.sitewidesystems.backlog.exceptions.gigoExceptions.BadStoryIdException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This repository implements the BacklogRepository using a JDBC datastore.
 * This repository (unusually perhaps) also fetches stories for the backlog. This
 * is specifically because the backlog is more or less just a list of the
 * stories (with some code to modify the ordering of the stories).
 * User: gerwood
 * Date: 10/04/2009
 * Time: 9:37:05 PM
 */
public class BacklogJdbcRepository extends AbstractJdbcRepository implements BacklogRepository, StoryRepository {

    private String nextIdSql = "SELECT BL_STORY_SEQ.NEXTVAL FROM DUAL";
    ParameterizedRowMapper<Story> storyMapper = new ParameterizedRowMapper<Story>() {
        @Override
        public Story mapRow(ResultSet resultSet, int i) throws SQLException {
            Story s = new Story();
            s.setStoryId(resultSet.getInt("STORYID"));
            s.setTitle(resultSet.getString("TITLE"));
            s.setClosed(resultSet.getDate("CLOSED"));
            s.setOpened(resultSet.getDate("OPENED"));
            s.setOwner(resultSet.getString("OWNER"));
            s.setPriority(resultSet.getInt("PRIORITY"));
            s.setProject(resultSet.getString("PROJECTID"));
            s.setState(resultSet.getString("STATE"));
            s.setStory(resultSet.getString("STORY"));
            s.setPoints(resultSet.getInt("POINTS"));
            return s;
        }
    };

    @Override
    public Backlog getBacklog(Project project) throws DataAccessException {
        return getBacklog(project.getProjectId());
    }

    @Override
    public Backlog getBacklog(String project) throws DataAccessException {
        if(getJdbc() == null) {
            DataAccessException dae = new DataAccessException("No datasource provided");
            throw dae;
        }
        String query = "SELECT STORYID, TITLE, CLOSED, OPENED, OWNER, PRIORITY, POINTS, PROJECTID, STATE, STORY  FROM BL_STORIES WHERE PROJECTID=? ORDER BY PRIORITY";

        Backlog b = new Backlog();
        b.setProjectId(project);
        try {
            b.setStories(getJdbc().query(query, storyMapper, project));
            return b;
        } catch (org.springframework.dao.DataAccessException e) {
            DataAccessException dae = new DataAccessException("Database access issue");
            dae.setStackTrace(e.getStackTrace());
            throw dae;
        }
    }

    @Override
    public void setBacklog(Backlog backlog) throws DataAccessException, BacklogNotFoundException {
        String query = "UPDATE BL_STORIES SET PRIORITY=? WHERE STORYID=?";

        for (Story s : backlog.getStories()) {
            getJdbc().update(query, backlog.getStories().indexOf(s), s.getStoryId());
        }
    }

    @Override
    public void setStory(Story story) throws StoryNotFoundException, DataAccessException {
        String query = "UPDATE BL_STORIES SET TITLE=?, CLOSED=?, OPENED=?, OWNER=?, PROJECTID=?, POINTS=?, STATE=?, STORY=? WHERE STORYID=?";

        try {
            getJdbc().update(query, story.getTitle(),
                    story.getClosed(),
                    story.getOpened(),
                    story.getOwner(),
                    story.getProject(),
                    story.getPoints(),
                    story.getState(),
                    story.getStory(),
                    story.getStoryId());
        } catch (org.springframework.dao.DataAccessException e) {
            DataAccessException dae = new DataAccessException(e.getMessage());
            dae.setStackTrace(e.getStackTrace());

            throw dae;
        }
    }

    @Override
    public void addStory(Story story) throws DataAccessException {
        String query = "INSERT INTO BL_STORIES (TITLE,CLOSED,OPENED,OWNER,PRIORITY,POINTS,PROJECTID,STATE,STORY,STORYID) VALUES (?,?,?,?,?,?,?,?,?,?)";

        story.setStoryId(getNextId());

        try {
            getJdbc().update(query, story.getTitle(),
                    story.getClosed(),
                    story.getOpened(),
                    story.getOwner(),
                    story.getPriority(),
                    story.getPoints(),
                    story.getProject(),
                    story.getState(),
                    story.getStory(),
                    story.getStoryId());
        } catch (org.springframework.dao.DataAccessException e) {
            DataAccessException dae = new DataAccessException(e.getMessage());
            dae.setStackTrace(e.getStackTrace());

            throw dae;
        }
    }

    @Override
    public Boolean exists(Integer storyId) throws DataAccessException {
        String query = "SELECT COUNT(STORYID) FROM BL_STORIES WHERE STORYID=?";

        try {
            Integer counted = getJdbc().queryForInt(query, storyId);
            if (counted > 0) {
                return true;
            }

            return false;
        } catch (org.springframework.dao.DataAccessException e) {
            DataAccessException dae = new DataAccessException(e.getMessage());
            dae.setStackTrace(e.getStackTrace());
            throw dae;
        }

    }

    public Integer getNextId() throws org.springframework.dao.DataAccessException {
        return getJdbc().queryForInt(this.getNextIdSql());
    }

    @Override
    public Story getStory(Integer story) throws StoryNotFoundException, DataAccessException, BadStoryIdException {
        if(story == null) {
            BadStoryIdException bsie = new BadStoryIdException("You MUST provide a story id, null is not valid");
            throw bsie;
        }
        String query = "SELECT STORYID, TITLE, CLOSED, OPENED, OWNER, PRIORITY, POINTS, PROJECTID, STATE, STORY  FROM BL_STORIES WHERE STORYID=?";

        try {
            return getJdbc().queryForObject(query, storyMapper, story);
        } catch (EmptyResultDataAccessException e) {
            StoryNotFoundException snfe = new StoryNotFoundException("Story (" + story + ") was not found in datasource");
            snfe.setStackTrace(e.getStackTrace());
            throw snfe;
        } catch (org.springframework.dao.DataAccessException e) {
            DataAccessException dae = new DataAccessException("Database access issue");
            dae.setStackTrace(e.getStackTrace());
            throw dae;
        }
    }

    public String getNextIdSql() {
        return nextIdSql;
    }

    public void setNextIdSql(String nextIdSql) {
        this.nextIdSql = nextIdSql;
    }
}
