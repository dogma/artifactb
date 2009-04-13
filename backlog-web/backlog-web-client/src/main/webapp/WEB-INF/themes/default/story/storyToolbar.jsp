<%--
  Created by IntelliJ IDEA.
  User: gerwood
  Date: 12/04/2009
  Time: 8:51:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${not empty story}">
    <div id="story-toolbar">
        <div class="title">Story</div>
        <a href="<c:url value="/projects/project/${project.projectId}/story/${story.storyId}/details"/>" class="details-button" >Details</a>
        <a href="<c:url value="/projects/project/${project.projectId}/backlog"/>" class="add-category-button" >Add Category</a>
        <a href="<c:url value="/projects/project/${project.projectId}/story/new" />" class="add-note-button">Add Note</a>
        <a href="<c:url value="/projects/project/${project.projectId}/story/${story.storyId}/edit" />" class="edit-button">Edit Story</a>
    </div>
</c:if>
