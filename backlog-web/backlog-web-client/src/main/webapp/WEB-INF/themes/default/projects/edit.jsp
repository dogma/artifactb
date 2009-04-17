<%--
  Created by IntelliJ IDEA.
  User: gerwood
  Date: 11/04/2009
  Time: 5:57:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../layout/header.jspf"%>
<div id="work-area">
<form:form commandName="project" cssClass="edit-project edit">
<div class="title">Editing: ${project.title} <span class="id">(${project.projectId})</span></div>
    <table>
        <colgroup>
            <col class="form-tags" />
        </colgroup>
        <tbody>
        <%--<tr>--%>
            <%--<th>Id</th>--%>
            <%--<td><form:input path="projectId" /></td>--%>
        <%--</tr>--%>
        <tr>
            <th>Title</th>
            <td><form:input path="title" accesskey="t" cssStyle="width: 200px;" maxlength="128" /></td>
            <th>State</th>
            <td>
                <form:select path="state">
                    <option value="new">New</option>
                    <option value="accepted">Accepted</option>
                    <option value="in-progress">In Progress</option>
                    <option value="complete">Complete</option>
                    <option value="abandoned">Abandoned</option>
                </form:select>
            </td>
        </tr>
        <tr>
            <th>Description</th>
            <td colspan="3"><form:textarea path="description" cssStyle="width: 100%; height: 150px;"/></td>
        </tr>
        </tbody>
    </table>
<input type="submit" value="Save" />
</form:form>
</div>
<%@ include file="../layout/footer.jspf"%>
