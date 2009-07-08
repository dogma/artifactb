package com.sitewidesystems.backlog.client.mvc.project;

import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Required;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sitewidesystems.backlog.repository.IterationRepository;
import com.sitewidesystems.backlog.repository.ProjectRepository;
import com.sitewidesystems.backlog.client.util.PathManipulator;
import com.sitewidesystems.backlog.exceptions.DataAccessException;
import com.sitewidesystems.backlog.exceptions.IterationNotFoundException;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * Creator: gerwood
 * Created: 19/04/2009 1:56:53 PM
 */
public class IterationController implements Controller {

    private String defaultView;

    private IterationRepository iterationRepository;
    private ProjectRepository projectRepository;
    private PathManipulator pathManipulator;

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView(getDefaultView());

        mav.addObject("area","iterations");

        HashMap<String,String> pathRequest = pathManipulator.keyValues(request);

        if(pathRequest.containsKey("project")) {
            mav.addObject("project",projectRepository.getProject(pathRequest.get("project")));
            try {
                mav.addObject("currentIteration",iterationRepository.getCurrentIteration(pathRequest.get("project")));
            } catch (DataAccessException e) {
                mav.addObject("currentIteration",null);
            } catch (IterationNotFoundException e) {
                mav.addObject("currentIteration",null);
            }
            mav.addObject("iterations",iterationRepository.getProjectIterations(pathRequest.get("project")));
        } else {
            return mav;
        }

        if(pathRequest.containsKey("iteration")) {
        } else {
            
        }
        return mav;
    }

    public String getDefaultView() {
        return defaultView;
    }

    public void setDefaultView(String defaultView) {
        this.defaultView = defaultView;
    }

    public IterationRepository getIterationRepository() {
        return iterationRepository;
    }

    @Required
    public void setIterationRepository(IterationRepository iterationRepository) {
        this.iterationRepository = iterationRepository;
    }

    public PathManipulator getPathManipulator() {
        return pathManipulator;
    }

    @Required
    public void setPathManipulator(PathManipulator pathManipulator) {
        this.pathManipulator = pathManipulator;
    }

    public ProjectRepository getProjectRepository() {
        return projectRepository;
    }

    @Required
    public void setProjectRepository(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }
}
