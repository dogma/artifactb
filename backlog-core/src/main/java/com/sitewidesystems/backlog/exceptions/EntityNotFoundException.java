package com.sitewidesystems.backlog.exceptions;

/**
 * This is unlikely to be thrown. More likely either a PersonNotFoundException or GroupNotFoundException
 * will be thrown instead as an Entity is a starting point only
 * User: gerwood
 * Date: 11/04/2009
 * Time: 8:21:42 PM
 */
public class EntityNotFoundException extends Exception {
    public EntityNotFoundException (String message) {
        super(message);
    }
}
