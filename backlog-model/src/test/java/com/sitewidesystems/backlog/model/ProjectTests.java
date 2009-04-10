package com.sitewidesystems.backlog.model;

import org.junit.Test;
import org.junit.Assert;

/**
 * Tests for the Project object.
 * User: gerwood
 * Date: 09/04/2009
 * Time: 5:29:55 PM
 */
public class ProjectTests {

    @Test
    public void getterAndSetters () {

        Project p = new Project();

        p.setDescription("This is the description");
        p.setProjectId("projectA");
        p.setTitle("Project A Title");

        Assert.assertEquals("projectA",p.getProjectId());
        Assert.assertEquals("This is the description",p.getDescription());
        Assert.assertEquals("Project A Title",p.getTitle());
    }
}
