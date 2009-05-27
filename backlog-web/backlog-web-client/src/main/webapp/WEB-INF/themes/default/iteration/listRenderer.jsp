<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="backlog">
    <div class="backlog-title">Iterations for ${project.title}</div>
    <c:forEach var="iteration" items="${iterations}">
        <div id="iteration-story-${iteration.iterationId}" class="iteration-listing ${iteration.state}">
            <div class="title"><a href="<c:url value="/projects/project/${project.projectId}/iteration/${iteration.iterationId}/details" />" >#${iteration.iterationId}: ${iteration.title}</a></div>
            <div class="story">${iteration.description}</div>
            <div class="toolbar">
                <a href="<c:url value="/projects/project/${project.projectId}/iteration/${iteration.iterationId}/edit" />" class="edit-button" >Edit Iteration</a>
                <a href="<c:url value="/projects/project/${project.projectId}/iteration/${iteration.iterationId}/details" />" class="details-button" >Details</a>
            </div>
        </div>
    </c:forEach>
</div>
