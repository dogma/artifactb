package com.sitewidesystems.backlog.model.org;

import com.sitewidesystems.backlog.model.markers.GroupMember;

import java.util.List;

/**
 * A group represents a group of Person and/or Group objects (i.e. a list of Entity objects)
 * It is used to give access/ownership within the system.
 * Creator: gerwood
 * Created: 11/04/2009 8:03:06 PM
 */
public class Group extends Entity {

    private List<GroupMember> members;
    private String description;

    public Group (String id) {
       super(id);
    }

    public Group () {}

    public String getGroupId() {
        return getId();
    }

    public void setGroupId(String groupId) {
        this.setId(groupId);
    }

    public List<GroupMember> getMembers() {
        return members;
    }

    public void setMembers(List<GroupMember> members) {
        this.members = members;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
