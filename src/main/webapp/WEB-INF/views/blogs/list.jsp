<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/3/17
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Blogs List</title>
</head>
<body>
Blog List:
<c:forEach items="${blogList}" var="myblog">
    <p>myblog.id:<c:out value="${myblog.id}"/></p>
    <p>myblog.title:<c:out value="${myblog.title}"/></p>
    <p>myblog.author:<c:out value="${myblog.author}"/></p>
    <p>myblog.content:<c:out value="${myblog.content}"/></p>
</c:forEach>
</body>
</html>
