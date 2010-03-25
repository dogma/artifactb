<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="people-toolbar" class="toolbar">
    <div class="title">People</div>
    <a href="<c:url value="/people/person/search" />" class="person-find-button">Find Person</a>
    <a href="<c:url value="/people/person/new" />" class="person-create-button">Add Person</a>
</div>
<div id="group-toolbar" class="toolbar">
    <div class="title">Groups</div>
    <a href="<c:url value="/people/groups/list" />" class="group-manage-button">Manage Groups</a>
    <a href="<c:url value="/people/group/new" />" class="group-create-button">Create Group</a>
</div>
