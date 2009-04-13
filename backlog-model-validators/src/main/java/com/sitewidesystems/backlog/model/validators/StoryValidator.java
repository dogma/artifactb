package com.sitewidesystems.backlog.model.validators;

import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import com.sitewidesystems.backlog.model.Story;

/**
 * Created by IntelliJ IDEA.
 * Creator: gerwood
 * Created: 13/04/2009 12:30:34 PM
 */
public class StoryValidator implements Validator {
    @Override
    public boolean supports(Class aClass) {
        return Story.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
