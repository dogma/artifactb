<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Lists all projects available from the project repository.
  User: gerwood
  Date: 11/04/2009
  Time: 5:57:49 PM
--%>
<%@ include file="../layout/header.jspf" %>
<%@ include file="projectsToolbar.jsp" %>

<div id="project-list">
    <c:forEach var="currentProject" items="${projectList}">
        <div id="project-${currentProject.projectId}" class="project-listing">
            <div class="title"><a href="<c:url value="/projects/project/${currentProject.projectId}/backlog" />" >${currentProject.title} <span class="id">(${currentProject.projectId})</a></span></div>
            <div class="description">${currentProject.description}</div>
            <div class="toolbar">
                <a href="<c:url value="/projects/edit/${currentProject.projectId}" />" class="edit-button" >Edit</a>
            </div>
        </div>
    </c:forEach>
</div>
<%@ include file="../layout/footer.jspf" %>
