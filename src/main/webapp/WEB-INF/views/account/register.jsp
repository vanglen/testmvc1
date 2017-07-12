<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/3/17
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>

Welcome To Register!
<form method="post">
    <div>name:<input type="text" name="name"/></div>
    <div>pwd:<input type="text" name="pwd"/></div>
    <div><input type="submit"/></div>
</form>

<sf:form method="post" commandName="account">
    <sf:errors path="*"/>
    <div>name:<sf:input path="name"/></div>
    <div>pwd:<sf:password path="pwd"/></div>
    <div><input type="submit"/></div>
</sf:form>
</body>
</html>
