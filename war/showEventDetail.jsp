<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="javax.jdo.PersistenceManager"%>
<%@ page import="javax.jdo.Query"%>
<%@ page import="java.text.MessageFormat"%>
<%@ page import="java.util.List"%>
<%@ page import="planner.PMF"%>
<%@ page import="planner.model.Event"%>
<%@ page import="planner.model.EventJoin"%>
<%@ page import="planner.model.User" %>
<%@ page import="javax.jws.soap.SOAPBinding" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
if(session.getAttribute("id") != null){
String eventId = request.getParameter("eventId");
PersistenceManager pm = PMF.get().getPersistenceManager();
Query eventQuery = pm.newQuery(Event.class);
eventQuery.setFilter("eventId == paramId");
eventQuery.declareParameters("String paramId");
List<Event> events = (List<Event>)eventQuery.execute(eventId);
Event event = events.get(0);

%>
<h1><%=event.getTitle() %></h1>
<p><%=event.getDate() != null? event.getDate().toString():"" %></p>
<p><%=event.getDescription() != null? event.getDescription():"" %></p>
<br>
<br>
<HR>
<%
    Query eventJoinQuery = pm.newQuery(EventJoin.class);
    eventJoinQuery.setFilter("eventId == paramId");
    eventJoinQuery.declareParameters("String paramId");
    List<EventJoin> joinUsers = (List<EventJoin>)eventJoinQuery.execute(eventId);
%>
<table>
    <tbody>
<%
    int memberCount = 0;
    for(EventJoin e: joinUsers){
        Query userQuery = pm.newQuery(User.class);
        userQuery.setFilter("userId == paramId");
        userQuery.declareParameters("String paramId");
        List<User> users = (List<User>)userQuery.execute(e.getUserId());
        User user = users.get(0);
        if(user != null){
            if(e.isJoin() == true){
                memberCount++;
            }
%>
<tr>
    <td><%=user.getName()%></td>
    <td><%=e.isJoin() == true? "参加" : "不参加"%></td>
</tr>
<%
        }
    }
%>
    </tbody>
</table>
<p>参加予定:<%=memberCount%>人</p>

<HR>
<form action="/joinEvent" method="post">
    <p>イベントに参加</p>
    <p><input type="radio" name="isJoin" value="true" >参加する</p>
    <p><input type="radio" name="isJoin" value="false">参加しない</p>
    <input type="hidden" value="<%=eventId%>" name="eventId">
    <input type="submit">
</form>
<HR>
<input type="button" onclick="location.href='/editEvent.jsp?eventId=<%=eventId%>'"value="イベント編集">
<%
}else{
	response.sendRedirect("/");
}
%>
</body>
</html>
