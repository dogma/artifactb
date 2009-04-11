package com.sitewidesystems.backlog.repository.jdbc;

import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.beans.factory.annotation.Required;

import javax.sql.DataSource;

/**
 * Provides an abstract class from which all JDBC repositories can extend.
 * This will provide a basic jdbc (SimpleJdbcTemplate object) handle to use.
 * User: gerwood
 * Date: 10/04/2009
 * Time: 9:47:06 PM
 */
public abstract class AbstractJdbcRepository {

    private SimpleJdbcTemplate jdbc;

    public SimpleJdbcTemplate getJdbc() {
        return jdbc;
    }

    @Required
    public void setDataSource(DataSource dataSource) {
        this.jdbc = new SimpleJdbcTemplate(dataSource);
    }
}
