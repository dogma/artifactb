package com.sitewidesystems.backlog.model.org;

import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;

import com.sitewidesystems.backlog.model.markers.GroupMember;

/**
 * Tests for the Group object
 * User: gerwood
 * Date: 11/04/2009
 * Time: 8:42:22 PM
 */
public class GroupTest {

    @Test
    public void settersAndGetters () {

        Group g = new Group("groupId");

        g.setDescription("The description");

        ArrayList<GroupMember> members = new ArrayList<GroupMember>();
        g.setMembers(members);
        g.setName("groupName");

        Assert.assertEquals("groupId",g.getId());
        Assert.assertEquals("groupId",g.getGroupId());
        Assert.assertEquals("groupName",g.getName());
        Assert.assertEquals("The description",g.getDescription());
        Assert.assertEquals(members,g.getMembers());
    }
}
