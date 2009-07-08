package com.sitewidesystems.backlog.repository;

import com.sitewidesystems.backlog.model.Event;

/**
 * Created by IntelliJ IDEA.
 * Creator: gerwood
 * Created: 06/06/2009 5:41:42 PM
 */
public interface EventRepository {
    Event getEvent(Long id);

    void save(Event event);

    void remove (Event event);
}
