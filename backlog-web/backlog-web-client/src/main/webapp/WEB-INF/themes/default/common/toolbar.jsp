<div id="appSidebar">
    <c:if test="${area == 'backlog' || area == 'projects' || area == 'iterations'}">
        <%@ include file="../projects/adminToolbar.jsp" %>
    </c:if>
    <c:if test="${area == 'groups' || area == 'people'}">
        <%@ include file="../people/peopleToolbar.jsp" %>
    </c:if>
    <c:if test="${area == 'backlog' || area == 'projects' || area == 'iterations'}">
        <%@ include file="../backlog/projectToolbar.jsp" %>
    </c:if>
    <c:if test="${area == 'backlog' || area == 'iterations'}">
        <%@ include file="../story/storyToolbar.jsp" %>
    </c:if>
</div>
