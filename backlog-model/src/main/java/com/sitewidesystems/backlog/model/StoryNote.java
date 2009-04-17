package com.sitewidesystems.backlog.model;

/**
 * Created by IntelliJ IDEA.
 * Creator: gerwood
 * Created: 17/04/2009 7:53:59 AM
 */
public class StoryNote {

    private Integer storyId;
    private Integer noteId;
    private String note;

    public Integer getStoryId() {
        return storyId;
    }

    public void setStoryId(Integer storyId) {
        this.storyId = storyId;
    }

    public Integer getNoteId() {
        return noteId;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
