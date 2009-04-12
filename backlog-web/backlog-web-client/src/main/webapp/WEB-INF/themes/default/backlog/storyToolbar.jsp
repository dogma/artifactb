<%--
  Created by IntelliJ IDEA.
  User: gerwood
  Date: 12/04/2009
  Time: 8:51:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="story-toolbar">
    <c:if test="${not empty project && not empty project.projectId}">
        <div>
        <span>Current Project:</span>
        <span class="project-title">${project.title}</span>
        </div>
    </c:if>
    <a href="<c:url value="/projects/${project.projectId}/backlog/new" />" class="create-button">Create Story</a>
</div>
