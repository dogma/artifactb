package com.sitewidesystems.backlog.client.mvc;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * Creator: gerwood
 * Created: 12/04/2009 5:04:19 PM
 */
public class AbstractPathController {

    private Integer pathOffset = 0;

    public HashMap<String,String> breakPath (HttpServletRequest request, Integer offset) {
        HashMap<String,String> keyValues = new HashMap<String, String>();

        String path[] = request.getPathInfo().split("/");

        for(Integer i = offset; i < path.length; i++) {
            String key = path[i++];
            String value = null;
            if(path.length > i) {
                value = path[i];
            }
            keyValues.put(key,value);
        }

        return keyValues;
    }

    public HashMap<String,String> breakPath (HttpServletRequest request) {
        return breakPath(request,getPathOffset());
    }

    public Integer getPathOffset() {
        return pathOffset;
    }

    public void setPathOffset(Integer pathOffset) {
        this.pathOffset = pathOffset;
    }
}
