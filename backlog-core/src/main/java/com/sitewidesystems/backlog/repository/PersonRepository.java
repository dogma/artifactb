package com.sitewidesystems.backlog.repository;

import com.sitewidesystems.backlog.model.org.Person;
import com.sitewidesystems.backlog.exceptions.PersonNotFoundException;
import com.sitewidesystems.backlog.exceptions.DataAccessException;
import com.sitewidesystems.backlog.exceptions.PersonAlreadyExistsException;

import java.util.List;

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
    public Person getPerson (String person) throws PersonNotFoundException, DataAccessException;

    public List<Person> getPeople(String userstring) throws DataAccessException;

    public Person getPersonByUsername (String username) throws DataAccessException, PersonNotFoundException;

    public void addPerson(Person person) throws DataAccessException, PersonAlreadyExistsException;

    public void updatePerson(Person person) throws DataAccessException, PersonNotFoundException;
}
