package com.sitewidesystems.backlog.dao.hibernate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.SessionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.junit.Before;
import org.junit.Test;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.FlushMode;
import com.sitewidesystems.backlog.repository.PersonRepository;
import com.sitewidesystems.backlog.exceptions.PersonNotFoundException;
import com.sitewidesystems.backlog.model.org.Person;

/**
 * Created by IntelliJ IDEA.
 * Creator: gerwood
 * Created: 06/06/2009 6:48:09 PM
 */
public class PersonDaoTest {

    ApplicationContext appCon;

    @Before
    public void setup () {
        appCon = new ClassPathXmlApplicationContext("classpath:/spring-hibernate.xml");
        SessionFactory sessionFactory = (SessionFactory) appCon.getBean("sessionFactory");
        Session session = SessionFactoryUtils.getSession(sessionFactory, true);
        TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(session));
    }

    @Test
    public void loadPersonTest () {
        PersonRepository pR = (PersonRepository) appCon.getBean("personRepository");

        try {
            Person p = pR.getPerson("testa");
            System.out.println("Name: "+p.getName());
        } catch (PersonNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
