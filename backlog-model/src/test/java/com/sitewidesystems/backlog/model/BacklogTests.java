package com.sitewidesystems.backlog.model;

import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Date;

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

        Story s1 = new Story();
        s1.setOpened(new Date());
        s1.setClosed(new Date());
        s1.setTitle("Story 1");
        s1.setStoryId(1);
        Story s2 = new Story();
        s2.setOpened(new Date());
        s2.setClosed(new Date());
        s2.setStoryId(2);
        s2.setTitle("Story 2");


        Story s3 = new Story();
        s3.setOpened(new Date());
        s3.setClosed(new Date());
        s3.setStoryId(3);
        s3.setTitle("Story 3");

        Story s4 = new Story();
        s4.setOpened(new Date());
        s4.setClosed(new Date());
        s4.setStoryId(4);
        s4.setTitle("Story 4");

        Story s5 = new Story();
        s5.setOpened(new Date());
        s5.setClosed(new Date());
        s5.setStoryId(5);
        s5.setTitle("Story 5");

        stories.add(s1);
        stories.add(s2);

        b.setStories(stories);

        b.add(s3);
        b.add(s4,3);
        b.add(s5,13);

        Assert.assertEquals("projectA",b.getProjectId());
        Assert.assertEquals(stories,b.getStories());

        Assert.assertEquals("s4 is not in the expected position",s4,b.getStory(3));
        Assert.assertEquals("s5 is not in the expected position",s5,b.getStory(4));

        Assert.assertEquals(s1,b.getStory(0));
        Assert.assertEquals(s2,b.getStory(1));
        Assert.assertEquals(s3,b.getStory(2));
        //Now move the stories around
        b.move(s2,0);
        Assert.assertEquals(s2,b.getStory(0));
        Assert.assertEquals(s1,b.getStory(1));
        Assert.assertEquals(s3,b.getStory(2));

        b.moveByPosition(1,0);
        Assert.assertEquals(s1,b.getStory(0));
        Assert.assertEquals(s2,b.getStory(1));
        Assert.assertEquals(s3,b.getStory(2));

        b.moveById(3,0);
        Assert.assertEquals(s3,b.getStory(0));
        Assert.assertEquals(s1,b.getStory(1));
        Assert.assertEquals(s2,b.getStory(2));


        b.moveById(5,0);
        Assert.assertEquals(s5,b.getStory(0));
        Assert.assertEquals(s3,b.getStory(1));
        Assert.assertEquals(s1,b.getStory(2));
        Assert.assertEquals(s2,b.getStory(3));
        Assert.assertEquals(s4,b.getStory(4));

        b.moveById(5,4);
        Assert.assertEquals(s3,b.getStory(0));
        Assert.assertEquals(s1,b.getStory(1));
        Assert.assertEquals(s2,b.getStory(2));
        Assert.assertEquals(s4,b.getStory(3));
        Assert.assertEquals(s5,b.getStory(4));

        b.moveById(200,4);
        Assert.assertEquals(s3,b.getStory(0));
        Assert.assertEquals(s1,b.getStory(1));
        Assert.assertEquals(s2,b.getStory(2));
        Assert.assertEquals(s4,b.getStory(3));
        Assert.assertEquals(s5,b.getStory(4));

    }
}
