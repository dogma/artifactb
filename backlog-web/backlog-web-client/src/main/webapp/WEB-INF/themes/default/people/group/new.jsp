<%--
  This is the view used when creating a new story for a project backlog.
  User: gerwood
  Date: 11/04/2009
  Time: 5:57:58 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../../layout/header.jspf" %>
<div id="work-area">
    <form:form commandName="group" cssClass="edit-group edit">
        <div class="title">Create New Group</div>
        <table>
            <colgroup>
                <col class="form-tags"/>
            </colgroup>
            <tbody>
            <tr>
                <th>Id</th>
                <td><form:input path="groupId" accesskey="i" cssStyle="width: 200px;" maxlength="128"/></td>
                <th>Title</th>
                <td><form:input path="name" accesskey="n" cssStyle="width: 200px;" maxlength="128"/></td>
            </tr>
            <tr>
                <th>Description</th>
                <td colspan="5"><form:textarea path="description" cssStyle="width: 100%; height: 150px;"/></td>
            </tr>
            </tbody>
        </table>
        <input type="submit" value="Save"/>
    </form:form>
</div>
<%@ include file="../../layout/footer.jspf" %>
