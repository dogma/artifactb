package com.sitewidesystems.backlog.exceptions;

/**
 * Created by IntelliJ IDEA.
 * User: gerwood
 * Date: 11/04/2009
 * Time: 8:20:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class GroupNotFoundException extends EntityNotFoundException {
    public GroupNotFoundException (String message) {
        super(message);
    }
}
