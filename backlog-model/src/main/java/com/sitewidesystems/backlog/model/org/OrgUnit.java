package com.sitewidesystems.backlog.model.org;

/**
 * An entity is something that can have ownership and access permissions within the
 * system. Person and Group objects inherit from it and it is used to give them
 * a common lineage. It contains an id and a name.
 * User: gerwood
 * Date: 11/04/2009
 * Time: 8:05:20 PM
 */
public class OrgUnit {

    protected String id;
    private String name;

    public OrgUnit() {}

    public OrgUnit(String id) {
        this.setId(id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
