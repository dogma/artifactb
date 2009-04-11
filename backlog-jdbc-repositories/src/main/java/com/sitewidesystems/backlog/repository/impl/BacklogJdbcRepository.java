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
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This repository implements the BacklogRepository using a JDBC datastore.
 * User: gerwood
 * Date: 10/04/2009
 * Time: 9:37:05 PM
 */
public class BacklogJdbcRepository extends AbstractJdbcRepository implements BacklogRepository, StoryRepository {

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
            return s;
        }
    };

    @Override
    public Backlog getBacklog(Project project) throws DataAccessException, BacklogNotFoundException {
        return getBacklog(project.getProjectId());
    }

    @Override
    public Backlog getBacklog(String project) throws DataAccessException, BacklogNotFoundException {
        String query = "SELECT STORYID, TITLE, CLOSED, OPENED, OWNER, PRIORITY, PROJECTID, STATE, STORY  FROM BL_STORIES WHERE PROJECTID=? ORDER BY PRIORITY";

        Backlog b = new Backlog();
        b.setProjectId(project);
        try {
            b.setStories(getJdbc().query(query,storyMapper, project));
        } catch (EmptyResultDataAccessException e) {
            //If there are no stories, initialise an empty log.
            b.setStories(new ArrayList<Story>());
        } catch (org.springframework.dao.DataAccessException e) {
            DataAccessException dae = new  DataAccessException("Database access issue");
            dae.setStackTrace(e.getStackTrace());
            throw dae;
        }


        return b;
    }

    @Override
    public void setBacklog(Backlog backlog) throws DataAccessException, BacklogNotFoundException {
        String query = "UPDATE BL_STORIES SET PRIORITY=? WHERE STORYID=?";

        for(Story s : backlog.getStories()) {
            getJdbc().update(query,backlog.getStories().indexOf(s),s.getStoryId());
        }
    }

    @Override
    public void setStory(Story story) throws StoryNotFoundException, DataAccessException {
        String query = "UPDATE BL_STORIES SET TITLE=?, CLOSED=?, OPENED=?, OWNER=?, PROJECTID=?, STATE=?, STORY=? WHERE STORYID=?";

        try {
            getJdbc().update(query,story.getTitle(),
                story.getClosed(),
                story.getOpened(),
                story.getOwner(),
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
    public void addStory(Story story) throws DataAccessException {
        String query = "INSERT INTO BL_STORIES (TITLE,CLOSED,OPENED,OWNER,PRIORITY,PROJECTID,STATE,STORY,STORYID) VALUES (?,?,?,?,?,?,?,?,?)";

        story.setStoryId(getNextId());
        
        try {
            getJdbc().update(query,story.getTitle(),
                story.getClosed(),
                story.getOpened(),
                story.getOwner(),
                story.getPriority(),
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

    public Integer getNextId() throws org.springframework.dao.DataAccessException {
        String query = "SELECT BL_STORY_SEQ.NEXTVAL FROM DUAL";
        return getJdbc().queryForInt(query);
    }

    @Override
    public Story getStory(Integer story) throws StoryNotFoundException, DataAccessException {
        String query = "SELECT STORYID, TITLE, CLOSED, OPENED, OWNER, PRIORITY, PROJECTID, STATE, STORY  FROM BL_STORIES WHERE STORYID=?";

        try {
            return getJdbc().queryForObject(query,storyMapper,story);
        } catch (EmptyResultDataAccessException e) {
            StoryNotFoundException snfe = new StoryNotFoundException("Story ("+story+") was not found in datasource");
            snfe.setStackTrace(e.getStackTrace());
            throw snfe;
        } catch (org.springframework.dao.DataAccessException e) {
            DataAccessException dae = new  DataAccessException("Database access issue");
            dae.setStackTrace(e.getStackTrace());
            throw dae;
        }
    }
}
