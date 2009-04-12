<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: gerwood
  Date: 09/04/2009
  Time: 5:44:39 PM
  To change this template use File | Settings | File Templates.
--%>
<div id="main-toolbar">
    <a href="<c:url value="/projects/"/>" class="projects-button">Projects</a>
    <c:if test="${not empty project && not empty project.projectId}">
        <a href="<c:url value="/projects/project/${project.projectId}/backlog"/>" class="backlog-button" >${project.title} Backlog</a>
    </c:if>
</div>