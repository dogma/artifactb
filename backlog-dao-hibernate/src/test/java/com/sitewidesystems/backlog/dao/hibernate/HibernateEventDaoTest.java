package com.sitewidesystems.backlog.dao.hibernate;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.junit.After;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.SessionHolder;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.FlushMode;

import java.util.Date;

import com.sitewidesystems.backlog.repository.EventRepository;
import com.sitewidesystems.backlog.model.Event;

/**
 * Created by IntelliJ IDEA.
 * Creator: gerwood
 * Created: 06/06/2009 9:36:01 AM
 */
public class HibernateEventDaoTest {

    ApplicationContext appCon;

    @Before
    public void setup () {
        appCon = new ClassPathXmlApplicationContext("classpath:/spring-hibernate.xml");
        SessionFactory sessionFactory = (SessionFactory) appCon.getBean("sessionFactory");
        Session session = SessionFactoryUtils.getSession(sessionFactory, true);
        TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(session));
    }

    @After
    public void teardown () {
    }

    @Test
    public void basicTest () {
        Event e = new Event();
        e.setDate(new Date());
        e.setDescription("This is a description. It's a very good one to");
        e.setTitle("myTitle");
        EventRepository eD = (EventRepository) appCon.getBean("eventDao");
        eD.save(e);
        System.out.println("Event ID: "+e.getId());
        Event e2 = eD.getEvent(e.getId());
        System.out.println("e2: "+e2);
        Assert.assertEquals(e2.getId(),e.getId());

    }

    @Test
    public void removeTest () {
        Event e = new Event();
        e.setDate(new Date());
        e.setDescription("This is a description. It's a very good one to");
        e.setTitle("myTitle");

        EventRepository eD = (EventRepository) appCon.getBean("eventDao");
        eD.save(e);
        System.out.println("Event ID: "+e.getId());
        Event e2 = eD.getEvent(e.getId());
        System.out.println("e2: "+e2);
        Assert.assertEquals(e2.getId(),e.getId());

        Long i = e2.getId();
        eD.remove(e2);
    }
}
