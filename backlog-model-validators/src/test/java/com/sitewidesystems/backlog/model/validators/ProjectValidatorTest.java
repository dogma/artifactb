package com.sitewidesystems.backlog.model.validators;

import org.junit.Test;
import org.junit.Assert;
import org.springframework.validation.Errors;
import com.sitewidesystems.backlog.model.Project;

/**
 * Created by IntelliJ IDEA.
 * Creator: gerwood
 * Created: 13/04/2009 4:54:23 PM
 */
public class ProjectValidatorTest {

    /**
     * Validates the support method.
     */
    @Test
    public void testSupports() {
        ProjectValidator pV = new ProjectValidator();

        Assert.assertTrue("Doesn't support project object",pV.supports(Project.class));
        Assert.assertFalse("Didn't fail an unacceptable object class",pV.supports(String.class));
    }

    @Test
    public void testValidate() {
        Project p = new Project();
        ProjectValidator pV = new ProjectValidator();
    }
}
