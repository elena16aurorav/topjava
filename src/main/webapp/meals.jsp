<%--
  Created by IntelliJ IDEA.
  User: a
  Date: 10.02.2019
  Time: 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>meals</title>
</head>
<body>
"${a}"

<table>
<c:forEach items="${mealsTo}" var="l" >
  <tr>
  <td><c:out value="${l.getDateTime()}"/></td>
    <td><c:out value="${l.getDescription()}"/></td>
  </tr>
</c:forEach>
</table>

</body>
</html>
