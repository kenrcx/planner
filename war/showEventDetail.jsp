<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="javax.jdo.PersistenceManager" %>
<%@ page import="javax.jdo.Query" %>
<%@ page import="java.text.MessageFormat" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="planner.PMF" %>
<%@ page import="planner.model.Event" %>
<%@ page import="planner.model.EventJoin" %>
<%@ page import="planner.model.User" %>
<%@ page import="javax.jws.soap.SOAPBinding" %>
<%@ page import="com.google.appengine.api.datastore.Key" %>
<%@ page import="com.google.appengine.api.datastore.KeyFactory" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    if (session.getAttribute("id") != null) {
        String eventId = request.getParameter("eventId");
        PersistenceManager pm = PMF.get().getPersistenceManager();
        Key k = KeyFactory.stringToKey(eventId);
        Event event = pm.getObjectById(Event.class, k);

        User owner = event.getEventOwner();

        Key userId = (Key)session.getAttribute("id");


        User user = pm.getObjectById(User.class, userId);



%>
<h1><%=event.getTitle() %>
</h1>
<p>主催：<%=owner.getName()%>
</p>
<p><%=event.getDate() != null ? event.getDate().toString() : "" %>
</p>
<p><%=event.getDescription() != null ? event.getDescription() : "" %>
</p>
<br>
<br>
<HR>
<%

%>
<!--
参加予定の表示
-->
<%
    if (event.isInvestigating() == true) {
%>
<table>
    <tbody>

    <%
        //TODO 参加予定/不参加予定を分けて表示する
        int memberCount = 0;
        if (event.getEventjoins().size() > 0) {
            for (EventJoin e : event.getEventjoins()) {
                User tmp = e.getUser();
                if (tmp != null) {
                    if (e.isJoin() == true) {
                        memberCount++;
                    }
    %>
    <tr>
        <td><%=user.getName()%>
        </td>
        <td><%=e.isJoin() == true ? "参加" : "不参加"%>
        </td>
    </tr>
    <%
                }
            }
        }
    %>
    </tbody>
</table>
<p>参加予定:<%=memberCount%>人</p>
<%
    }
%>
<HR>
<%
    //参加申し込みを有効にしているかつ締め切り前のみ入力可能
    Date deadLine = event.getInvesigationDeadLine();
    boolean isDeadLineActive = true;
    if(deadLine == null){
        isDeadLineActive = false;
        deadLine = new java.util.Date();
    }
    if (event.isInvestigating() == true && deadLine.after(new java.util.Date())) {
%>
<form action="/joinEvent" method="post">
    <p>イベントに参加</p>
    <p><input type="radio" name="isJoin" value="true">参加する</p>
    <p><input type="radio" name="isJoin" value="false">参加しない</p>
    <input type="hidden" value="<%=eventId%>" name="eventId">
    <%
      if(isDeadLineActive == true && event.getInvesigationDeadLine() != null){
    %>
        <p>締め切り:<%=event.getInvesigationDeadLine()%></p>
    <%
      }
    %>
    </p>
    <input type="submit">
</form>
<HR>
<%
    }
%>
<%
    if (event.getEventOwner().equals(user)) {
        String key = KeyFactory.keyToString(event.getEventId());
%>
<input type="button" onclick="location.href='/editEvent.jsp?eventId=<%=key%>'" value="イベント編集">
<%
        }
    } else {
        response.sendRedirect("/");
    }
%>
</body>
</html>
