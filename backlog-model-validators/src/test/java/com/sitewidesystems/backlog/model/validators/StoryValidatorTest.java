package com.sitewidesystems.backlog.model.validators;

import org.junit.Test;
import org.junit.Assert;
import com.sitewidesystems.backlog.model.Story;

/**
 * Created by IntelliJ IDEA.
 * Creator: gerwood
 * Created: 13/04/2009 5:08:34 PM
 */
public class StoryValidatorTest {
    @Test
    public void testSupports() {
            StoryValidator sV = new StoryValidator();

            Assert.assertTrue("Doesn't support story object",sV.supports(Story.class));
            Assert.assertFalse("Didn't fail an unacceptable object class",sV.supports(String.class));
    }

    @Test
    public void testValidate() {
        // Add your code here
    }
}
