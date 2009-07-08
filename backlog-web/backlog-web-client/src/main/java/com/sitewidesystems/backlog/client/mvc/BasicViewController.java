package com.sitewidesystems.backlog.client.mvc;

import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * Creator: gerwood
 * Created: 15/04/2009 9:58:34 PM
 */
public class BasicViewController implements Controller {

    private String defaultView;
    private String area;

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView(getDefaultView());

        if(area != null) {
            mav.addObject("area",area);
        }

        return mav;
    }

    public String getDefaultView() {
        return defaultView;
    }

    public void setDefaultView(String defaultView) {
        this.defaultView = defaultView;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
