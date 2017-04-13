<%--
  Created by IntelliJ IDEA.
  User: Ken
  Date: 2017/04/09
  Time: 11:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>イベント追加</title>
</head>
<body>
<%
    if (session.getAttribute("id") != null) {
%>
<form action="/addEvent" method="post">
    <p>タイトル(必須)<input type="text" name="title" required></p>
    <p>日付<input type="date" name="date"></p>
    <p><input type="number" name="length" min="1" max="100" value="1">日間</p>
    <p>詳細</p>
    <p><textarea name="description" rows="4" cols="20"></textarea></p>
    <p>参加アンケートを</p>
    <p><input type="radio" name="isInvest" value="true" >行う</p>
    <p><input type="radio" name="isInvest" value="false">行わない</p>

    <p>アンケート締め切り<input type="date" name="deadline"></p>
    <p><input type="submit"></p>

</form>
<%
    }else{
        response.sendRedirect("/");
    }
%>

</body>
</html>
