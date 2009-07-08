package com.sitewidesystems.backlog.dao.hibernate;

import com.sitewidesystems.backlog.repository.GroupRepository;
import com.sitewidesystems.backlog.model.org.Group;
import com.sitewidesystems.backlog.model.org.OrgUnit;
import com.sitewidesystems.backlog.model.markers.GroupMember;
import com.sitewidesystems.backlog.exceptions.GroupNotFoundException;
import com.sitewidesystems.backlog.exceptions.DataAccessException;
import com.sitewidesystems.backlog.exceptions.GroupAlreadyExistsException;

import java.util.List;

import org.hibernate.SessionFactory;

/**
 * Created by IntelliJ IDEA.
 * Creator: gerwood
 * Created: 06/06/2009 5:44:17 PM
 */
public class GroupDao extends DaoHibernateBase implements GroupRepository {

    public GroupDao (SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public GroupDao () {
        
    }

    @Override
    public Group getGroup(String groupId) throws GroupNotFoundException, DataAccessException {
        return (Group) getCurrentSession().load(Group.class,groupId);
    }

    @Override
    public void save(Group group) throws GroupNotFoundException, DataAccessException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void addGroup(Group group) throws GroupAlreadyExistsException, DataAccessException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Group> getAllGroups() throws DataAccessException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setGroupMembership(String groupId, List<GroupMember> groupMembers) throws DataAccessException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void addGroupMember(Group g, OrgUnit e) throws DataAccessException, GroupNotFoundException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Boolean exists(String groupId) throws DataAccessException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public SessionFactory getSessionFactory() {
        return super.getSessionFactory();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
}
