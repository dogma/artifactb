package com.sitewidesystems.backlog.model;

import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;

/**
 * Tests for the Backlog object
 * User: gerwood
 * Date: 09/04/2009
 * Time: 5:29:46 PM
 */
public class BacklogTests {

    @Test
    public void getterAndSetters () {

        Backlog b = new Backlog();

        b.setProjectId("projectA");

        ArrayList<Story> stories = new ArrayList<Story>();

        stories.add(new Story());
        stories.add(new Story());

        b.setStories(stories);

        Assert.assertEquals("projectA",b.getProjectId());
        Assert.assertEquals(stories,b.getStories());
    }
}
