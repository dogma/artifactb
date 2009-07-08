package com.sitewidesystems.backlog.dao.hibernate;

import com.sitewidesystems.backlog.repository.PersonRepository;
import com.sitewidesystems.backlog.model.org.Person;
import com.sitewidesystems.backlog.exceptions.PersonNotFoundException;

/**
 * Created by IntelliJ IDEA.
 * Creator: gerwood
 * Created: 06/06/2009 6:45:37 PM
 */
public class PersonDao extends DaoHibernateBase implements PersonRepository {

    @Override
    public Person getPerson(String person) throws PersonNotFoundException {
        return (Person) getCurrentSession().load(Person.class,person);
    }
}
