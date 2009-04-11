package com.sitewidesystems.backlog.exceptions;

/**
 * To be thrown when a Person object is not found via it's unique id. 
 * User: gerwood
 * Date: 11/04/2009
 * Time: 8:21:11 PM
 */
public class PersonNotFoundException extends EntityNotFoundException {
    public PersonNotFoundException (String message) {
        super(message);
    }
}
