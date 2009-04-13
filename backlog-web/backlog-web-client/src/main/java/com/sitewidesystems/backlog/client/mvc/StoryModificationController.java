package com.sitewidesystems.backlog.client.mvc;

import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.apache.commons.lang.StringUtils;
import com.sitewidesystems.backlog.repository.ProjectRepository;
import com.sitewidesystems.backlog.repository.StoryRepository;
import com.sitewidesystems.backlog.model.Project;
import com.sitewidesystems.backlog.model.Story;
import com.sitewidesystems.backlog.exceptions.DataAccessException;
import com.sitewidesystems.backlog.exceptions.ProjectNotFoundException;
import com.sitewidesystems.backlog.exceptions.StoryNotFoundException;
import com.sitewidesystems.backlog.client.util.PathManipulator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * Creator: gerwood
 * Created: 13/04/2009 11:54:41 AM
 */
public class StoryModificationController extends SimpleFormController {
    private String newView;
    private ProjectRepository projectRepository;
    private StoryRepository storyRepository;
    private PathManipulator pathManipulator;
    private String pathOffset;

    protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException errors) throws Exception {

        ModelAndView mav = new ModelAndView(getFormView());
        HashMap<String, String> pathRequest = pathManipulator.keyValues(request);

        Story s = (Story) formBackingObject(request);
        Project p = projectRepository.getProject(pathRequest.get("project"));
        s.setProject(p.getProjectId());
        mav.addObject("project", p);
        System.out.println("The Story is: "+s.getStoryId());
        mav.addObject("story",s);

        if (pathRequest.containsKey("story") && pathRequest.get("story").equals("new")) {
            mav.setViewName(newView);
            return mav;
        } else if (pathRequest.containsKey("edit")) {
            //Can not change the id of an existing project.
            p.setProjectId(pathRequest.get("project"));
            mav.setViewName(getFormView());
        }

        return showForm(request, response, errors, mav.getModel());
    }

    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws ServletException, Exception {
        ModelAndView mav = new ModelAndView(getFormView());
        System.out.println("Running on submit");
        HashMap<String, String> pathRequest = pathManipulator.keyValues(request);

        Story s = (Story) command;

        Project p = projectRepository.getProject(pathRequest.get("project"));
        mav.addObject("project",p);

        try {
            if (pathRequest.containsKey("story") && pathRequest.get("story").equals("new")) {
                storyRepository.addStory(s);
            } else if (pathRequest.containsKey("edit")) {
                //Can not change the id of an existing project.
                System.out.println("Running update..."+s);
                storyRepository.setStory(s);
                System.out.println("Done: "+s);
            }

            mav.addObject("story",s);
            mav.setViewName(getSuccessView());
            return mav;
        } catch (DataAccessException e) {
            errors.addError(new ObjectError("data-access", "data-access"));
            throw e;
        } catch (StoryNotFoundException e) {
            errors.addError(new ObjectError("story-not-found", "story-not-found"));
            throw e;
        }
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        System.out.println("formBackingObject");
        HashMap<String, String> pathRequest = pathManipulator.keyValues(request);
        if (pathRequest.containsKey("story")) {

            if (pathRequest.get("story").equals("new")) {
                Story s = new Story();
                s.setProject(pathRequest.get("project"));
                return s;
            } else if(StringUtils.isNumeric(pathRequest.get("story"))) {
                System.out.println("Getting a story: "+pathRequest.get("story"));
                System.out.println("In numeric form: "+Integer.valueOf(pathRequest.get("story")));
                Story s = storyRepository.getStory(Integer.valueOf(pathRequest.get("story")));
                return s;
            }

        } else if (pathRequest.containsKey("edit") && pathRequest.containsKey("project")) {
            try {
                return getStoryRepository().getStory(Integer.getInteger(pathRequest.get("story")));
            } catch (DataAccessException e) {
                throw e;
            } catch (StoryNotFoundException e) {
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

    public StoryRepository getStoryRepository() {
        return storyRepository;
    }

    @Required
    public void setStoryRepository(StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
    }
}
