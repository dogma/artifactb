package com.sitewidesystems.backlog.model.org;

/**
 * The Person object represents a single user within the system. This is the most basic version
 * of a Person object and does not contain any special details.
 * User: gerwood
 * Date: 11/04/2009
 * Time: 8:03:36 PM
 */
public class Person extends Entity {

    public Person (String id) {
        super(id);
    }
    
    public String getUserId() {
        return getId();
    }

    public void setUserId(String userId) {
        this.setId(userId);
    }

}
