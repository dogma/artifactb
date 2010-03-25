package com.sitewidesystems.backlog.client.mvc.project;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import com.sitewidesystems.backlog.repository.ProjectRepository;
import com.sitewidesystems.backlog.model.Project;
import com.sitewidesystems.backlog.exceptions.DataAccessException;
import com.sitewidesystems.backlog.exceptions.ProjectNotFoundException;
import com.sitewidesystems.backlog.client.util.PathManipulator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * Creator: gerwood
 * Created: 12/04/2009 9:20:13 PM
 */
public class ProjectModificationController extends SimpleFormController {

    private String newView;
    private ProjectRepository projectRepository;
    private PathManipulator pathManipulator;
    private String pathOffset;

    private Log logger = LogFactory.getLog(getClass());

    protected ModelAndView showForm (HttpServletRequest request, HttpServletResponse response, BindException errors) throws Exception {

        ModelAndView mav = new ModelAndView(getFormView());
        mav.addObject("area","projects");
        HashMap<String, String> pathRequest = pathManipulator.keyValues(request);

        Project p = (Project) formBackingObject(request);
        mav.addObject("project",p);
        
        if(pathRequest.containsKey("new")) {
            mav.setViewName(newView);
            return mav;
        } else if (pathRequest.containsKey("edit")) {
            //Can not change the id of an existing project.
            p.setProjectId(pathRequest.get("project"));
            mav.setViewName(getFormView());
        }
        
        return showForm(request, response, errors, mav.getModel());
    }
    
    protected ModelAndView onSubmit (HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws ServletException, Exception {
        ModelAndView mav = new ModelAndView(getFormView());
        logger.debug("Running on submit");
        HashMap<String, String> pathRequest = pathManipulator.keyValues(request);

        Project p = (Project) command;

        try {
            if(pathRequest.containsKey("new")) {
                projectRepository.addProject(p);
            } else if (pathRequest.containsKey("edit")) {
                //Can not change the id of an existing project.
                p.setProjectId(pathRequest.get("project"));
                projectRepository.setProject(p);
            }
            
            mav.addObject("project",p);
            mav.setViewName(getSuccessView());
        } catch (DataAccessException e) {
            errors.addError(new ObjectError("data-access", "data-access"));
        } catch (ProjectNotFoundException e) {
            errors.addError(new ObjectError("project-not-found", "project-not-found"));
        }

        return mav;
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        logger.debug("formBackingObject");
        HashMap<String, String> pathRequest = pathManipulator.keyValues(request);
        if (pathRequest.containsKey("new")) {
            Project p = new Project();
            return p;

        } else if(pathRequest.containsKey("edit") && pathRequest.containsKey("project")) {
            try {
                return projectRepository.getProject(pathRequest.get("project"));
            } catch (DataAccessException e) {
                throw e;
            } catch (ProjectNotFoundException e) {
                throw e;
            }
        }

        throw new Exception();
    }

    public ProjectRepository getProjectRepository() {
        return projectRepository;
    }

    @Required
    public void setProjectRepository(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public PathManipulator getPathManipulator() {
        return pathManipulator;
    }

    @Required
    public void setPathManipulator(PathManipulator pathManipulator) {
        this.pathManipulator = pathManipulator;
    }

    public String getPathOffset() {
        return pathOffset;
    }

    public void setPathOffset(String pathOffset) {
        this.pathOffset = pathOffset;
    }

    public String getNewView() {
        return newView;
    }

    public void setNewView(String newView) {
        this.newView = newView;
    }
}
