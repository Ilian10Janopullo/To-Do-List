package Controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "/AddActivityServlet")
public class AddActivityServlet extends HttpServlet {

    public void init() {
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        List<String> activities = (List<String>) request.getSession().getAttribute("listActivities");

        if (activities == null) {
            activities = new ArrayList<>();
        }

        String activity = request.getParameter("activity");
        if (activity != null && !activity.trim().isEmpty()) {
            activities.add(activity);
        }

        request.getSession().setAttribute("listActivities", activities);

        RequestDispatcher dispatcher = request.getRequestDispatcher("protected/to-do.jsp");
        dispatcher.forward(request, response);
    }


    public void destroy() {
    }
}