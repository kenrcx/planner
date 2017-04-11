package planner.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Ken on 2017/04/11.
 */
public class LogOutServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if(session != null){
        	session.removeAttribute("id");
        	session.invalidate();
        }
        response.sendRedirect("/");
    }
}
