<%--
  Created by IntelliJ IDEA.
  User: Ken
  Date: 2017/04/11
  Time: 9:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="javax.jdo.PersistenceManager" %>
<%@ page import="javax.jdo.Query" %>
<%@ page import="java.util.List" %>
<%@ page import="planner.PMF" %>
<%@ page import="planner.model.Event" %>
<html>
<head>
    <%
        //ログインしていない場合はトップに戻す
        if (session.getAttribute("id") == null) {
            response.sendRedirect("/");
        }
        //編集するイベントの現在の情報を取得
        String eventId = request.getParameter("eventId");
        PersistenceManager pm = PMF.get().getPersistenceManager();
        Query query = pm.newQuery(Event.class);
        query.setFilter("eventId == paramId");
        query.declareParameters("String paramId");
        List<Event> events = (List<Event>) query.execute(eventId);
        Event event = events.get(0);
        //存在しないイベントの場合は500を返す
        if (event == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

    %>
    <title><%=event.getTitle()%>の編集</title>

</head>
<body>
<form action="/editEvent" method="post">
    <input type="hidden" name="eventId" value="<%=eventId%>">
    <p>タイトル(必須)<input type="text" name="title" value="<%=event.getTitle()%>" required></p>
    <p>日付<input type="date" name="date" value="<%=event.getDate()%>"></p>
    <p> <input type="number" name="length" min="1" max="100" value="<%=event.getLength()%>" >日間</p>
    <p>詳細</p>
    <p><textarea name="description" rows="4" cols="20"><%=event.getDescription()%></textarea></p>
    <p><input type="submit"></p>

</form>
</body>
</html>
