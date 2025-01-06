package Controllers;

import Models.User;

import java.io.*;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import javax.sql.DataSource;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    @Resource(name = "jdbc/users")
    private DataSource source;

    private LoginUtil loginUtil;

    @Override
    public void init() throws ServletException{
        loginUtil = new LoginUtil(source);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if(loginUtil.validate(request, response)){
            HttpSession oldSession = request.getSession(false);

            if(oldSession != null){
                oldSession.invalidate();
            }

            HttpSession newSession = request.getSession(true);
            newSession.setMaxInactiveInterval(60*10);
            newSession.setAttribute("user", new User(request.getParameter("username"), request.getParameter("password")));

            RequestDispatcher dispatcher = request.getRequestDispatcher("protected/to-do.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login-page.jsp");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Either username or password is wrong.</font>");
            dispatcher.forward(request,response);
        }
    }

    public void destroy() {
    }
}