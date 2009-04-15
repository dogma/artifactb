package com.sitewidesystems.backlog.client.mvc.people;

import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Required;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sitewidesystems.backlog.repository.GroupRepository;
import com.sitewidesystems.backlog.client.util.PathManipulator;
import com.sitewidesystems.backlog.model.org.Group;

import java.util.HashMap;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Creator: gerwood
 * Created: 15/04/2009 9:15:50 PM
 */
public class GroupManagementController implements Controller {

    private String listView;
    private String detailsView;

    private GroupRepository groupRepository;
    private PathManipulator pathManipulator;
    
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView(getListView());

        HashMap<String,String> pathRequest = pathManipulator.keyValues(request);
        System.out.println("Keys:"+pathRequest.size());
        if(pathRequest.containsKey("groups") && pathRequest.get("groups").equals("list")) {
            System.out.println("Fulfilling list groups request...");
            List<Group> groups = groupRepository.getAllGroups();
            mav.addObject("groups",groups);
        } else if (pathRequest.containsKey("group")) {
            Group g = groupRepository.getGroup(pathRequest.get("group"));
            mav.setViewName(getDetailsView());
            mav.addObject("group",g);
        }

        return mav;
    }

    public String getListView() {
        return listView;
    }

    @Required
    public void setListView(String listView) {
        this.listView = listView;
    }

    public GroupRepository getGroupRepository() {
        return groupRepository;
    }

    @Required
    public void setGroupRepository(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public PathManipulator getPathManipulator() {
        return pathManipulator;
    }

    public void setPathManipulator(PathManipulator pathManipulator) {
        this.pathManipulator = pathManipulator;
    }

    public String getDetailsView() {
        return detailsView;
    }

    public void setDetailsView(String detailsView) {
        this.detailsView = detailsView;
    }
}
