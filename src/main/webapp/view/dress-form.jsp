<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Dresses Store Application</title>
</head>
<body>

    <c:if test="${dress != null}">
        <form method="post" action="${pageContext.request.contextPath}/dresses/edit">
    </c:if>

    <c:if test="${dress == null}">
        <form method="post" action="${pageContext.request.contextPath}/dresses/add">
    </c:if>

        <input type="hidden" id="id" name="id" value="<c:out value='${dress.id}' />">

        <label for="color">Color:</label><br>
        <input type="text" id="color" name="color" value="<c:out value='${dress.color}' />"><br>

        <label for="price">Price:</label><br>
        <input type="number" id="price" name="price" value="<c:out value='${dress.price}' />"><br>

        <label for="inStock">In stock:</label><br>
        <input type="number" id="inStock" name="inStock" value="<c:out value='${dress.inStock}' />"><br>

        <input type="submit">
    </form>

</body>
</html>
