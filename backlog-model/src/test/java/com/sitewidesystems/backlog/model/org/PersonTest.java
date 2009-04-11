package com.sitewidesystems.backlog.model.org;

import org.junit.Test;
import org.junit.Assert;

/**
 * Tests for the Person Object
 * User: gerwood
 * Date: 11/04/2009
 * Time: 8:40:45 PM
 */
public class PersonTest {

    @Test
    public void settersAndGetters () {
        Person p = new Person("personId");

        p.setName("PersonName");

        Assert.assertEquals("personId",p.getId());
        Assert.assertEquals("personId",p.getUserId());

        Assert.assertEquals("PersonName",p.getName());
    }
}
