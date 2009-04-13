package com.sitewidesystems.backlog.repository;

import com.sitewidesystems.backlog.model.Story;
import com.sitewidesystems.backlog.exceptions.DataAccessException;
import com.sitewidesystems.backlog.exceptions.StoryNotFoundException;
import com.sitewidesystems.backlog.exceptions.gigoExceptions.BadStoryIdException;

/**
 * The StoryRepository is used to manage the stories datasource.
 * User: gerwood
 * Date: 07/04/2009
 * Time: 7:41:11 AM
 */
public interface StoryRepository {

    public Story getStory(Integer story) throws StoryNotFoundException, DataAccessException, BadStoryIdException;
    public void setStory(Story story) throws StoryNotFoundException, DataAccessException, BadStoryIdException;
    public void addStory(Story story) throws DataAccessException;

    public Boolean exists (Integer storyId) throws DataAccessException;

}
