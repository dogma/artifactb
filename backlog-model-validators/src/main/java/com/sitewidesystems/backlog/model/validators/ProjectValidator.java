package com.sitewidesystems.backlog.model.validators;

import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import com.sitewidesystems.backlog.model.Project;

/**
 * Created by IntelliJ IDEA.
 * Creator: gerwood
 * Created: 12/04/2009 9:37:29 PM
 */
public class ProjectValidator implements Validator {
    @Override
    public boolean supports(Class aClass) {
        return Project.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        System.out.println("Running validator.");
        Project p = (Project) o;
        if(p.getProjectId().matches("\\W")) {
            System.out.println("Found a non-alphanumeric character. Ain't I special");
            errors.rejectValue("projectId","project.id.illegalCharacters");
        }
    }
}
