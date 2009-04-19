<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="backlog">
    <div class="backlog-title">Story Backlog for ${project.title}</div>
    <c:forEach var="story" items="${backlog.stories}">
        <div id="project-story-${story.storyId}" class="story-listing">
            <div class="title"><a href="<c:url value="/projects/project/${project.projectId}/story/${story.storyId}/details" />" >#${story.storyId}: ${story.title}</a></div>
            <div class="story">${story.story}</div>
            <div class="toolbar">
                <a href="<c:url value="/projects/project/${project.projectId}/story/${story.storyId}/edit" />" class="edit-button" >Edit Story</a>
                <a href="<c:url value="/projects/project/${project.projectId}/story/${story.storyId}/details" />" class="details-button" >Details</a>
            </div>
        </div>
    </c:forEach>
</div>
