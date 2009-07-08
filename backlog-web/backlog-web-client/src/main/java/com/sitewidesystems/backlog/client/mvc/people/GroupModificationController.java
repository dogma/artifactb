package com.sitewidesystems.backlog.client.mvc.people;

import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.beans.factory.annotation.Required;
import com.sitewidesystems.backlog.client.util.PathManipulator;
import com.sitewidesystems.backlog.model.org.Group;
import com.sitewidesystems.backlog.exceptions.DataAccessException;
import com.sitewidesystems.backlog.exceptions.GroupNotFoundException;
import com.sitewidesystems.backlog.repository.GroupRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * Creator: gerwood
 * Created: 17/04/2009 6:24:25 AM
 */
public class GroupModificationController extends SimpleFormController {

    private GroupRepository groupRepository;
    private PathManipulator pathManipulator;
    private String newView;
    private String pathOffset;

    protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException errors) throws Exception {

        ModelAndView mav = new ModelAndView(getFormView());
        mav.addObject("area","groups");
        HashMap<String, String> pathRequest = pathManipulator.keyValues(request);

        Group g = (Group) formBackingObject(request);
        mav.addObject("group",g);

        if (pathRequest.containsKey("group") && pathRequest.get("group").equals("new")) {
            mav.setViewName(newView);
            return mav;
        } else if (pathRequest.containsKey("edit")) {
            //Can not change the id of an existing project.
            mav.setViewName(getFormView());
        }

        return showForm(request, response, errors, mav.getModel());
    }

    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws ServletException, Exception {
        ModelAndView mav = new ModelAndView(getFormView());
        HashMap<String, String> pathRequest = pathManipulator.keyValues(request);

        Group g = (Group) command;

        try {
            if (pathRequest.containsKey("group") && pathRequest.get("group").equals("new")) {
                groupRepository.addGroup(g);
            } else if (pathRequest.containsKey("edit")) {
                //Can not change the id of an existing project.
                groupRepository.save(g);
            }

            mav.addObject("group",g);
            mav.setViewName(getSuccessView());
            return mav;
        } catch (DataAccessException e) {
            errors.addError(new ObjectError("data-access", "data-access"));
            throw e;
        } catch (GroupNotFoundException e) {
            errors.addError(new ObjectError("group-not-found", "group-not-found"));
            throw e;
        }
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        HashMap<String, String> pathRequest = pathManipulator.keyValues(request);
        if (pathRequest.containsKey("group")) {
            if (pathRequest.get("group").equals("new")) {
                Group g = new Group();
                return g;
            } else {
                Group g  = groupRepository.getGroup(pathRequest.get("group"));
                return g;
            }

        } else if (pathRequest.containsKey("edit") && pathRequest.containsKey("project")) {
            try {
                return getGroupRepository().getGroup(pathRequest.get("story"));
            } catch (DataAccessException e) {
                throw e;
            } catch (GroupNotFoundException e) {
                throw e;
            }
        }

        throw new Exception("Didn't reach a happy conclusion in determining group");
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

    public GroupRepository getGroupRepository() {
        return groupRepository;
    }

    @Required
    public void setGroupRepository(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }
}
