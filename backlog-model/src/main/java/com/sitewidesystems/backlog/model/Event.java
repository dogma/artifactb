package com.sitewidesystems.backlog.model;

/**
 * Created by IntelliJ IDEA.
 * Creator: gerwood
 * Created: 31/05/2009 5:20:44 PM
 */
import com.sitewidesystems.backlog.model.org.Person;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "BL_EVENT")
@SequenceGenerator(name = "event_seq", sequenceName = "BL_EVENT_SEQ")
public class Event {

    private Long id;
    private String title;
    private Date date;
    private String description;
    private Person person;

    @Id
    @Column(name = "EVENTID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "event_seq")
    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    @Column(name = "OCCURED")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name = "TITLE")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String toString () {
        return "event["+id+","+title+","+date.toString()+","+description+"]"; 
    }

    @JoinColumn(name = "PERSONID")
    @ManyToOne
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}