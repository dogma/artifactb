package com.sitewidesystems.backlog.model.org;

import java.util.List;

/**
 * A group represents a group of Person and/or Group objects (i.e. a list of Entity objects)
 * It is used to give access/ownership within the system.
 * Creator: gerwood
 * Created: 11/04/2009 8:03:06 PM
 */
public class Group extends Entity {

    private List<Entity> members;
    private String description;

    public Group (String id) {
       super(id);
    }

    public String getGroupId() {
        return getId();
    }

    public void setGroupId(String groupId) {
        this.setId(groupId);
    }

    public List<Entity> getMembers() {
        return members;
    }

    public void setMembers(List<Entity> members) {
        this.members = members;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
