<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Lists all projects available from the project repository.
  User: gerwood
  Date: 11/04/2009
  Time: 5:57:49 PM
--%>
<%@ include file="../../layout/header.jspf" %>
<div id="work-area">
    <div class="projects-title">Manage Users</div>
    <c:choose>
        <c:when test="${status == false}">
            <div class="portlet-error">
                    ${message}
            </div>
        </c:when>
        <c:when test="${status == true}">
            <div class="portlet-success">
                    ${message}
            </div>
        </c:when>
    </c:choose>
    <form action="./new" class="edit-group edit">
        <div class="title">Create New User</div>
        <table>
            <colgroup>
                <col class="form-tags"/>
            </colgroup>
            <tbody>
            <tr>
                <th>Username</th>
                <td><input type="text" name="username"/></td>
            </tr>
            <tr>
                <th>Name</th>
                <td><input type="text" name="name"/></td>
            </tr>
            <tr>
                <th>Password</th>
                <td><input type="password" name="password"/></td>
            </tr>
            <tr>
                <th>Confirm Password</th>
                <td><input type="password" name="confirm-password"/></td>
            </tr>
            </tbody>
            <tfoot>
                <tr>
                    <th></th>
                    <td>
                        <input type="submit" class="add-button" value="Add Person"/>
                    </td>
                </tr>
            </tfoot>
        </table>
    </form>
</div>
<%@ include file="../../layout/footer.jspf" %>
