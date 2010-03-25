<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="com.sitewidesystems.backlog.repository.GroupRepository" %>
<%@ page import="com.sitewidesystems.backlog.repository.PersonRepository" %>
<%@ page import="com.sitewidesystems.backlog.model.org.Group" %>
<%@ page import="java.util.List" %>
<%@ page import="com.sitewidesystems.backlog.model.org.Person" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Lists all groups and people available from the group repository.
  User: gerwood
  Date: 11/04/2009
  Time: 5:57:49 PM
--%>

<%
    ServletContext servletContext = this.getServletContext();

    WebApplicationContext wac = WebApplicationContextUtils.
            getRequiredWebApplicationContext(servletContext);

    GroupRepository gR = (GroupRepository) wac.getBean("groupRepository");
    PersonRepository pR = (PersonRepository) wac.getBean("personDao");


%>
<%@ include file="../layout/header.jspf" %>
<div id="people-list">
    <div class="people-title">Manage People</div>
    <div style="width: 50%; display: inline-block; float: left;">
        <h3>Groups</h3>
        <%
            List<Group> groups = gR.getAllGroups();
            request.setAttribute("groups", groups);
        %>
        Search <input type="text" name="groupSearch" /> <input name="go" value="Go" type="button" />
        <div class="item-list">
            <c:forEach items="${groups}" var="group">
                <div><a href="group/${group.groupId}/members">${group.name}</a></div>
            </c:forEach>
        </div>
    </div>
    <div style="width: 50%; display: inline-block; float:right;">
        <h3>People</h3>

        <%
//            List<Person> people = pR.getAllPeople();
//            request.setAttribute("groups", groups);
        %>
        <div class="item-list">
        </div>
    </div>

</div>
<%@ include file="../layout/footer.jspf" %>
