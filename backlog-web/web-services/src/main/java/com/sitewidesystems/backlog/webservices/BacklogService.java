package com.sitewidesystems.backlog.webservices;

import com.sitewidesystems.backlog.repository.BacklogRepository;
import com.sitewidesystems.backlog.repository.StoryRepository;
import com.sitewidesystems.backlog.exceptions.DataAccessException;
import com.sitewidesystems.backlog.exceptions.BacklogNotFoundException;
import com.sitewidesystems.backlog.model.Backlog;

import javax.ws.rs.*;

/**
 * The BacklogService provides rest services for the Backlog (found in the model package).
 * It provides fetch, update, delete and create services.
 * User: gerwood
 * Date: 07/04/2009
 * Time: 6:19:57 AM
 */
@Path("/services/backlog/")
@Produces("application/xml")
public class BacklogService {

    private BacklogRepository backlogRepository;
    private StoryRepository storyRepository;

    @GET
    @Path("/stories-list/{project}")
    public Backlog getStories (@PathParam("project") String project) throws DataAccessException, BacklogNotFoundException {
        return backlogRepository.getBacklog(project);
    }

    @PUT
    @Path("/stories/{project}/move/{story}/{position}")
    public void moveStory (@PathParam("project") String project, @PathParam("story") Integer story, @PathParam("position") Integer position) throws DataAccessException, BacklogNotFoundException {
        Backlog b = backlogRepository.getBacklog(project);
        b.move(story,position);
        backlogRepository.setBacklog(b);
    }

    public BacklogRepository getBacklogRepository() {
        return backlogRepository;
    }

    public void setBacklogRepository(BacklogRepository backlogRepository) {
        this.backlogRepository = backlogRepository;
    }

    public StoryRepository getStoryRepository() {
        return storyRepository;
    }

    public void setStoryRepository(StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
    }
}
