package com.sitewidesystems.backlog.dao.hibernate;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.hibernate.Session;
import org.hibernate.HibernateException;
import org.hibernate.annotations.Table;
import org.hibernate.annotations.Entity;
import com.sitewidesystems.backlog.model.Event;
import com.sitewidesystems.backlog.repository.EventRepository;

import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * Creator: gerwood
 * Created: 06/06/2009 9:32:13 AM
 */
public class EventDao extends DaoHibernateBase implements EventRepository {

    private HibernateTemplate hibernateTemplate;

     public void setHibernateTemplate(HibernateTemplate hibernateTemplate){
         this.hibernateTemplate = hibernateTemplate;
     }

     public HibernateTemplate getHibernateTemplate(){
         return hibernateTemplate;
     }

    public Event getEvent(final Long id){
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
                return session.load(Event.class, id);
            }
        };
        return (Event)hibernateTemplate.execute(callback);
    }


    public void save(Event event){
//        HibernateCallback callback = new HibernateCallback() {
//            public Object doInHibernate(Session session)
//                throws HibernateException,SQLException {
//                session.saveOrUpdate(event);
//                return null;
//            }
//        };
//        hibernateTemplate.execute(callback);
        hibernateTemplate.save(event);
    }

    public void remove (final Event event) {
        HibernateCallback callback = new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                session.delete(event);
                return null;
            }
        };
    }
}
