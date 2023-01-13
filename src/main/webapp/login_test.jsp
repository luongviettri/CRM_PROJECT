<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>

</head>

<body>


<%--<c:choose>--%>
<%--    <c:when test="${param.enter=='1'}">--%>
<%--        pizza.--%>
<%--        <br/>--%>
<%--    </c:when>--%>
<%--    <c:otherwise>--%>
<%--        pizzas.--%>
<%--        <br/>--%>
<%--    </c:otherwise>--%>
<%--</c:choose>--%>


<%--
       -Action: link muốn gọi đến
       -Method: phương thức muốn dùng [GET, POST]
       -thuộc tính name của tag input là tên tham số truyền đến action
 --%>
<form action="http://localhost:8080/login" method="post">
    <input name="username" type="text" placeholder="User name"/>
    <input name="password" type="text" placeholder="Password"/>
    <button>Login</button>
</form>

</body>
</html>