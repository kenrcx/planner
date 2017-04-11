package planner.servlet;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import planner.PMF;
import planner.model.EventJoin;

/**
 * Created by Ken on 2017/04/11.
 */
public class JoinEventServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String eventId = request.getParameter("eventId");
        HttpSession session = request.getSession(false);
        String userId = String.valueOf(session.getAttribute("id"));
        Boolean isJoin = Boolean.valueOf(request.getParameter("isJoin"));

        PersistenceManager pm = PMF.get().getPersistenceManager();
        Query query = pm.newQuery(EventJoin.class);
        query.setFilter("eventId == paramEventId & userId == paramUserId");
        query.declareParameters("String paramEventId, String paramUserId");
        List<EventJoin> users = (List<EventJoin>)query.execute(eventId, userId);

        if(users.isEmpty() == true){
            EventJoin eventJoin = new EventJoin(eventId, userId, isJoin);
            pm.makePersistent(eventJoin);
        }else{
            EventJoin eventJoin = users.get(0);
            eventJoin.setJoin(isJoin);
        }




        pm.close();

        response.sendRedirect("/showEventDetail.jsp?eventId=" + eventId);


    }
}
