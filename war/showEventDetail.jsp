<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="javax.jdo.PersistenceManager"%>
<%@ page import="javax.jdo.Query"%>
<%@ page import="java.text.MessageFormat"%>
<%@ page import="java.util.List"%>
<%@ page import="planner.PMF"%>
<%@ page import="planner.model.Event"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
if(session.getAttribute("id") != null){
String eventId = request.getParameter("eventId");
PersistenceManager pm = PMF.get().getPersistenceManager();
Query query = pm.newQuery(Event.class);
query.setFilter("eventId == paramId");
query.declareParameters("String paramId");
List<Event> events = (List<Event>)query.execute(eventId);
Event event = events.get(0);

%>
<h1><%=event.getTitle() %></h1>
<p><%=event.getDate() != null? event.getDate().toString():"" %></p>
<p><%=event.getDescription() != null? event.getDescription():"" %></p>
<br>
<br>
<input type="button" onclick="location.href='/editEvent.jsp?eventId=<%=eventId%>'"value="イベント編集">

<form action="/joinEvent" method="post">
    <p>イベントに参加</p>
    <p><input type="radio" name="isJoin" value="true">参加する</p>
    <p><input type="radio" name="isJoin" value="false">参加しない</p>
    <input type="hidden" value="<%=eventId%>" name="eventId">
    <input type="submit">
</form>
<%
}else{
	response.sendRedirect("/");
}
%>
</body>
</html>
