package planner.servlet;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import planner.PMF;
import planner.model.Event;
import planner.model.EventJoin;
import planner.model.User;

/**
 * Created by Ken on 2017/04/11.
 */
public class JoinEventServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String eventId = request.getParameter("eventId");
        HttpSession session = request.getSession(false);

        Boolean isJoin = Boolean.valueOf(request.getParameter("isJoin"));

        Key userId = (Key)session.getAttribute("id");

        PersistenceManager pm = PMF.get().getPersistenceManager();


        User user = pm.getObjectById(User.class, userId);

        Key eventKey = KeyFactory.stringToKey(eventId);
        Event event = pm.getObjectById(Event.class, eventKey);

        List<EventJoin> eventJoins =  event.getEventjoins();

        for(EventJoin e:eventJoins){
            if(e.getUser().equals(user)){
                e.setJoin(isJoin);
                pm.close();
                response.sendRedirect("/showEventDetail.jsp?eventId=" + eventId);
            }
        }


        EventJoin eventJoin = new EventJoin(user, event, isJoin);

        event.addEventJoins(eventJoin);

        pm.close();

        response.sendRedirect("/showEventDetail.jsp?eventId=" + eventId);


    }
}
