package com.sitewidesystems.backlog.exceptions;

/**
 * BacklogNotFoundException is thrown when the Backlog specified is not found. This should
 * only be thrown if the data source can be contacted. Otherwise see DataAccessException
 * User: gerwood
 * Date: 07/04/2009
 * Time: 7:01:16 AM
 */
public class BacklogNotFoundException extends DataNotFoundException {
    public BacklogNotFoundException (String message) {
        super(message);
    }
}
