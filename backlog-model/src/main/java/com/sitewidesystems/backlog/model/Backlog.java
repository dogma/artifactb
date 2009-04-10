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

    public void move(Story story, Integer position) {
        stories.remove(story);
        stories.add(position,story);
    }

    public void move (Integer story, Integer position) {
        for(Story s: stories) {
            if(s.getStoryId().equals(story)) {
                this.move(s,position);
                return;
            }
        }
    }

    public void add(Story story, Integer position) {
        stories.add(position,story);
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
