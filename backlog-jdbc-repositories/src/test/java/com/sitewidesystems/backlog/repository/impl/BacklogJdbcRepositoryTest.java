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
import com.sitewidesystems.backlog.exceptions.StoryNotFoundException;
import com.sitewidesystems.backlog.exceptions.gigoExceptions.BadStoryIdException;

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
        }
    }

    @Test
    public void getBacklogWithBadProjectId() {
        BacklogJdbcRepository bRep = new BacklogJdbcRepository();
        bRep.setDataSource((DataSource) appCon.getBean("dataSource"));

        try {
            Project p = new Project();
            Backlog b = bRep.getBacklog(p);
            Assert.assertEquals(0,b.getStories().size());
        } catch (DataAccessException e) {
            Assert.fail("DataAccessException thrown should have returned thrown a BacklogNotFoundException: "+e.getMessage());
        }
    }

    @Test
    public void throwBacklogNotFoundException () {
        BacklogJdbcRepository bRep = new BacklogJdbcRepository();
        bRep.setDataSource((DataSource) appCon.getBean("dataSource"));

        try {
            Backlog b = bRep.getBacklog("project3-not-there");
            Assert.assertEquals(0,b.getStories().size());
        } catch (DataAccessException e) {
            Assert.fail("DataAccessException thrown should have returned thrown a BacklogNotFoundException: "+e.getMessage());
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
        BacklogJdbcRepository bRep = new BacklogJdbcRepository();
        bRep.setDataSource((DataSource) appCon.getBean("dataSource"));

        try {
            Backlog b = bRep.getBacklog("project1");
            Story s1 = b.getStory(0);

            s1.setTitle("This is the new title");
            s1.setStory("This is the new title");
            s1.setPoints(45);
            s1.setPriority(10);

                bRep.setStory(s1);

            //Refetch the backlog...
            Backlog b2 = bRep.getBacklog("project1");
            Assert.assertEquals(s1.getTitle(),b2.getStory(0).getTitle());
            Assert.assertEquals(s1.getStory(),b2.getStory(0).getStory());
            Assert.assertEquals(s1.getPoints(),b2.getStory(0).getPoints());
            Assert.assertEquals(s1.getPriority(),b2.getStory(0).getPriority());


        } catch (StoryNotFoundException e) {
            Assert.fail("Story did not set as expected. Threw StoryNotFoundException: "+e.getMessage());
        } catch (DataAccessException e) {
            Assert.fail("Backlog didn't re-arrange as expected. Threw DataAccessException:"+e.getMessage());
        }
    }

//    @Test
//    public void testAddStory() {
//        BacklogJdbcRepository bRep = new BacklogJdbcRepository();
//        bRep.setDataSource((DataSource) appCon.getBean("dataSource"));
//
//        try {
//            Backlog b = bRep.getBacklog("project1");
//            Story s1 = new Story();
//
//            bRep.setNextIdSql("SELECT NEXT VALUE FOR BL_STORY_SEQ");
//
//            s1.setTitle("This is the new title");
//            s1.setStory("This is the new story");
//            s1.setProject("project1");
//            s1.setPoints(45);
//            s1.setPriority(10);
//
//            bRep.addStory(s1);
//
//            //Refetch the backlog...
//            Backlog b2 = bRep.getBacklog("project1");
//            Assert.assertEquals(b.getStories().size()+1,b2.getStories().size());
//
//
//        } catch (DataAccessException e) {
//            Assert.fail("Backlog didn't re-arrange as expected. Threw DataAccessException:"+e.getMessage());
//        }
//    }

    @Test
    public void testGetStory() {
        BacklogJdbcRepository bRep = new BacklogJdbcRepository();
        bRep.setDataSource((DataSource) appCon.getBean("dataSource"));

        try {
            Story s1 = bRep.getStory(3);

            Assert.assertEquals("Title is wrong","Title3",s1.getTitle());
            Assert.assertNotNull("opened date didn't return: "+s1.getOpened(),s1.getOpened());
            Assert.assertNull("closed date didn't return null",s1.getClosed());
            Assert.assertEquals("Owner is wrong","nominalOwner",s1.getOwner());
            Assert.assertEquals("Priority is wrong",new Integer(10),s1.getPriority());
            Assert.assertEquals("Project is wrong","project1",s1.getProject());
            Assert.assertEquals("State is wrong","new",s1.getState());
            Assert.assertEquals("Story is wrong","This is the story of a lovely lady",s1.getStory());
            Assert.assertEquals("Points is wrong",new Integer(40),s1.getPoints());


        } catch (StoryNotFoundException e) {
            Assert.fail("Story did not set as expected. Threw StoryNotFoundException: "+e.getMessage());
        } catch (BadStoryIdException e) {
            Assert.fail("Not provided with a good story id: "+e.getMessage());
        } catch (DataAccessException e) {
            Assert.fail("Backlog didn't re-arrange as expected. Threw DataAccessException:"+e.getMessage());
        }
    }

    @Test
    public void testStoryExists () {
        BacklogJdbcRepository bRep = new BacklogJdbcRepository();
        bRep.setDataSource((DataSource) appCon.getBean("dataSource"));

        try {
            if(bRep.exists(1)) {
                Assert.assertTrue(true);
            } else {
                Assert.fail("Thrown 'not exists' should have thrown true.");
            }
        } catch (DataAccessException e) {
            Assert.fail("DataAccessException thrown: "+e.getMessage());
        }

    }

    @Test
    public void testStoryDoesntExist () {
        BacklogJdbcRepository bRep = new BacklogJdbcRepository();
        bRep.setDataSource((DataSource) appCon.getBean("dataSource"));

        try {
            if(bRep.exists(50)) {
                Assert.fail("Thrown 'exists' should have thrown false.");
            } else {
                Assert.assertTrue(true);
            }
        } catch (DataAccessException e) {
            Assert.fail("DataAccessException thrown: "+e.getMessage());
        }
    }


    @Test
    public void testGetStoryOnNull () {
        BacklogJdbcRepository bRep = new BacklogJdbcRepository();
        bRep.setDataSource((DataSource) appCon.getBean("dataSource"));

        try {
            bRep.getStory(null);
            Assert.fail("Should have thrown BadStoryIdException");
        } catch (StoryNotFoundException e) {
            Assert.fail("StoryNotFoundException. Should have thrown BadStoryIdException");
        } catch (DataAccessException e) {
            Assert.fail("DataAccessException. Should have thrown BadStoryIdException");
        } catch (BadStoryIdException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testGetStoryNonExistantId () {
        BacklogJdbcRepository bRep = new BacklogJdbcRepository();
        bRep.setDataSource((DataSource) appCon.getBean("dataSource"));

        try {
            bRep.getStory(50);
            Assert.fail("Should have thrown StoryNotFoundException");
        } catch (StoryNotFoundException e) {
            Assert.assertTrue(true);
        } catch (DataAccessException e) {
            Assert.fail("DataAccessException. Should have thrown StoryNotFoundException");
        } catch (BadStoryIdException e) {
            Assert.fail("BadStoryIdException. Should have thrown StoryNotFoundException");
        }
    }

//    @Test
//    public void testGetStoryBadDataSource () {
//        BacklogJdbcRepository bRep = new BacklogJdbcRepository();
//        bRep.setDataSource((DataSource) appCon.getBean("badDataSource"));
//
//        try {
//            bRep.getStory(50);
//            Assert.fail("Should have thrown DataAccessException");
//        } catch (StoryNotFoundException e) {
//            Assert.fail("StoryNotFoundException. Should have thrown DataAccessException");
//        } catch (DataAccessException e) {
//            Assert.assertTrue(true);
//        } catch (BadStoryIdException e) {
//            Assert.fail("BadStoryIdException. Should have thrown DataAccessException");
//        }
//    }
}
