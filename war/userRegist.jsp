<%--
  Created by IntelliJ IDEA.
  User: Ken
  Date: 2017/04/08
  Time: 21:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登録</title>
</head>
<body>
<form action="/userRegist" method="post">
    <p>名前 <input type="text" name="name" required> </p>
    <p>メールアドレス <input type="email" name="mailAddress" required> </p>
    <p>パスワード <input type="password" name="password" required> </p>
    <p><input type="submit"></p>
</form>

</body>
</html>
