package Controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;

public class LoginUtil {
    private DataSource source;

    public LoginUtil(DataSource source){
        this.source = source;
    }

    public boolean validate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            return false;
        }

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            connection = source.getConnection();
            String SQL = "SELECT * FROM users WHERE username = ? and passwordUser = ?";
            statement = connection.prepareStatement(SQL);
            statement.setString(1, username);
            statement.setString(2, password);
            result = statement.executeQuery();

            int counter = 0;

            while(result.next()){
                counter++;
            }

            return (counter == 1);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(connection, statement,result);
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
