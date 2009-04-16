package com.sitewidesystems.backlog.model;

import java.util.List;

/**
 * The Backlog object is in effect just a list of Story objects. It also however
 * keeps a record of the project to which it belongs which allows it to be used without loading
 * the project itself. The backlog object also contains one method called 'move' which is used to
 * re-prioritise objects within the backlog.
 * User: gerwood
 * Date: 07/04/2009
 * Time: 6:43:17 AM
 */
public class Backlog {

    private String projectId;
    private List<Story> stories;

    /**
     * Will move the provided story to the specified position in the array.
     * If the position greater than the index bounds it will add it to the end of the
     * backlog.
     * @param story
     * @param position
     */
    public void move(Story story, Integer position) {
        stories.remove(story);
        if(position >= stories.size()) {
            position = stories.size();
        }
        stories.add(position,story);
    }

    /**
     * Performs the same function as move but takes a stories id instead of the story object.
     * @param story
     * @param position
     */
    public void moveById (Integer story, Integer position) {
        for(Story s: stories) {
            if(s.getStoryId().equals(story)) {
                this.move(s,position);
                return;
            }
        }
    }

    /**
     * Performs the same function as move but takes the position of the story in
     * the backlog instead of a story object.
     * @param story
     * @param position
     */
    public void moveByPosition (Integer story, Integer position) {
        this.move(stories.get(story),position);
    }

    /**
     * Returns the requested story by position.
     * @param position The position in the backlog that you want retrieved.
     * @return 
     */
    public Story getStory(Integer position) {
        return stories.get(position);
    }

    /**
     * Add a story to the backlog at the specified position.
     * @param story The story to add
     * @param position The position the story should be added. If the position is outside of the 
     */
    public void add(Story story, Integer position) {
        if(position > stories.size()) {
            position = stories.size();
        }
        stories.add(position,story);
    }

    public void add(Story story) {
        stories.add(story);
    }

    public List<Story> getStories() {
        return stories;
    }

    public void setStories(List<Story> stories) {
        this.stories = stories;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
