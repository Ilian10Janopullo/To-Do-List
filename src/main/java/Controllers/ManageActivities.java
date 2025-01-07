package Controllers;

import Models.User;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "/ManageActivities")
public class ManageActivities extends HttpServlet {

    @Resource(name = "jdbc/users")
    private DataSource source;

    private ActivitiesUtil activitiesUtil;

    @Override
    public void init() throws ServletException{
        activitiesUtil = new ActivitiesUtil(source);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        User user = (User) request.getSession().getAttribute("user");

        response.setContentType("text/html");
        String command = "loadActivities";

        if(request.getParameter("command") != null){
            command = request.getParameter("command");
        }

        switch (command) {
            case "loadActivities":
                loadActivities(request, response, user);
                break;
            case "removeActivities":
                removeActivities(request, response, user);
                break;
            case "addActivities":
                addActivities(request, response, user);
                break;
            default:
                break;
        }
    }

    private void addActivities(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        String activity = request.getParameter("activity");
        activitiesUtil.addActivity(activity, user.getUsername());
        loadActivities(request, response, user);
    }

    private void removeActivities(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        String activityToRemove = request.getParameter("activityToRemove");
        activitiesUtil.removeActivity(activityToRemove, user.getUsername());
        loadActivities(request, response, user);
    }

    private void loadActivities(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        List<String> activities = activitiesUtil.getActivities(user.getUsername());
        request.getSession().setAttribute("listActivities", activities);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/protected/to-do.jsp");
        dispatcher.forward(request, response);
    }


    public void destroy() {
    }
}