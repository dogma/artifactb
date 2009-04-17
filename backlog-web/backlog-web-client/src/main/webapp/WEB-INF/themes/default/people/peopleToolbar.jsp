<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="project-toolbar">
    <div class="title">People</div>
    <a href="<c:url value="/people/person/new" />" class="person-create-button">Add Person</a>
    <a href="<c:url value="/people/groups/list" />" class="group-manage-button">Manage Groups</a>
    <a href="<c:url value="/people/group/new" />" class="group-create-button">Create Group</a>
</div>
