package com.sitewidesystems.backlog.repository;

import com.sitewidesystems.backlog.model.org.Group;
import com.sitewidesystems.backlog.exceptions.DataAccessException;
import com.sitewidesystems.backlog.exceptions.GroupNotFoundException;
import com.sitewidesystems.backlog.exceptions.GroupAlreadyExistsException;

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

}
