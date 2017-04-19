package planner.servlet;

import java.io.IOException;
import java.util.UUID;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import planner.PMF;
import planner.PasswordHash;
import planner.Sanitizer;
import planner.model.User;

/**
 * Created by Ken on 2017/04/08.
 */
public class UserRegistServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        String name = request.getParameter("name");
        name = Sanitizer.convertSanitize(name);
        String email = request.getParameter("mailAddress");
        email = Sanitizer.convertSanitize(email);
        String password = request.getParameter("password");


        User user = new User(name, email);

        PersistenceManager pm = PMF.get().getPersistenceManager();

        pm.makePersistent(user);

        user.setHashedPassword(PasswordHash.getPasswordHash(user.getUserId(), password));

        pm.close();



        response.sendRedirect("/");

    }
}
