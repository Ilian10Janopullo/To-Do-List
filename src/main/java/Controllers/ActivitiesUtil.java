package Controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActivitiesUtil {
    private final DataSource source;

    public ActivitiesUtil(DataSource source){
        this.source = source;
    }

    public List<String> getActivities(String username) throws ServletException, IOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<String> activities = new ArrayList<>();

        try{
            connection = source.getConnection();
            preparedStatement = connection.prepareStatement("select * from activities where username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                activities.add(resultSet.getString(2));
            }
            return activities;
        }catch (Exception ignored){

        }finally {
            close(connection, preparedStatement, resultSet);
        }
        return null;
    }

    public void removeActivity(String activity, String user){
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = source.getConnection();
            preparedStatement = connection.prepareStatement("delete from activities where username = ? and activity = ?");
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, activity);
            preparedStatement.execute();
        }catch (Exception ignored){

        }finally {
            close(connection, preparedStatement, null);
        }

    }

    public void addActivity(String activity, String user){
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = source.getConnection();
            preparedStatement = connection.prepareStatement("insert into activities (activity, username) values(?,?)");
            preparedStatement.setString(1, activity);
            preparedStatement.setString(2, user);
            preparedStatement.execute();
        }catch (Exception ignored){

        }finally {
            close(connection, preparedStatement, null);
        }
    }

    private void close(Connection myConn, Statement myStmt, ResultSet myResult){
        try{
            if(myConn != null){
                myConn.close();
            }
            if(myStmt != null){
                myStmt.close();
            }
            if(myResult != null){
                myResult.close();
            }
        }catch (Exception exception){
            exception.fillInStackTrace();
        }
    }
}
