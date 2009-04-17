package com.sitewidesystems.backlog.model;

import org.junit.Test;
import org.junit.Assert;

/**
 * Created by IntelliJ IDEA.
 * Creator: gerwood
 * Created: 17/04/2009 7:56:12 AM
 */
public class StoryNoteTest {

    @Test
    public void settersAndGetters () {
        StoryNote sN = new StoryNote();

        sN.setNote("A note");
        sN.setNoteId(1);
        sN.setStoryId(1);

        Assert.assertEquals("A note",sN.getNote());
        Assert.assertEquals(1,sN.getNoteId());
        Assert.assertEquals(1,sN.getStoryId());
    }
}
