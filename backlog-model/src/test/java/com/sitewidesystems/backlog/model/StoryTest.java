package com.sitewidesystems.backlog.model;

import org.junit.Test;
import org.junit.Assert;

import java.util.Date;
import java.util.ArrayList;

/**
 * Run tests on the Story object.
 * User: gerwood
 * Date: Apr 5, 2009
 * Time: 2:19:31 PM
 */
public class StoryTest {

    @Test
    public void SettersAndGetters () {
        Story s = new Story();

        s.setClosed(new Date());
        s.setOpened(new Date());

        s.setTitle("Title");
        s.setStory("This is a story");
        s.setState("closed");
        s.setProject("backlogger");
        s.setOwner("me");

        s.setPriority(1);

        ArrayList<String> cats = new ArrayList<String>();

        cats.add("categoryA");
        cats.add("categoryB");

        s.setCategories(cats);

        Assert.assertEquals("Title",s.getTitle());
        Assert.assertEquals("This is a story",s.getStory());
        Assert.assertEquals("closed",s.getState());
        Assert.assertEquals("backlogger",s.getProject());
        Assert.assertEquals("me",s.getOwner());

        Assert.assertEquals(new Integer(1),s.getPriority());

        Assert.assertEquals(cats,s.getCategories());

        Assert.assertNotNull(s.getOpened());
        Assert.assertNotNull(s.getClosed());
        
    }
}
