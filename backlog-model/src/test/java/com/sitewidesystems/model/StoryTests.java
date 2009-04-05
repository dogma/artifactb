package com.sitewidesystems.model;

import org.junit.Test;
import org.junit.Assert;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: gerwood
 * Date: Apr 5, 2009
 * Time: 2:19:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class StoryTests {

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

        Assert.assertEquals("Title",s.getTitle());
        Assert.assertEquals("This is a story",s.getStory());
        Assert.assertEquals("closed",s.getState());
        Assert.assertEquals("backlogger",s.getProject());
        Assert.assertEquals("me",s.getOwner());

        Assert.assertEquals(1,s.getPriority());

        Assert.assertNotNull(s.getOpened());
        Assert.assertNotNull(s.getClosed());
        
    }
}
