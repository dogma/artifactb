package com.sitewidesystems.backlog.repository;

import com.sitewidesystems.backlog.model.StoryNote;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Creator: gerwood
 * Created: 17/04/2009 7:58:41 AM
 */
public interface StoryNotesRepository {

    public List<StoryNote> getNotes (Integer storyId);
    public void addNote(StoryNote n);
    
}
