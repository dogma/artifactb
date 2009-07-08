package com.sitewidesystems.backlog.dao.hibernate;

import com.sitewidesystems.backlog.repository.IterationRepository;
import com.sitewidesystems.backlog.model.Iteration;
import com.sitewidesystems.backlog.exceptions.DataAccessException;
import com.sitewidesystems.backlog.exceptions.IterationNotFoundException;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Query;

/**
 * Created by IntelliJ IDEA.
 * Creator: gerwood
 * Created: 06/06/2009 7:12:34 PM
 */
public class IterationDao implements IterationRepository {
    private HibernateTemplate hibernateTemplate;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    public void setHibernateTemplate (HibernateTemplate ht) {
        hibernateTemplate = ht;
    }

    @Override
    public Iteration getIteration(Integer iteration) throws DataAccessException, IterationNotFoundException {
        return (Iteration) hibernateTemplate.load(Iteration.class,iteration);
    }

    @Override
    public void setIteration(Iteration iteration) throws DataAccessException, IterationNotFoundException {
        hibernateTemplate.save(iteration);
    }

    @Override
    public void addIteration(Iteration iteration) throws DataAccessException {
        try {
            setIteration(iteration);
        } catch (IterationNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Override
    public List<Iteration> getProjectIterations(String projectId) throws DataAccessException {
        return hibernateTemplate.find("from Iteration as it where it.projectId = ?",projectId);
    }

    @Override
    public List<Iteration> getIterationsByState(String state) throws DataAccessException {
        return hibernateTemplate.find("from Iteration as it where it.state = ?",state);
    }

    @Override
    public List<Iteration> getRecentIteraions(String projectId) throws DataAccessException {
        return hibernateTemplate.find("from Iteration as it where it.projectId = ?",projectId);
    }

    @Override
    @Transactional
    public Iteration getCurrentIteration(String project) throws DataAccessException, IterationNotFoundException {
        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        Query query = session.createQuery("from Iteration as it where it.projectId = :project and it.state = :state");
        query.setString("project",project).setString("state","running");
        Iteration i = (Iteration) query.uniqueResult();
        return i;
//        return hibernateTemplate.find("from Iteration as it where it.projectId = ? and it.state = ?",r);
//        return null;
    }

    @Override
    @Transactional
    public List<Iteration> getIterationsByState(String projectId, String state) throws DataAccessException {
        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        Query query = session.createQuery("from Iteration as it where it.projectId = :project and it.state = :state");
        query.setString("project",projectId).setString("state",state);
        return query.list();
    }
}
