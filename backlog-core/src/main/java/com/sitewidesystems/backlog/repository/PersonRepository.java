package com.sitewidesystems.backlog.repository;

import com.sitewidesystems.backlog.model.org.Person;
import com.sitewidesystems.backlog.exceptions.PersonNotFoundException;

/**
 * Created by IntelliJ IDEA.
 * Creator: gerwood
 * Created: 06/06/2009 6:46:14 PM
 */
public interface PersonRepository {

    /**
     * Used to retrieve a person from the datastore.
     * @return
     * @throws PersonNotFoundException
     */
    public Person getPerson (String person) throws PersonNotFoundException;
    
}
