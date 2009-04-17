package com.sitewidesystems.backlog.client.mvc.project;

import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Required;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sitewidesystems.backlog.repository.ProjectRepository;
import com.sitewidesystems.backlog.repository.StoryRepository;
import com.sitewidesystems.backlog.client.util.PathManipulator;
import com.sitewidesystems.backlog.model.Project;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * Creator: gerwood
 * Created: 13/04/2009 2:55:15 PM
 */
public class StoryDetailsController implements Controller {

    private ProjectRepository projectRepository;
    private StoryRepository storyRepository;
    private PathManipulator pathManipulator;

    private String defaultView;
    private String errorView;

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav  = new ModelAndView(getDefaultView());
        HashMap<String,String> pathRequest = pathManipulator.keyValues(request);

        if(pathRequest.containsKey("project")) {
            Project p = projectRepository.getProject(pathRequest.get("project"));
            mav.addObject("project",p);
        }

        if(pathRequest.containsKey("story") && StringUtils.isNumeric(pathRequest.get("story"))) {
            mav.addObject("story",storyRepository.getStory(Integer.valueOf(pathRequest.get("story"))));
            return mav;
        }

        mav.setViewName(getErrorView());
        return mav;
    }

    public ProjectRepository getProjectRepository() {
        return projectRepository;
    }

    @Required
    public void setProjectRepository(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public StoryRepository getStoryRepository() {
        return storyRepository;
    }

    @Required
    public void setStoryRepository(StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
    }

    public String getDefaultView() {
        return defaultView;
    }

    @Required
    public void setDefaultView(String defaultView) {
        this.defaultView = defaultView;
    }

    public PathManipulator getPathManipulator() {
        return pathManipulator;
    }

    @Required
    public void setPathManipulator(PathManipulator pathManipulator) {
        this.pathManipulator = pathManipulator;
    }

    public String getErrorView() {
        return errorView;
    }

    public void setErrorView(String errorView) {
        this.errorView = errorView;
    }
}
