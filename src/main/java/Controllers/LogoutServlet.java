package Controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


@WebServlet(name = "LogoutServlet")
public class LogoutServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("protectedPage/login-page.jsp");
        dispatcher.forward(request, response);
    }
}
