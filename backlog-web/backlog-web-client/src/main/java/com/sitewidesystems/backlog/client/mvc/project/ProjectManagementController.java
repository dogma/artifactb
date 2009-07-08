package com.sitewidesystems.backlog.client.mvc.project;

import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Required;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

import com.sitewidesystems.backlog.repository.ProjectRepository;
import com.sitewidesystems.backlog.model.Project;
import com.sitewidesystems.backlog.exceptions.DataAccessException;
import com.sitewidesystems.backlog.exceptions.ProjectNotFoundException;
import com.sitewidesystems.backlog.client.util.PathManipulator;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: gerwood
 * Date: 11/04/2009
 * Time: 5:50:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProjectManagementController implements Controller {

    private String listView;
    private String createView;
    private String editView;
    private String errorView;

    private ProjectRepository projectRepository;
    private PathManipulator pathManipulator;

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView(listView);

        mav.addObject("area","projects");

        HashMap<String, String> directives = getPathManipulator().keyValues(request);

        if (directives.containsKey("new")) {
            System.out.println("Running new...");
            if(request.getParameterMap().containsKey("projectid")) {
                Project p = new Project();
                p.setProjectId(request.getParameter("projectid"));
                p.setDescription(request.getParameter("description"));
                p.setTitle(request.getParameter("title"));

                try {
                    projectRepository.setProject(p);
                } catch (DataAccessException e) {
                    mav.setViewName(errorView);
                } catch (ProjectNotFoundException e) {
                    mav.setViewName(errorView);
                }
            } else {
                mav.setViewName(createView);
                mav.addObject("project",new Project());
            }
        } else if (directives.containsKey("load")) {
            Project project = projectRepository.getProject(directives.get("load"));
            mav.addObject("command", "load");
            mav.addObject("project", project);
            Cookie cookie = new Cookie("project", project.getProjectId());
            response.addCookie(cookie);
        } else if (directives.containsKey("edit")) {
            Project project = projectRepository.getProject(directives.get("edit"));
            mav.setViewName(editView);
            mav.addObject("project", project);
            Cookie cookie = new Cookie("project", project.getProjectId());
            response.addCookie(cookie);
            return mav;
        } else if (request.getCookies() != null && request.getCookies().length > 0) {
            Cookie[] cookies = request.getCookies();

            for (Cookie c : cookies) {
                if (c.getName().equals("project")) {
                    Project p = projectRepository.getProject(c.getName());
                    mav.addObject("project", p);
                }
            }

        }

        if (request.getParameterMap().containsKey("limit")) {
            mav.addObject("projectList", projectRepository.getProjects(0, Integer.getInteger(request.getParameter("limit"))));
        } else {
            mav.addObject("projectList", projectRepository.getProjects());
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

    public String getCreateView() {
        return createView;
    }

    @Required
    public void setCreateView(String createView) {
        this.createView = createView;
    }

    public String getEditView() {
        return editView;
    }

    @Required
    public void setEditView(String editView) {
        this.editView = editView;
    }

    public ProjectRepository getProjectRepository() {
        return projectRepository;
    }

    @Required
    public void setProjectRepository(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public String getErrorView() {
        return errorView;
    }

    @Required
    public void setErrorView(String errorView) {
        this.errorView = errorView;
    }

    public PathManipulator getPathManipulator() {
        return pathManipulator;
    }

    @Required
    public void setPathManipulator(PathManipulator pathManipulator) {
        this.pathManipulator = pathManipulator;
    }
}
