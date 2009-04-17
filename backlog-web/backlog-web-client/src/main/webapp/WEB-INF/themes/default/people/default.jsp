<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Lists all groups and people available from the group repository.
  User: gerwood
  Date: 11/04/2009
  Time: 5:57:49 PM
--%>

<%
%>
<%@ include file="../layout/header.jspf" %>
<div id="people-list">
    <div class="people-title">People</div>
    <div style="width: 40%; display: inline-block;">
        <h3>Groups</h3>
    </div>
    <div style="width: 40%; display: inline-block;">
        <h3>People</h3>
    </div>

</div>
<%@ include file="../layout/footer.jspf" %>
