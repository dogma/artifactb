package com.sitewidesystems.backlog.client.util;

/**
 * The Area determiner is a simplified version of the PathManipulator. It returns
 * the first part of the path after the context. This (in the current project) is
 * used to determine what the main 'top-level' area you are in and as a result adjust the
 * menus being presented.
 * Creator: gerwood
 * Created: 15/04/2009 10:18:08 PM
 */
public class AreaDeterminer {
    private Integer defaultOffset = 0;
    private Boolean ignoreLeadingSeparator = true;

    /**
     * Return the 'area' you are in.
     * @param request The request object provided by the application server
     * @param offset The offset does it need to 'discount' a certain depth into the string.
     * @return Returns a single string which is 1 part of the path (a path of /area/thing/etc) would return 'area'.
     */
    public String getArea (String request, Integer offset) {
        System.out.println("Path Info: "+request);

        String path[] = request.split("/");

        if(path.length == 0) {
            return "";
        }
        if(getDefaultOffset()+offset < 0) {
            offset = 0-getDefaultOffset();
        }

        if(getIgnoreLeadingSeparator() && path[0].equals("")) {
            offset++;
        }

        for(Integer i = getDefaultOffset() +offset; i < path.length; i++) {
            if(path[i].equals("")) { continue; }
            return path[i];
        }

        return "";
    }

//    public boolean checkRequest (HashMap<String,String> keyValues, )

    public Integer getDefaultOffset() {
        return defaultOffset;
    }

    /**
     * Set a default offset for the path. An offset of 1 for '/context/name/area/value1' would return
     * 'area' where 'context' is the name of the context the app runs in and 'name' is skipped because
     * of the offset. The next resulting value is 'area'.
     * @param defaultOffset The number of places to skip.
     */
    public void setDefaultOffset(Integer defaultOffset) {
        this.defaultOffset = defaultOffset;
    }

    public Boolean getIgnoreLeadingSeparator() {
        return ignoreLeadingSeparator;
    }

    /**
     * This setting is redundant as the / are ignored until a valid one is found unless none are available
     * or no valid match is found, in which case both will return an empty string.
     * @param ignoreLeadingSeparator
     */
    public void setIgnoreLeadingSeparator(Boolean ignoreLeadingSeparator) {
        this.ignoreLeadingSeparator = ignoreLeadingSeparator;
    }
}
