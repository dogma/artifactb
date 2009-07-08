package com.sitewidesystems.backlog.model;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * Creator: gerwood
 * Created: 17/04/2009 7:53:59 AM
 */
@Entity
@Table(name="BL_STORY_NOTES")
public class StoryNote {

    private Integer storyId;
    private Integer noteId;
    private String note;
    private String title;
    private Story story;

//    @Column(name = "STORYID")
    public Integer getStoryId() {
        return storyId;
    }

    public void setStoryId(Integer storyId) {
        this.storyId = storyId;
    }

    @Column(name = "NOTEID", nullable = false, unique = true)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Integer getNoteId() {
        return noteId;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    @Column(name = "CONTENT")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Column(name = "TITLE")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @JoinColumn(name = "STORYID")
    @ManyToOne()
    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }
}
