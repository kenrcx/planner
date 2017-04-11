<%--
  Created by IntelliJ IDEA.
  User: Ken
  Date: 2017/04/08
  Time: 22:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ログイン</title>
</head>
<body>
<form action="/login" method="post">

    <p>メールアドレス <input type="email" name="mailAddress" required> </p>
    <p>パスワード <input type="password" name="password" required> </p>
    <p><input value="ログイン" type="submit" ></p>
</form>

</body>
</html>
