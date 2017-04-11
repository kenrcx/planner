<%--
  Created by IntelliJ IDEA.
  User: Ken
  Date: 2017/04/08
  Time: 20:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <%
  if(session.getAttribute("id") == null){
  %>
  <input type="button" onclick="location.href='/login.jsp'"value="ログイン"><br>
  <input type="button" onclick="location.href='/userRegist.jsp'"value="新規登録"><br>
  <%
  }else{
  %>
  <input type="button" onclick="location.href='/showEvent.jsp'"value="イベント一覧"><br>
  <input type="button" onclick="location.href='/addEvent.jsp'"value="イベント登録"><br>
  <%
  }
  %>
  </body>
</html>
