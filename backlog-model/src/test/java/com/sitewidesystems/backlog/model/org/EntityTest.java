package com.sitewidesystems.backlog.model.org;

import org.junit.Test;
import org.junit.Assert;

/**
 * Tests for the Entity object
 * User: gerwood
 * Date: 11/04/2009
 * Time: 8:37:26 PM
 */
public class EntityTest {

    @Test
    public void settersAndGetters () {
        OrgUnit e = new OrgUnit("id");

        e.setName("EntityName");

        Assert.assertEquals("id",e.getId());
        Assert.assertEquals("EntityName",e.getName());
        
    }
}
