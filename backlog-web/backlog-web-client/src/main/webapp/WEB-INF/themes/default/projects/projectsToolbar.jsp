<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="project-toolbar">
    <c:if test="${not empty project && not empty project.projectId}">
        <div>
        Current Project: ${project.title}
        </div>
    </c:if>
    <a href="<c:url value="/projects/new" />" class="create-button">Create Project</a>
</div>
