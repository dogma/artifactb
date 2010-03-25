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
    <form:form commandName="story" cssClass="edit-story edit">
        <div class="title">Update Story</div>
        <table>
            <colgroup>
                <col class="form-tags"/>
            </colgroup>
            <tbody>
            <tr>
                <th>Project</th>
                <td>${project.title}</td>
            </tr>
            <tr>
                <th>Title</th>
                <td><form:input path="title" accesskey="t" cssStyle="width: 200px;" maxlength="128"/></td>
                <th>State</th>
                <td>
                    <form:select path="state">
                        <form:option value="new">New</form:option>
                        <form:option value="accepted">Accepted</form:option>
                        <form:option value="in-progress">In Progress</form:option>
                        <form:option value="closed">Closed</form:option>
                    </form:select>
                </td>
            </tr>
            <tr>
                <th>Priority</th>
                <td>
                    ${story.priority}
                </td>
                <th>Points</th>
                <td>
                    <form:select path="points">
                        <form:option value="0">0</form:option>
                        <form:option value="0.5">1/2</form:option>
                        <form:option value="1">1</form:option>
                        <form:option value="3">3</form:option>
                        <form:option value="5">5</form:option>
                        <form:option value="8">8</form:option>
                        <form:option value="20">20</form:option>
                        <form:option value="50">50</form:option>
                        <form:option value="100">100</form:option>
                    </form:select>
                </td>
            </tr>
            <tr>
                <th>Story</th>
                <td colspan="3"><form:textarea path="story" cssStyle="width: 100%; height: 150px;"/></td>
            </tr>
            </tbody>
            <tfoot>
            <tr>
                <th></th>
                <td>
                    <input type="submit" class="save-button" value="Save"/>
                </td>
            </tr>
            </tfoot>
        </table>
    </form:form>
</div>
<%@ include file="../layout/footer.jspf" %>
