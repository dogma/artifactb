package com.sitewidesystems.backlog.repository.impl;

import com.sitewidesystems.backlog.repository.GroupRepository;
import com.sitewidesystems.backlog.repository.jdbc.AbstractJdbcRepository;
import com.sitewidesystems.backlog.model.org.Group;
import com.sitewidesystems.backlog.model.org.Entity;
import com.sitewidesystems.backlog.model.org.Person;
import com.sitewidesystems.backlog.model.markers.GroupMember;
import com.sitewidesystems.backlog.exceptions.GroupNotFoundException;
import com.sitewidesystems.backlog.exceptions.DataAccessException;
import com.sitewidesystems.backlog.exceptions.GroupAlreadyExistsException;

import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

/**
 * Created by IntelliJ IDEA.
 * Creator: gerwood
 * Created: 13/04/2009 8:29:03 PM
 */
public class GroupJdbcRepository extends AbstractJdbcRepository implements GroupRepository {

    ParameterizedRowMapper<Group> groupMapper = new ParameterizedRowMapper<Group>() {
        @Override
        public Group mapRow(ResultSet resultSet, int i) throws SQLException {
            Group g = new Group(resultSet.getString("GROUPID"));
            g.setDescription(resultSet.getString("DESCRIPTION"));
            g.setName(resultSet.getString("TITLE"));
            try {
                g.setMembers(getGroupMembers(g.getGroupId()));
            } catch (DataAccessException e) {
                SQLException sqlE = new SQLException(e.getMessage());
                sqlE.setStackTrace(e.getStackTrace());
                throw sqlE;
            }
            return g;
        }
    };

    ParameterizedRowMapper<GroupMember> groupMemberMapper = new ParameterizedRowMapper<GroupMember>() {
        @Override
        public GroupMember mapRow(ResultSet resultSet, int i) throws SQLException {

            if (resultSet.getString("TYPE").equals("PERSON")) {
                GroupMember gM = new GroupMember(resultSet.getString("MEMBERID"), GroupMember.MemberType.PERSON);
                return gM;
            }
            if (resultSet.getString("TYPE").equals("GROUP")) {
                GroupMember gM = new GroupMember(resultSet.getString("MEMBERID"), GroupMember.MemberType.GROUP);
                return gM;
            }

            return null;
        }
    };

    @Override
    public Group getGroup(String groupId) throws GroupNotFoundException, DataAccessException {
        String query = "SELECT GROUPID,TITLE,DESCRIPTION FROM BL_GROUPS WHERE GROUPID=?";

        try {
            Group g = getJdbc().queryForObject(query, groupMapper, groupId);
            return g;
        } catch (org.springframework.dao.DataAccessException e) {
            DataAccessException dae = new DataAccessException(e.getMessage());
            dae.setStackTrace(e.getStackTrace());
            throw dae;
        }
    }

    protected List<GroupMember> getGroupMembers(String groupId) throws DataAccessException {
        String query = "SELECT MEMBERID, TYPE FROM BL_GROUP_MEMBERS WHERE GROUPID=?";

        try {
            List<GroupMember> gM = getJdbc().query(query, groupMemberMapper, groupId);
            return gM;
        } catch (org.springframework.dao.DataAccessException e) {
            DataAccessException dae = new DataAccessException(e.getMessage());
            dae.setStackTrace(e.getStackTrace());
            throw dae;
        }
    }

    @Override
    public void setGroup(Group group) throws GroupNotFoundException, DataAccessException {
        String query = "UPDATE BL_GROUPS SET TITLE=?, DESCRIPTION=? WHERE GROUPID=?";

        try {
            getJdbc().update(query,
                    group.getName(),
                    group.getDescription(),
                    group.getGroupId()
            );
        } catch (org.springframework.dao.DataAccessException e) {
            DataAccessException dae = new DataAccessException(e.getMessage());
            dae.setStackTrace(e.getStackTrace());
            throw dae;
        }
    }

    @Override
    public Boolean exists(String groupId) throws DataAccessException {
        String query = "SELECT COUNT(GROUPID) FROM BL_GROUPS WHERE GROUPID=?";

        try {
            if (getJdbc().queryForInt(query, groupId) > 0) {
                return true;
            } else {
                return false;
            }
        } catch (org.springframework.dao.DataAccessException e) {
            DataAccessException dae = new DataAccessException(e.getMessage());
            dae.setStackTrace(e.getStackTrace());
            throw dae;
        }
    }

    @Override
    public void addGroup(Group group) throws GroupAlreadyExistsException, DataAccessException {

        String query = "INSERT INTO BL_GROUPS (TITLE, DESCRIPTION, GROUPID) VALUES (?,?,?)";

        try {
            getJdbc().update(query,
                    group.getName(),
                    group.getDescription(),
                    group.getGroupId()
            );
        } catch (org.springframework.dao.DataAccessException e) {
            DataAccessException dae = new DataAccessException(e.getMessage());
            dae.setStackTrace(e.getStackTrace());
            throw dae;
        }
    }

    @Override
    public List<Group> getAllGroups() throws DataAccessException {
        String query = "SELECT TITLE,DESCRIPTION,GROUPID FROM BL_GROUPS";

        try {
            return getJdbc().query(query,groupMapper);
        } catch (org.springframework.dao.DataAccessException e) {
            DataAccessException dae = new DataAccessException(e.getMessage());
            dae.setStackTrace(e.getStackTrace());
            throw dae;
        }
    }

    @Override
    public void setGroupMembership(String groupId, List<GroupMember> groupMembers) throws DataAccessException {
        String query = "INSERT INTO BL_GROUP_MEMBERS (GROUPID, MEMBERID, TYPE) VALUES (?,?,?)";
        String removeQuery = "DELETE FROM BL_GROUP_MEMBERS WHERE GROUPID=?";

        getJdbc().update(removeQuery,groupId);
        for(GroupMember membership: groupMembers) {
            getJdbc().update(query,groupId, membership.getId(), membership.getType());
        }
    }

    @Override
    public void addGroupMember(Group g, Entity e) throws DataAccessException, GroupNotFoundException {
        String query = "INSERT INTO BL_GROUP_MEMBERS (GROUPID, MEMBERID, TYPE) VALUES (?,?,?)";

        String type = "";
        if(e.getClass().equals(Person.class)) {
            type = "PERSON";
        } else if (e.getClass().equals(Group.class)) {
            type = "GROUP";
        } else {
            return;
        }
        try {
            getJdbc().update(query,g.getId(),e.getId(),type);
        } catch (org.springframework.dao.DataAccessException e1) {
            DataAccessException dae = new DataAccessException(e1.getMessage());
            dae.setStackTrace(e1.getStackTrace());
            throw dae;
        }
    }
}
