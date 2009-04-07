package com.sitewidesystems.backlog.exceptions;

/**
 * A DataAccessException is thrown by a repository when it can't communicate with its
 * data source.
 * User: gerwood
 * Date: 07/04/2009
 * Time: 7:01:45 AM
 */
public class DataAccessException extends Exception {
    public DataAccessException (String message) {
        super(message);
    }
}
