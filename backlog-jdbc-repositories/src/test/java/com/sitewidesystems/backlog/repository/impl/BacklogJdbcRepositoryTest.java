package com.sitewidesystems.backlog.repository.impl;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;

import com.sitewidesystems.backlog.model.Backlog;
import com.sitewidesystems.backlog.model.Project;
import com.sitewidesystems.backlog.model.Story;
import com.sitewidesystems.backlog.exceptions.DataAccessException;
import com.sitewidesystems.backlog.exceptions.BacklogNotFoundException;

/**
 * BacklogJdbcRepositoryTest will run through the tests for the BacklogJdbcRepository object
 * User: gerwood
 * Date: 10/04/2009
 * Time: 10:25:22 PM
 */
public class BacklogJdbcRepositoryTest {

    ApplicationContext appCon = new ClassPathXmlApplicationContext("classpath:/test-config.xml");

    @Before
    public void setup () {
        
    }
    
    @Test
    public void getBacklogWithProjectId() {
        BacklogJdbcRepository bRep = new BacklogJdbcRepository();
        bRep.setDataSource((DataSource) appCon.getBean("dataSource"));

        try {
            Project p = new Project();
            p.setProjectId("project1");
            Backlog b = bRep.getBacklog(p);
            Assert.assertEquals(3,b.getStories().size());
        } catch (DataAccessException e) {
            Assert.fail("DataAccessException thrown should have returned a list of three stories: "+e.getMessage());
        } catch (BacklogNotFoundException e) {
            Assert.fail("BacklogNotFoundException thrown should have returned a list of three stories"+e.getMessage());
        }
    }

    @Test
    public void getBacklogWithBadProjectId() {
        BacklogJdbcRepository bRep = new BacklogJdbcRepository();
        bRep.setDataSource((DataSource) appCon.getBean("dataSource"));

        try {
            Project p = new Project();
            Backlog b = bRep.getBacklog(p);
            Assert.fail("Should have thrown a BacklogNotFoundException");
        } catch (DataAccessException e) {
            Assert.fail("DataAccessException thrown should have returned thrown a BacklogNotFoundException: "+e.getMessage());
        } catch (BacklogNotFoundException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void throwBacklogNotFoundException () {
        BacklogJdbcRepository bRep = new BacklogJdbcRepository();
        bRep.setDataSource((DataSource) appCon.getBean("dataSource"));

        try {
            Backlog b = bRep.getBacklog("project3-not-there");
            Assert.fail("Should have thrown a BacklogNotFoundException");
        } catch (DataAccessException e) {
            Assert.fail("DataAccessException thrown should have returned thrown a BacklogNotFoundException: "+e.getMessage());
        } catch (BacklogNotFoundException e) {
            Assert.assertTrue(true);
        }


    }

    @Test
    public void testGetBacklogWithString() {
        BacklogJdbcRepository bRep = new BacklogJdbcRepository();
        bRep.setDataSource((DataSource) appCon.getBean("dataSource"));

        try {
            Backlog b = bRep.getBacklog("project2");
            Assert.assertEquals(1,b.getStories().size());
        } catch (DataAccessException e) {
            Assert.fail("DataAccessException thrown should have returned a list of three stories: "+e.getMessage());
        } catch (BacklogNotFoundException e) {
            Assert.fail("BacklogNotFoundException thrown should have returned a list of three stories"+e.getMessage());
        }
    }

    @Test
    public void testgetBacklogWithoutDB() {
        BacklogJdbcRepository bRep = new BacklogJdbcRepository();
//        bRep.setDataSource((DataSource) appCon.getBean("dataSource"));

        try {
            Backlog b = bRep.getBacklog("project2");
            Assert.fail("Should have thrown a DataAccessException");
        } catch (DataAccessException e) {
            Assert.assertTrue(true);
        } catch (BacklogNotFoundException e) {
            Assert.fail("BacklogNotFoundException thrown should have thrown a DataAccessException:"+e.getMessage());
        }
    }

    @Test
    public void testSettingBacklog () {
        BacklogJdbcRepository bRep = new BacklogJdbcRepository();
        bRep.setDataSource((DataSource) appCon.getBean("dataSource"));

        try {
            Backlog b = bRep.getBacklog("project1");
            Story s1 = b.getStory(0);
            Story s2 = b.getStory(1);
            Story s3 = b.getStory(2);
            b.moveByPosition(0,3);
            b.moveByPosition(1,0);

            bRep.setBacklog(b);

            Backlog b2 = bRep.getBacklog("project1");

            Assert.assertEquals(s1.getStoryId(),b2.getStory(2).getStoryId());
            Assert.assertEquals(s2.getStoryId(),b2.getStory(1).getStoryId());
            Assert.assertEquals(s3.getStoryId(),b2.getStory(0).getStoryId());


        } catch (DataAccessException e) {
            Assert.fail("Backlog didn't re-arrange as expected. Threw DataAccessException:"+e.getMessage());
        } catch (BacklogNotFoundException e) {
            Assert.fail("BacklogNotFoundException thrown should have re-arranged Stories:"+e.getMessage());
        }
    }

    @Test
    public void testSetStory() {
        Assert.fail("Create the test.");
    }

    @Test
    public void testAddStory() {
        Assert.fail("Create the test.");
    }

    @Test
    public void testGetNextId() {
        Assert.fail("Create the test.");
    }

    @Test
    public void testGetStory() {
        Assert.fail("Create the test.");
    }
}
