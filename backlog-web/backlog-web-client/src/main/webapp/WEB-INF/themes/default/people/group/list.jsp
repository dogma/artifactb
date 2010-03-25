<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Lists all projects available from the project repository.
  User: gerwood
  Date: 11/04/2009
  Time: 5:57:49 PM
--%>
<%@ include file="../../layout/header.jspf" %>
<div id="project-list">
    <div class="projects-title">Groups</div>
    <c:forEach var="group" items="${groups}">
        <div id="project-${group.groupId}" class="project-listing">
            <div class="title"><a href="<c:url value="/people/group/${group.groupId}/details" />" >${group.name} <span class="id">(${group.groupId})</span></a></div>
            <div class="description">${group.description}</div>
            <div class="toolbar">
                <a href="<c:url value="/people/group/${group.groupId}/edit" />" class="edit-button" >Edit Group</a>
                <a href="<c:url value="/people/group/${group.groupId}/projects"/>" class="backlog-button" >Manage Projects</a>
                <%--<a href="<c:url value="/people/group/${group.groupId}/new" />" class="create-button">Add Member</a>--%>
                <a href="<c:url value="/people/group/${group.groupId}/members" />" class="people-button">Members</a>
            </div>
        </div>
    </c:forEach>
</div>
<%@ include file="../../layout/footer.jspf" %>
