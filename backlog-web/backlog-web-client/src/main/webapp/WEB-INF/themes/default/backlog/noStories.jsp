<%--
  The no stories view provides an 'error' state when the backlog is empty...
  User: gerwood
  Date: 11/04/2009
  Time: 5:57:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../layout/header.jspf" %>
<%@ include file="projectToolbar.jsp" %>

<div id="backlog">
    <c:forEach var="story" items="${backlog.stories}">
        <div id="project-story-${story.storyId}" class="story-listing">
            <div class="title"><a href="<c:url value="/projects/project/${currentProject.projectId}/backlog/${story.storyId}" />" >#${story.storyId}: ${story.title} </div>
            <div class="story">${story.story}</div>
            <div class="toolbar">
                <a href="<c:url value="/projects/project/${currentProject.projectId}/backlog/${story.storyId}/edit" />" class="edit-button" >Edit</a>
            </div>
        </div>
    </c:forEach>
</div>
<%@ include file="../layout/footer.jspf" %>
