<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Lists all projects available from the project repository.
  User: gerwood
  Date: 11/04/2009
  Time: 5:57:49 PM
--%>
<%@ include file="../layout/header.jspf" %>
<div id="project-list">
    <div class="projects-title">Projects</div>
    <c:forEach var="currentProject" items="${projectList}">
        <c:if test="${currentProject.state != 'abandoned'}">
            <div id="project-${currentProject.projectId}" class="project-listing">
                <div class="title"><a
                        href="<c:url value="/projects/project/${currentProject.projectId}/backlog" />">${currentProject.title}
                    <span class="id">(${currentProject.projectId})</span></a></div>
                <div class="description">${currentProject.description}</div>
                <div class="toolbar">
                    <a href="<c:url value="/projects/project/${currentProject.projectId}/edit" />" class="edit-button">Edit
                        Project</a>
                    <a href="<c:url value="/projects/project/${currentProject.projectId}/backlog"/>"
                       class="backlog-button">Backlog</a>
                    <a href="<c:url value="/projects/project/${currentProject.projectId}/iterations"/>"
                       class="iteration-button">Iterations</a>
                    <a href="<c:url value="/projects/project/${currentProject.projectId}/story/new" />"
                       class="create-button">Create Story</a>
                </div>
            </div>
        </c:if>
    </c:forEach>
</div>
<%@ include file="../layout/footer.jspf" %>
