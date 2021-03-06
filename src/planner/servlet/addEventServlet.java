package planner.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import com.google.api.server.spi.types.SimpleDate;
import com.google.appengine.api.datastore.Key;

import planner.PMF;
import planner.Sanitizer;
import planner.model.Event;
import planner.model.User;

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
        //日付を取得し、フォーマッタを通してDate型に変換
        Date date = null;
        try {
            date = format.parse(request.getParameter("date"));
        } catch (ParseException e) {
        	date = null;
        }

        Date deadLine = date = new GregorianCalendar(2100, 1, 1, 0, 0, 0).getTime();
        try{
            deadLine = format.parse(request.getParameter("deadline"));
        }catch (ParseException e){
            date = new GregorianCalendar(2100, 1, 1, 0, 0, 0).getTime();

        }

        //開催日数がnullの場合があるのでtry-catch
        int length;
        try{
        	length = Integer.parseInt(request.getParameter("length"));
        }catch(NumberFormatException e){
        	length = 0;
        }

        String description = request.getParameter("description");
        description =Sanitizer.convertSanitize(description);

        Boolean isInvest = Boolean.valueOf(request.getParameter("isInvest"));
        if(isInvest == null){
            isInvest = false;
        }

        HttpSession session =  request.getSession(false);
        Key ownerId = (Key)session.getAttribute("id");

        PersistenceManager pm = PMF.get().getPersistenceManager();


        User owner = pm.getObjectById(User.class, ownerId);


        Event event = new Event(title, date, length, description, owner, isInvest, deadLine);



        pm.makePersistent(event);

        pm.close();

        response.sendRedirect("/");
    }
}
