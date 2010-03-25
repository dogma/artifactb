package com.sitewidesystems.backlog.repository.impl;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;

import com.sitewidesystems.backlog.exceptions.DataAccessException;
import com.sitewidesystems.backlog.exceptions.ProjectNotFoundException;
import com.sitewidesystems.backlog.model.Project;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Creator: gerwood
 * Created: 16/04/2009 9:51:19 PM
 */
public class ProjectJdbcRepositoryTest {

    ApplicationContext appCon;

    @Before
    public void setup () {
        appCon = new ClassPathXmlApplicationContext("classpath:/test-config.xml");
    }

    @Test
    public void testGetProject() {
        ProjectJdbcRepository pRep = new ProjectJdbcRepository();
        pRep.setDataSource((DataSource) appCon.getBean("dataSource"));

        try {
            Project p = pRep.getProject("project1");

            Assert.assertEquals("project1",p.getProjectId());
            Assert.assertEquals("Project 1",p.getTitle());

        } catch (DataAccessException e) {
            Assert.fail("Threw DataAccessException");
        } catch (ProjectNotFoundException e) {
            Assert.fail("Threw ProjectNotFoundException");
        }
    }

    @Test
    public void testSetProject() {
        ProjectJdbcRepository pRep = new ProjectJdbcRepository();
        pRep.setDataSource((DataSource) appCon.getBean("dataSource"));

        try {
            Project p = pRep.getProject("project1");

            p.setTitle("New Project 1");
            pRep.setProject(p);

            Project p2 = pRep.getProject("project1");
            
            Assert.assertEquals(p.getProjectId(),p2.getProjectId());
            Assert.assertEquals(p.getTitle(),p2.getTitle());
            Assert.assertEquals(p.getDescription(),p2.getDescription());
            Assert.assertEquals(p.getCreated(),p2.getCreated());

        } catch (DataAccessException e) {
            Assert.fail("Threw DataAccessException");
        } catch (ProjectNotFoundException e) {
            Assert.fail("Threw ProjectNotFoundException");
        }
    }

    @Test
    public void testAddProject() {
        //Need to find a way of working around
    }

    @Test
    public void testGetProjectsUnlimited() {
        ProjectJdbcRepository pRep = new ProjectJdbcRepository();
        pRep.setDataSource((DataSource) appCon.getBean("dataSource"));

        try {
            List<Project> p = pRep.getProjects();

            Assert.assertEquals(4,p.size());

        } catch (DataAccessException e) {
            Assert.fail("Threw DataAccessException");
        }
    }

    @Test
    public void testAllProjectsByOwner() {
        ProjectJdbcRepository pRep = new ProjectJdbcRepository();
        pRep.setDataSource((DataSource) appCon.getBean("dataSource"));

        try {
            List<Project> p = pRep.getProjects("nominalOwner");

            Assert.assertEquals(3,p.size());

        } catch (DataAccessException e) {
            Assert.fail("Threw DataAccessException:"+e.getMessage());
        }
    }

//    @Test
//    public void testAllProjectsWithinRange() {
//        ProjectJdbcRepository pRep = new ProjectJdbcRepository();
//        pRep.setDataSource((DataSource) appCon.getBean("dataSource"));
//
//        try {
//            List<Project> p = pRep.getProjects(0,2,"title");
//
//            Assert.assertEquals(2,p.size());
//
//        } catch (DataAccessException e) {
//            Assert.fail("Threw DataAccessException:"+e.getMessage());
//        }
//    }
//
    @Test
    public void projectExists () {
        ProjectJdbcRepository pRep = new ProjectJdbcRepository();
        pRep.setDataSource((DataSource) appCon.getBean("dataSource"));

        try {
            Assert.assertTrue(pRep.exists("project1"));
            return;
        } catch (DataAccessException e) {
            Assert.fail("Returned DataAccessException, Should have returned true");
        }
    }

    @Test
    public void projectDoesntExists () {
        ProjectJdbcRepository pRep = new ProjectJdbcRepository();
        pRep.setDataSource((DataSource) appCon.getBean("dataSource"));

        try {
            Assert.assertFalse(pRep.exists("project145"));
            return;
        } catch (DataAccessException e) {
            Assert.fail("Returned DataAccessException, Should have returned false");
        }
    }
}
