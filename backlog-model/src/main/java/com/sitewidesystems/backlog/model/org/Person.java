package com.sitewidesystems.backlog.model.org;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.List;

/**
 * The Person object represents a single user within the system. This is the most basic version
 * of a Person object and does not contain any special details.
 * User: gerwood
 * Date: 11/04/2009
 * Time: 8:03:36 PM
 */
@Entity
@Table(name = "BL_PERSONS")
public class Person {


    private String name;
    private String id;
    private String username;
    private List<Membership> membership;

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Id
    @Column(name = "PERSONID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "USERNAME")
    @NaturalId
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Transient
    public List<Membership> getMembership() {
        return membership;
    }

    public void setMembership(List<Membership> membership) {
        this.membership = membership;
    }
}
