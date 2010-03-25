package com.sitewidesystems.backlog.dao.hibernate;

import com.sitewidesystems.backlog.repository.PersonRepository;
import com.sitewidesystems.backlog.model.org.Person;
import com.sitewidesystems.backlog.exceptions.PersonNotFoundException;
import com.sitewidesystems.backlog.exceptions.DataAccessException;
import com.sitewidesystems.backlog.exceptions.PersonAlreadyExistsException;

import java.util.List;
import java.util.Date;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataIntegrityViolationException;

/**
 * Created by IntelliJ IDEA.
 * Creator: gerwood
 * Created: 06/06/2009 6:45:37 PM
 */
public class PersonDao extends DaoHibernateBase implements PersonRepository {

    private String algorithm = "MD5";
    
    @Override
    public Person getPerson(String person) throws PersonNotFoundException {
        return (Person) getCurrentSession().get(Person.class,person);
    }

    public List<Person> getPeople(String userstring) {
        Criteria criteria = getCurrentSession().createCriteria(Person.class);
        criteria.add(Restrictions.like("username","%"+userstring+"%"));
        return criteria.list();
    }

    public Person getPersonByUsername(String username) throws DataAccessException, PersonNotFoundException {
        Criteria criteria = getCurrentSession().createCriteria(Person.class);
        criteria.add(Restrictions.like("username",username));
        List<Person> l = criteria.list();
        if(l.size() == 0) {
            PersonNotFoundException pnfe = new PersonNotFoundException("No match to requested username");
            throw pnfe;
        }
        return l.get(0);
    }

    public void addPerson(Person person) throws DataAccessException, PersonAlreadyExistsException {
        try {
            Person exists = getPersonByUsername(person.getUsername());
            PersonAlreadyExistsException pae = new PersonAlreadyExistsException("User ("+person.getUsername()+") already exists");
            throw pae;
        } catch (PersonNotFoundException pe) { //This is exactly what we expect to happen...
            if(person.getId() == null) {
                //Generate person id
                try {
                    MessageDigest digest = MessageDigest.getInstance(algorithm);
                    Date d = new Date();
                    digest.update((person.getUsername()+person.getName()+d.getTime()).getBytes());
                    person.setId(convertToHex(digest.digest()));
                } catch (NoSuchAlgorithmException e) {
                    DataAccessException dae = new DataAccessException(e.getMessage());
                    dae.setStackTrace(e.getStackTrace());
                    throw dae;
                }
            }

            try {
                System.out.println("Running save on person...");
                getCurrentSession().save(person);
            } catch (DataIntegrityViolationException e) {
                PersonAlreadyExistsException pae = new PersonAlreadyExistsException("Requested username already taken");
                pae.setStackTrace(e.getStackTrace());
                throw pae;
            } catch (HibernateException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }

    private static String convertToHex(byte[] data) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
        	int halfbyte = (data[i] >>> 4) & 0x0F;
        	int two_halfs = 0;
        	do {
	            if ((0 <= halfbyte) && (halfbyte <= 9))
	                buf.append((char) ('0' + halfbyte));
	            else
	            	buf.append((char) ('a' + (halfbyte - 10)));
	            halfbyte = data[i] & 0x0F;
        	} while(two_halfs++ < 1);
        }
        return buf.toString();
    }
    public void updatePerson(Person person) throws DataAccessException {
        getCurrentSession().update(person);
    }
}
