package Controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "/RemoveActivityServlet")
public class RemoveActivityServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> activities = (List<String>) request.getSession().getAttribute("listActivities");
        String itemToBeRemoved = request.getParameter("remove");

        for(int i = 0 ; i< activities.size() ; i++){
            if(activities.get(i).equals(itemToBeRemoved)){
                activities.remove(i);
                break;
            }
        }

        request.getSession().setAttribute("listActivities", activities);
        RequestDispatcher dispatcher = request.getRequestDispatcher("protected/to-do.jsp");
        dispatcher.forward(request, response);
    }

}
