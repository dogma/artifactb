package com.sitewidesystems.backlog.client.mvc.project;

import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Required;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sitewidesystems.backlog.repository.BacklogRepository;
import com.sitewidesystems.backlog.repository.ProjectRepository;
import com.sitewidesystems.backlog.model.Project;
import com.sitewidesystems.backlog.model.Backlog;
import com.sitewidesystems.backlog.client.util.PathManipulator;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: gerwood
 * Date: 11/04/2009
 * Time: 5:49:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class BacklogManagementController implements Controller {

    private String listView;
    private String errorView;
    private BacklogRepository backlogRepository;
    private ProjectRepository projectRepository;
    private PathManipulator pathManipulator;

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView(getListView());

        HashMap<String,String> directives = getPathManipulator().keyValues(request);

        if(directives.containsKey("project")) {
            Project p = projectRepository.getProject(directives.get("project"));
            mav.addObject("project",p);

            Backlog b = backlogRepository.getBacklog(p);
            System.out.println("Backlog: "+b+" size: "+b.getStories().size());
            mav.addObject("backlog",b);
            return mav;
        }

        mav.addObject("error","Project id not available");
        mav.addObject("errorInstructions","Please ensure you have specified a project id in the path (project/{projectId}/backlog).");
        mav.setViewName(errorView);
        return mav;
    }

    public String getListView() {
        return listView;
    }

    @Required
    public void setListView(String listView) {
        this.listView = listView;
    }

    public BacklogRepository getBacklogRepository() {
        return backlogRepository;
    }

    @Required
    public void setBacklogRepository(BacklogRepository backlogRepository) {
        this.backlogRepository = backlogRepository;
    }

    public String getErrorView() {
        return errorView;
    }

    public void setErrorView(String errorView) {
        this.errorView = errorView;
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
}
