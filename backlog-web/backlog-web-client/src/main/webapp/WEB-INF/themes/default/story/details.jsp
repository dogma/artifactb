<%@ page import="com.sitewidesystems.backlog.model.Project" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="com.sitewidesystems.backlog.repository.ProjectRepository" %>
<%@ page import="com.sitewidesystems.backlog.client.util.PathManipulator" %>
<%@ page import="java.util.HashMap" %>
<%--
  This is the view used when creating a new story for a project backlog.
  User: gerwood
  Date: 11/04/2009
  Time: 5:57:58 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../layout/header.jspf" %>
<div id="work-area">
    <div class="view">
        <h3 class="title">${story.title} details</h3>
        <table>
            <colgroup>
                <col class="form-tags"/>
            </colgroup>
            <tbody>
            <tr>
                <th>State</th>
                <td>${story.state}</td>
                <th>Points</th>
                <td>${story.points}</td>
            </tr>
            <tr>
                <th>Added By</th>
                <td>${story.owner}</td>
                <th>Points</th>
                <td>${story.points}</td>
            </tr>
            <tr>
                <th>Opened</th>
                <td>${story.opened}</td>
                <th>Closed</th>
                <td>${story.closed}</td>
            </tr>
            <tr>
                <th colspan="6"><h3 class="story-title">Story</h3></th>
            </tr>
            <tr>
                <td colspan="6">${story.story}</td>
            </tr>
            <tr>
                <th colspan="6"><h3 class="category-title">Categories</h3></th>
            </tr>
            <tr>
                <td colspan="6">
                    <c:forEach var="category" items="${story.categories}">
                        <div class="story-category">${category}</div>
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <th colspan="6"><h3 class="notes-title">Notes</h3></th>
            </tr>
            <tr>
                <td colspan="6">
                </td>
            </tr>
            </tbody>
        </table>
</div>
<%@ include file="../layout/footer.jspf" %>
