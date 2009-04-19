package com.sitewidesystems.backlog.exceptions;

/**
 * Generally should be thrown by a StoryRepository when it doesn't contain the requested story
 * User: gerwood
 * Date: 07/04/2009
 * Time: 7:41:51 AM
 */
public class StoryNotFoundException extends DataNotFoundException {
    public StoryNotFoundException (String message) {
        super(message);
    }
}
