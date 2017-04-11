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
import planner.PasswordHash;
import planner.model.User;

/**
 * Created by Ken on 2017/04/08.
 */
public class LoginServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        String email = request.getParameter("mailAddress");
        String password = request.getParameter("password");

        PersistenceManager pm = PMF.get().getPersistenceManager();

        Query query =  pm.newQuery(User.class);

        query.setFilter("mailAddress == paramMailAddress");
        query.declareParameters("String paramMailAddress");

        List<User> users = (List<User>)query.execute(email);

        pm.close();

        if(!users.isEmpty()){
            User user = users.get(0);
            if(user.getHashedPassword().equals(PasswordHash.getPasswordHash(user.getUserId(), password))){
                HttpSession session = request.getSession(true);
                session.invalidate();
                HttpSession newSession = request.getSession(true);
                newSession.setAttribute("id", user.getUserId());
                response.sendRedirect("/");
            }else{
                response.sendRedirect("/ng");
            }
        }else {
            response.sendRedirect("/no");
        }
    }
}
