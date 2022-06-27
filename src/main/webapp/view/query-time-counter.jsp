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
    Dresses with price > 2000 = <c:out value="${count}"/>.
    Request took: <c:out value="${time}"/> ms.
</h2>



</body>
</html>
