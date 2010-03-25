package com.sitewidesystems.logging;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by IntelliJ IDEA.
 * User: gerwood
 * Date: 26/08/2009
 * Time: 7:11:29 AM
 * To change this template use File | Settings | File Templates.
 */
public class SimpleJdbcAppender extends AppenderSkeleton {

    private SimpleJdbcTemplate jdbc;
    @Override
    protected void append(LoggingEvent event) {

        String query = "INSERT INTO SYSTEM_EVENT_LOG (LEVEL,CLASS,LOGGER,MESSAGE,EXCEPTION,TIMESTAMP,LOCALEINFO) VALUES (?,?,?,?,?,?,?)";

        getJdbc().update(query,
                event.getLevel(),
                event.getClass(),
                event.getLoggerName(),
                event.getRenderedMessage(),
                event.getThrowableStrRep().toString(),
                event.timeStamp,
                event.getLocationInformation().fullInfo
        );
    }

    @Override
    public boolean requiresLayout() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void close() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public SimpleJdbcTemplate getJdbc() {
        return jdbc;
    }

    public void setDataSource(DataSource ds) {
        this.jdbc = new SimpleJdbcTemplate(ds);
    }
}
