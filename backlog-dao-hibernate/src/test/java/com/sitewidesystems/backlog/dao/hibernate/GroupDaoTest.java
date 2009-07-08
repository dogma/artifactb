package com.sitewidesystems.backlog.dao.hibernate;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.SessionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.FlushMode;
import com.sitewidesystems.backlog.repository.GroupRepository;
import com.sitewidesystems.backlog.model.org.Group;
import com.sitewidesystems.backlog.exceptions.GroupNotFoundException;
import com.sitewidesystems.backlog.exceptions.DataAccessException;

/**
 * Created by IntelliJ IDEA.
 * Creator: gerwood
 * Created: 06/06/2009 6:29:32 PM
 */

public class GroupDaoTest {
    ApplicationContext appCon;

    @Before
    public void setup () {
        appCon = new ClassPathXmlApplicationContext("classpath:/spring-hibernate.xml");
        SessionFactory sessionFactory = (SessionFactory) appCon.getBean("sessionFactory");
        Session session = SessionFactoryUtils.getSession(sessionFactory, true);
        session.setFlushMode(FlushMode.NEVER);
        TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(session));
    }

    @Test
    public void loadGroup () {
        GroupRepository gR = (GroupRepository) appCon.getBean("groupRepository");

        try {
            Group g = gR.getGroup("home");
            Assert.assertNotNull(g);
            Assert.assertEquals("Home",g.getName()  );
        } catch (GroupNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (DataAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

}
