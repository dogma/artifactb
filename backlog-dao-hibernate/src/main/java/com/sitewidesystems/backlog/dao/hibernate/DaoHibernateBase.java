package com.sitewidesystems.backlog.dao.hibernate;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Created by IntelliJ IDEA.
 * Creator: gerwood
 * Created: 06/06/2009 5:45:11 PM
 */
public abstract class DaoHibernateBase {
    private HibernateTemplate hibernateTemplate;

    private SessionFactory sessionFactory;

    public DaoHibernateBase () {
        
    }
    public DaoHibernateBase (SessionFactory sf) {
        sessionFactory = sf;
    }

    protected Session getCurrentSession() {
        return getSessionFactory().getCurrentSession();
    }

     public void setHibernateTemplate(HibernateTemplate hibernateTemplate){
         this.sessionFactory = hibernateTemplate.getSessionFactory();
         this.hibernateTemplate = hibernateTemplate;
     }

     public HibernateTemplate getHibernateTemplate(){
         return hibernateTemplate;
     }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
