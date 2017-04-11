package planner.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.google.api.server.spi.types.SimpleDate;
import planner.PMF;
import planner.Sanitizer;
import planner.model.Event;

/**
 * Created by Ken on 2017/04/09.
 */
public class addEventServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        response.setContentType("text/html; charset=UTF-8");
        String title = request.getParameter("title");
        title =Sanitizer.convertSanitize(title);
        Date date = null;
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
        String eventId = UUID.randomUUID().toString();
        Event event = new Event(eventId, title, date, length, description);

        PersistenceManager pm = PMF.get().getPersistenceManager();

        pm.makePersistent(event);

        pm.close();

        response.sendRedirect("/");
    }
}
