<%--
  This view is used to create the project.
  User: gerwood
  Date: 11/04/2009
  Time: 5:57:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../layout/header.jspf"%>
<form:form commandName="project" cssClass="edit-project">
<div class="title">${project.title} <span class="id">(${project.projectId})</span></div>
    <table>
        <tbody>
        <tr>
            <th>Title</th>
            <td><form:input path="title" accesskey="t" /></td>
        </tr>
        <tr>
            <th>Description</th>
            <td><form:textarea path="description"/></td>
        </tr>
        </tbody>
    </table>
<input type="submit" value="Save" />
</form:form>
<%@ include file="../layout/footer.jspf"%>
