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
    <c:if test="${not empty error}">
        <div class="portlet-error">${error}</div>
    </c:if>
    <form:form commandName="group" cssClass="edit-group edit">
        <div class="title">${group.name}</div>
        <table>
            <colgroup>
                <col class="form-tags"/>
            </colgroup>
            <tbody>
            <tr>
                <th>Members</th>
                <td>
                    <c:forEach items="${group.members}" var="member">
                        <div >${member.member}</div>
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <th>New Member</th>
                <td>
                    <form>
                        <input type="hidden" name="add" value="true" />
                        <input type="text" name="username" value="" />  <input type="submit" class="add-button" value="Add"/>
                    </form>
                </td>
            </tr>
            </tbody>
        </table>
    </form:form>
</div>
<%@ include file="../../layout/footer.jspf" %>
