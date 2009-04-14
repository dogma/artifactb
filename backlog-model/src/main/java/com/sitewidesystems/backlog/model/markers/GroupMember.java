package com.sitewidesystems.backlog.model.markers;

/**
 * This object holds the membership details for 1 group membership.
 * It is setup as an object to hold the Type of membership (GROUP or PERSON)
 * It is used to provide a list of members to the Group object. This is instead of fully
 * loading the members. If a single members details are needed they can be loaded
 * separately by the PersonRepository, same for loading another Group. 
 * Creator: gerwood
 * Created: 13/04/2009 8:34:15 PM
 */
public class GroupMember {

    public GroupMember (String id, MemberType type) {
        this.id = id;
        this.type = type;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MemberType getType() {
        return type;
    }

    public void setType(MemberType type) {
        this.type = type;
    }

    public enum MemberType { GROUP, PERSON };

    private String id;
    private MemberType type;


}
