package com.sitewidesystems.backlog.repository;

import com.sitewidesystems.backlog.model.org.Group;
import com.sitewidesystems.backlog.model.org.Entity;
import com.sitewidesystems.backlog.model.markers.GroupMember;
import com.sitewidesystems.backlog.exceptions.DataAccessException;
import com.sitewidesystems.backlog.exceptions.GroupNotFoundException;
import com.sitewidesystems.backlog.exceptions.GroupAlreadyExistsException;

import java.util.List;

/**
 * The Interface defines the required methods to contract as a Group provider.
 * User: gerwood
 * Date: 11/04/2009
 * Time: 8:02:28 PM
 */
public interface GroupRepository {

    /**
     * Loads a group from the datasource. This is fully populated with the Entities.
     * @param groupId The id of the group you want to load
     * @return Returns a Group object containing the details (and members) of the requested group
     * @throws GroupNotFoundException Thrown if the group can not be found
     * @throws DataAccessException Thrown if there is an issue accessing the data source.
     */
    public Group getGroup(String groupId) throws GroupNotFoundException, DataAccessException;

    public void setGroup (Group group) throws GroupNotFoundException, DataAccessException;
    public void addGroup (Group group) throws GroupAlreadyExistsException, DataAccessException;

    /**
     * Returns an alphabetically ordered list of all groups found within the system.
     * @return
     * @throws DataAccessException
     */
    public List<Group> getAllGroups() throws DataAccessException;
    public void setGroupMembership(List<GroupMember> groupMembers) throws DataAccessException;
    public void addGroupMember(Group g, Entity e) throws DataAccessException, GroupNotFoundException;

    /**
     * Used to check on the existence of a group...
     * @param groupId
     * @return
     * @throws DataAccessException
     */
    public Boolean exists (String groupId) throws DataAccessException;
}
