<%@ page import="model.UserModel" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>

    <%--
        <% %>: xử lý logic code
        <%= %>: xuất giá trị ra màn hình giao diện
        <%! %>: khai báo biến
      --%>

</head>
<body>

<b>

    <c:out value="${param.username}"/>

    <c:out value="${param.username=='cybersoft'}"/>

    <c:out value="${param.username.equals('cybersoft')}"/>


    <c:choose>
        <c:when test="${username == 'cybersoft' && password == '123' }">

            <b>

                    <%--                <c:out value="${username}"/>--%>


                <c:out value="${param.username}"/>
            </b>

        </c:when>
        <c:otherwise>

            <c:out value="Wrong credentials"/>

        </c:otherwise>
    </c:choose>

</b>


</body>


</html>
