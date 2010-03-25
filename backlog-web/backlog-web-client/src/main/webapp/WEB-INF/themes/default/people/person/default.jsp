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
    <div class="projects-title">Manage Users</div>
    <h3>Search</h3>
    <form action="<c:url value="/people/person/search" />" >
        <input type="text" name="username" value=""/> <input type="submit" class='search-button' value="Search"/>
    </form>
    <c:forEach var="person" items="${people}">
        <div id="person-${person.id}" class="person-listing">
            <span style="width: 120px; display: inline-block">
                <a href="<c:url value="/people/person/${person.id}/edit" />" class="edit-button" >Edit</a>
                <a href="<c:url value="/people/person/${person.id}/remove" />" class="delete-button" >Delete</a>
            </span>
            <span style="width: 80px; display: inline-block">${person.username}</span>
            <span style="width: 200px; display: inline-block">${person.name}</span>
                <%--<div class="title"><a href="<c:url value="/people/group/${group.groupId}/details" />" >${group.name} <span class="id">(${group.groupId})</span></a></div>--%>
                <%--<div class="description">${group.description}</div>--%>
                <%--<div class="toolbar">--%>

                <%--<a href="<c:url value="/people/group/${group.groupId}/projects"/>" class="backlog-button" >Manage Projects</a>--%>
                <%--<a href="<c:url value="/people/group/${group.groupId}/new" />" class="create-button">Add Member</a>--%>
                <%--<a href="<c:url value="/people/group/${group.groupId}/members" />" class="people-button">Members</a>--%>
                <%--</div>--%>
        </div>
    </c:forEach>
</div>
<%@ include file="../../layout/footer.jspf" %>
