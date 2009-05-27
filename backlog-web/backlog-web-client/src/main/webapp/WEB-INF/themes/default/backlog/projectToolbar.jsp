<%--
  Created by IntelliJ IDEA.
  User: gerwood
  Date: 12/04/2009
  Time: 8:51:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${not empty project && not empty project.projectId}">
    <div id="story-toolbar">
        <div class="title">Project</div>
        <a href="<c:url value="/projects/project/${project.projectId}/backlog"/>" class="backlog-button" >Backlog</a>
        <a href="<c:url value="/projects/project/${project.projectId}/story/new" />" class="create-button">Create Story</a>
        <a href="<c:url value="/projects/project/${project.projectId}/iterations" />" class="iteration-button">Iterations</a>
        <a href="<c:url value="/projects/project/${project.projectId}/edit" />" class="edit-button">Edit Project</a>
    </div>
</c:if>
