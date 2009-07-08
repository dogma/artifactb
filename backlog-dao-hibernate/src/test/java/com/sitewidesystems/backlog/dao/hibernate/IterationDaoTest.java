package com.sitewidesystems.backlog.dao.hibernate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.SessionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.FlushMode;
import com.sitewidesystems.backlog.repository.IterationRepository;
import com.sitewidesystems.backlog.model.Iteration;
import com.sitewidesystems.backlog.exceptions.DataAccessException;
import com.sitewidesystems.backlog.exceptions.IterationNotFoundException;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Creator: gerwood
 * Created: 06/06/2009 7:14:44 PM
 */
public class IterationDaoTest {

    ApplicationContext appCon;
    IterationRepository iR;

    @Before
    public void setup () {
        appCon = new ClassPathXmlApplicationContext("classpath:/spring-hibernate.xml");
        SessionFactory sessionFactory = (SessionFactory) appCon.getBean("sessionFactory");
        Session session = SessionFactoryUtils.getSession(sessionFactory, true);
////        session.setFlushMode(FlushMode.NEVER);
        TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(session));
        iR = (IterationRepository) appCon.getBean("iterationRepository");
    }

    @Test
    public void loadIteration() {

        try {
            Iteration i = iR.getIteration(1);
            i.setStarted(new Date());
            Assert.assertNotNull(i);
            Assert.assertEquals("Sprint 1",i.getTitle());
            return;
        } catch (DataAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IterationNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        Assert.fail("Has thrown an exception. Should not have.");
    }

    @Test
    public void saveIteration() {

        try {
            Iteration i = new Iteration();
            i.setTitle("My Sprint");
            i.setState("new");
            i.setProjectId("project1");
            i.setActiveDays(10);
            i.setStarted(new Date());
            iR.setIteration(i);
            Assert.assertNotNull(i);

            System.out.println("I id is : "+i.getIterationId());

            Iteration i2 = iR.getIteration(i.getIterationId());

            Assert.assertEquals("Titles are equal",i.getTitle(),i2.getTitle());

        } catch (DataAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IterationNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Test
    public void saveMultipleIterationsTestingIdSequence() {

        try {
            Iteration i = new Iteration();
            i.setTitle("My Sprint");
            i.setState("new");
            i.setProjectId("project1");
            i.setActiveDays(10);
            i.setStarted(new Date());
            iR.setIteration(i);
            Assert.assertNotNull(i);
            Assert.assertEquals(new Integer(200),i.getIterationId());

            Iteration i2 = new Iteration();
            i2.setTitle("My Sprint 1");
            i2.setState("new");
            i2.setProjectId("project1");
            i2.setActiveDays(10);
            i2.setStarted(new Date());
            iR.setIteration(i2);
            Assert.assertEquals(new Integer(201),i2.getIterationId());

            Iteration i3 = new Iteration();
            i3.setTitle("My Sprint 3");
            i3.setState("new");
            i3.setProjectId("project1");
            i3.setActiveDays(10);
            i3.setStarted(new Date());
            iR.setIteration(i3);
            Assert.assertEquals(new Integer(202),i3.getIterationId());

        } catch (DataAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IterationNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Test
    public void getAllProjectIterations () {
        try {
            List<Iteration> iterations = iR.getProjectIterations("project1");
            Assert.assertEquals(4,iterations.size());
            
        } catch (DataAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Test
    public void getIterationsByState () {
        try {
            List<Iteration> iterations = iR.getIterationsByState("new");
            Assert.assertEquals(2,iterations.size());

        } catch (DataAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Test
    public void getCurrentIteration () {
        try {
            try {
                Iteration i = iR.getCurrentIteration("project1");
                Assert.assertEquals("running",i.getState());
                Assert.assertEquals(new Integer(3), i.getIterationId());
            } catch (IterationNotFoundException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        } catch (DataAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
