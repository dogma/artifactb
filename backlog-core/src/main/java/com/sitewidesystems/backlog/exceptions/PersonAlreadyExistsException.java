package com.sitewidesystems.backlog.exceptions;

/**
 * Created by IntelliJ IDEA.
 * User: gerwood
 * Date: 30/08/2009
 * Time: 3:49:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class PersonAlreadyExistsException extends Exception {
    public PersonAlreadyExistsException (String message) {
        super(message);
    }
}
