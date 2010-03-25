package com.sitewidesystems.backlog.client.mvc.people;

import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Required;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sitewidesystems.backlog.repository.GroupRepository;
import com.sitewidesystems.backlog.repository.PersonRepository;
import com.sitewidesystems.backlog.client.util.PathManipulator;
import com.sitewidesystems.backlog.model.org.Group;
import com.sitewidesystems.backlog.model.org.Membership;
import com.sitewidesystems.backlog.model.org.Person;
import com.sitewidesystems.backlog.exceptions.PersonNotFoundException;

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
    private String membersView;

    private GroupRepository groupRepository;
    private PersonRepository personRepository;
    private PathManipulator pathManipulator;
    
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView(getListView());

        mav.addObject("area","groups");
        
        HashMap<String,String> pathRequest = pathManipulator.keyValues(request);

        if(pathRequest.containsKey("groups") && pathRequest.get("groups").equals("list")) {
            List<Group> groups = groupRepository.getAllGroups();
            mav.addObject("groups",groups);
        } else if (pathRequest.containsKey("group") && pathRequest.containsKey("members")) {
            Group g = groupRepository.getGroup(pathRequest.get("group"));
            if(request.getParameter("add") != null) {
                try {
                    Person p = personRepository.getPerson(request.getParameter("username"));
                    if(p != null) {
                        groupRepository.addGroupMember(g,p);
                    } else {
                        mav.addObject("error","Requested person was not found");
                    }
                } catch (PersonNotFoundException e) {
                    mav.addObject("error","Requested person was not found");
                }
            }
            mav.setViewName(getMembersView());
            mav.addObject("group",g);
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

    @Required
    public void setPathManipulator(PathManipulator pathManipulator) {
        this.pathManipulator = pathManipulator;
    }

    public String getDetailsView() {
        return detailsView;
    }

    @Required
    public void setDetailsView(String detailsView) {
        this.detailsView = detailsView;
    }

    public String getMembersView() {
        return membersView;
    }

    @Required
    public void setMembersView(String membersView) {
        this.membersView = membersView;
    }

    public PersonRepository getPersonRepository() {
        return personRepository;
    }

    @Required
    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
}
