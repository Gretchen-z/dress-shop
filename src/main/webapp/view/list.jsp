<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Dresses Shop Application</title>
</head>
<body>

<h1>Dress Management</h1>
<h2>
    <a href="${pageContext.request.contextPath}/dresses/add">Add New Dress</a>

</h2>

<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Dresses</h2></caption>
        <tr>
            <th>ID</th>
            <th>Color</th>
            <th>Price</th>
            <th>InStock</th>
            <th>Actions</th>
        </tr>

        <c:forEach var="dress" items="${dresses}">
            <tr>
                <td><c:out value="${dress.id}"/></td>
                <td><c:out value="${dress.color}"/></td>
                <td><c:out value="${dress.price}"/></td>
                <td><c:out value="${dress.inStock}"/></td>
                <td>
                    <a href="${pageContext.request.contextPath}/dresses/edit?id=<c:out value='${dress.id}' />">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="${pageContext.request.contextPath}/dresses/delete?id=<c:out value='${dress.id}' />">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
