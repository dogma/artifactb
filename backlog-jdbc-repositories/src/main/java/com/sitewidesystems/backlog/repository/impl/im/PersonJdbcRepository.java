package com.sitewidesystems.backlog.repository.impl.im;

import com.sitewidesystems.backlog.repository.PersonRepository;
import com.sitewidesystems.backlog.repository.jdbc.AbstractJdbcRepository;
import com.sitewidesystems.backlog.model.org.Person;
import com.sitewidesystems.backlog.exceptions.PersonNotFoundException;

import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.dao.DataAccessException;

/**
 * Created by IntelliJ IDEA.
 * User: gerwood
 * Date: 28/08/2009
 * Time: 7:47:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class PersonJdbcRepository extends AbstractJdbcRepository implements PersonRepository{

    ParameterizedRowMapper<Person> mapper = new ParameterizedRowMapper<Person>() {
        public Person mapRow(ResultSet resultSet, int i) throws SQLException {
            Person p = new Person();
            p.setId(resultSet.getString("PERSONID"));
            p.setName(resultSet.getString("NAME"));
            p.setUsername(resultSet.getString("USERNAME"));

            return p;
        }
    };

    public Person getPerson(String personid) throws PersonNotFoundException, DataAccessException {
        String query = "SELECT * FROM BL_PERSONS WHERE PERSONID=?";

        try {
            return getJdbc().queryForObject(query,mapper,personid);
        } catch (DataAccessException e) {
            throw e;
        }
    }

    public List<Person> getPeople(String userstring) throws DataAccessException {
        String query = "SELCT * FROM BL_PERSONS WHERE NAME=? or USERNAME=?";

        try {
            return getJdbc().query(query,mapper,"%"+userstring+"%","%"+userstring+"%");
        } catch (DataAccessException e) {
            throw e;
        }
    }

    public Person getPersonByUsername(String username) throws com.sitewidesystems.backlog.exceptions.DataAccessException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void addPerson(Person person) throws com.sitewidesystems.backlog.exceptions.DataAccessException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void updatePerson(Person person) throws com.sitewidesystems.backlog.exceptions.DataAccessException {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
