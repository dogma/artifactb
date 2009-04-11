package com.sitewidesystems.backlog.exceptions;

/**
 * Exception thrown when a save is requested for a new Group (that doesn't yet exist) and
 * the id provide has already been taken.
 * User: gerwood
 * Date: 11/04/2009
 * Time: 8:50:08 PM
 */
public class GroupAlreadyExistsException extends Exception {
    public GroupAlreadyExistsException (String message) {
        super(message);
    }
}
