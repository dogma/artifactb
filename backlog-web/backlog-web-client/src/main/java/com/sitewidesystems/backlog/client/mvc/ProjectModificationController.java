package com.sitewidesystems.backlog.client.mvc;

import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import com.sitewidesystems.backlog.repository.ProjectRepository;
import com.sitewidesystems.backlog.model.Project;
import com.sitewidesystems.backlog.exceptions.DataAccessException;
import com.sitewidesystems.backlog.exceptions.ProjectNotFoundException;

/**
 * Created by IntelliJ IDEA.
 * Creator: gerwood
 * Created: 12/04/2009 9:20:13 PM
 */
public class ProjectModificationController extends SimpleFormController {

    private ProjectRepository projectRepository;

    public ModelAndView onSubmit (Object command, BindException error) {
        ModelAndView mav = new ModelAndView(getSuccessView());

        try {
            projectRepository.setProject((Project) command);
        } catch (DataAccessException e) {
            error.addError(new ObjectError("data-access","data-access"));
        } catch (ProjectNotFoundException e) {
            error.addError(new ObjectError("project-not-found","project-not-found"));
        }
        
        return mav;
    }

    public ProjectRepository getProjectRepository() {
        return projectRepository;
    }

    @Required
    public void setProjectRepository(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }
}
