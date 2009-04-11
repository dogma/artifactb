package com.sitewidesystems.backlog.exceptions;

/**
 * This exception is design to be thrown when a repository tries to write a new Project back
 * to it's datasource and finds an already existing entry.
 * User: gerwood
 * Date: 11/04/2009
 * Time: 5:35:04 PM
 */
public class ProjectAlreadyExistsException extends Exception {
    public ProjectAlreadyExistsException (String message) {
        super(message);
    }
}
