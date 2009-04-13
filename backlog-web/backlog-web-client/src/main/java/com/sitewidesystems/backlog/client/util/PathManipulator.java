package com.sitewidesystems.backlog.client.util;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * Creator: gerwood
 * Created: 12/04/2009 9:43:29 PM
 */
public class PathManipulator {

    private Integer defaultOffset = 0;
    private Boolean ignoreLeadingSeparator = true;

    public HashMap<String,String> breakPath (String request, Integer offset) {
        HashMap<String,String> keyValues = new HashMap<String, String>();

        System.out.println("Path Info: "+request);

        if(request == null) {
            return new HashMap<String,String>();
        }
        String path[] = request.split("/");

        if(path.length == 0) {
            return new HashMap<String,String>();
        }
        if(getDefaultOffset()+offset < 0) {
            offset = 0-getDefaultOffset();
        }

        if(getIgnoreLeadingSeparator() && path[0].equals("")) {
            offset++;
        }

        for(Integer i = getDefaultOffset() +offset; i < path.length; i++) {
            if(path[i].equals("")) { continue; }
            String key = path[i++];
            String value = null;
            for(int k = 0;i < path.length; i++) {
                if(path[i].equals("")) {
                    continue;
                }
                if(path.length > i) {
                    value = path[i];
                }
                break;
            }
            keyValues.put(key,value);
        }

        return keyValues;
    }

    public HashMap<String,String> keyValues (HttpServletRequest request) {
        return breakPath(request.getPathInfo(),0);
    }

    public HashMap<String,String> keyValues (HttpServletRequest request, Integer offset) {
        return breakPath(request.getPathInfo(),offset);
    }

    public Integer getDefaultOffset() {
        return defaultOffset;
    }

    public void setDefaultOffset(Integer defaultOffset) {
        this.defaultOffset = defaultOffset;
    }

    public Boolean getIgnoreLeadingSeparator() {
        return ignoreLeadingSeparator;
    }

    public void setIgnoreLeadingSeparator(Boolean ignoreLeadingSeparator) {
        this.ignoreLeadingSeparator = ignoreLeadingSeparator;
    }
}
