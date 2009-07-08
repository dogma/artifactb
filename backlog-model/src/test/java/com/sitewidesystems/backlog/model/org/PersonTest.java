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
        Person p = new Person();
        p.setId("personId");
        p.setUsername("personUsername");

        p.setName("PersonName");

        Assert.assertEquals("personId",p.getId());
        Assert.assertEquals("personUsername",p.getUsername());

        Assert.assertEquals("PersonName",p.getName());
    }
}
