package planner.servlet;

import planner.PMF;
import planner.Sanitizer;
import planner.model.Event;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Ken on 2017/04/11.
 */
public class editEventServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String eventId =request.getParameter("eventId");

        //編集するイベントの情報を取得
        PersistenceManager pm = PMF.get().getPersistenceManager();
        Query query = pm.newQuery(Event.class);
        query.setFilter("eventId == paramId");
        query.declareParameters("String paramId");
        List<Event> events = (List<Event>) query.execute(eventId);
        Event event = events.get(0);

        //クエリから変更後の情報を取得
        String title = request.getParameter("title");
        title = Sanitizer.convertSanitize(title);
        Date date;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = format.parse(request.getParameter("date"));
        } catch (ParseException e) {
            date = null;
        }
        int length;
        try{
            length = Integer.parseInt(request.getParameter("length"));
        }catch(NumberFormatException e){
            length = 0;
        }
        String description = request.getParameter("description");
        description =Sanitizer.convertSanitize(description);

        //書き込み
        event.setTitle(title);
        event.setDate(date);
        event.setLength(length);
        event.setDescription(description);

        response.sendRedirect("/showEventDetail.jsp?eventId=" + eventId);

    }
}