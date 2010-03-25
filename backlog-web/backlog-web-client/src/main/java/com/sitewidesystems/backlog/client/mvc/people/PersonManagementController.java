package com.sitewidesystems.backlog.client.mvc.people;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Required;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sitewidesystems.backlog.repository.PersonRepository;
import com.sitewidesystems.backlog.client.util.PathManipulator;
import com.sitewidesystems.backlog.model.org.Person;
import com.sitewidesystems.backlog.exceptions.DataAccessException;
import com.sitewidesystems.backlog.exceptions.PersonAlreadyExistsException;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: gerwood
 * Date: 26/08/2009
 * Time: 9:42:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class PersonManagementController implements Controller {

    private String defaultView;
    private String newView;
    private String editView;

    private PersonRepository personRepository;
    private PathManipulator pathManipulator;

    private Log logger = LogFactory.getLog(getClass());

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView(getDefaultView());

        mav.addObject("area", "people");
        HashMap<String, String> pathRequest = pathManipulator.keyValues(request);

        if (pathRequest.get("person").equals("search")) {
            mav.addObject("people", personRepository.getPeople(request.getParameter("username")));
        } else if (pathRequest.containsKey("edit")) {
            mav.setViewName(getEditView());
            Person p = personRepository.getPerson(pathRequest.get("person"));
            System.out.println("Loaded person "+pathRequest.get("person"));
            System.out.println("Person:" +p.getUsername());
            mav.addObject("person", p);
        } else if (pathRequest.get("person").equals("new")) {
            mav.setViewName(newView);
            if (request.getParameterMap().containsKey("username")) {
                System.out.println("Adding person");
                Person p = new Person();
                p.setUsername(request.getParameter("username"));
                p.setName(request.getParameter("name"));
                p.setPassword(request.getParameter("password"));

                try {
                    personRepository.addPerson(p);
                    mav.addObject("status", true);
                    mav.addObject("message", "User " + p.getUsername() + " created");
                } catch (PersonAlreadyExistsException e) {
                    logger.info("User already exists", e);
                    mav.addObject("message", "User (" + p.getUsername() + ") already exists");
                    mav.addObject("status", false);
                } catch (DataAccessException e) {
                    logger.error("Data connection failed", e);
                    mav.addObject("message", "Failed to add user because of a data error. Check datasource is available.");
                    mav.addObject("status", false);
                }

            } else {
                System.out.println("Not adding person");
            }
        }

        return mav;
    }

    public PersonRepository getPersonRepository() {
        return personRepository;
    }

    @Required
    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public PathManipulator getPathManipulator() {
        return pathManipulator;
    }

    @Required
    public void setPathManipulator(PathManipulator pathManipulator) {
        this.pathManipulator = pathManipulator;
    }

    public String getDefaultView() {
        return defaultView;
    }

    @Required
    public void setDefaultView(String defaultView) {
        this.defaultView = defaultView;
    }

    public String getNewView() {
        return newView;
    }

    @Required
    public void setNewView(String newView) {
        this.newView = newView;
    }

    public String getEditView() {
        return editView;
    }

    public void setEditView(String editView) {
        this.editView = editView;
    }
}
