<%@ page import="java.util.Date" %>
<%@ page import="javax.jdo.Query" %>
<%@ page import="planner.PMF" %>
<%@ page import="javax.jdo.PersistenceManager" %>
<%@ page import="planner.model.Event" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Ken
  Date: 2017/04/09
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>イベント一覧</title>
</head>
<body>
<%
    if(session.getAttribute("id") != null){


%>
    <table>
        <tbody>
        <%
            Date date = new Date();
            PersistenceManager pm = PMF.get().getPersistenceManager();
            Query query = pm.newQuery(Event.class);
            //query.setFilter("date.getTime() > paramDate");
            //query.declareParameters("long paramDate");
            //List<Event> eventList = (List<Event>)query.executeWithArray(date.getTime());
            List<Event> eventList = (List<Event>)query.executeWithArray();

            for(Event e:eventList){
        %>
        <tr>
            <td><a href="showEventDetail.jsp?eventId=<%=e.getEventId()%>"><%=e.getTitle()%> </a> </td>
            <td><%=e.getDate() != null? e.getDate().toString() : ""%></td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
<%
    }else{
        response.sendRedirect("/");
    }
%>
</body>
</html>
