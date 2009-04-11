package com.sitewidesystems.backlog.client.mvc;

import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sitewidesystems.backlog.repository.BacklogRepository;

/**
 * Created by IntelliJ IDEA.
 * User: gerwood
 * Date: 11/04/2009
 * Time: 5:49:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class BacklogManagementController implements Controller {

    private String listView;
    private BacklogRepository backlogRepository;

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView(getListView());

        return mav;
    }

    public String getListView() {
        return listView;
    }

    public void setListView(String listView) {
        this.listView = listView;
    }

    public BacklogRepository getBacklogRepository() {
        return backlogRepository;
    }

    public void setBacklogRepository(BacklogRepository backlogRepository) {
        this.backlogRepository = backlogRepository;
    }
}
