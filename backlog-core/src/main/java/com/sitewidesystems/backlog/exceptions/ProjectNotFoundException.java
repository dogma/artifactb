package com.sitewidesystems.backlog.exceptions;

/**
 * Thrown when a Project object is requested or saved and it has no presence in the
 * data source.
 * User: gerwood
 * Date: 11/04/2009
 * Time: 2:21:16 PM
 */
public class ProjectNotFoundException extends Exception {
    public ProjectNotFoundException (String message) {
        super(message);
    }
}
