package com.sitewidesystems.backlog.model.org;

import javax.persistence.*;
import java.util.List;

/**
 * A group represents a group of Person and/or Group objects (i.e. a list of Entity objects)
 * It is used to give access/ownership within the system.
 * Creator: gerwood
 * Created: 11/04/2009 8:03:06 PM
 */
@Entity
@Table(name = "BL_GROUPS")
public class Group {


    private String groupId;
    private List<Membership> members;
    private String description;
    private String name;

    public Group () {}

    @Id
    @Column(name = "GROUPID")
    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @JoinColumn(name="GROUPID")
    public List<Membership> getMembers() {
        return members;
    }

    public void setMembers(List<Membership> members) {
        this.members = members;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "TITLE")
    public String getName () {
        return this.name;
    }

    public void setName (String name) {
        this.name = name;
    }
}
