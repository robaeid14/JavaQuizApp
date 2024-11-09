/*
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
*/
import java.sql.*;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/Quizdb";
    private static final String USER = "root"; // replace with your MySQL username
    private static final String PASSWORD = "1902147"; // replace with your MySQL password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    //To test connectivity run the below code
    /*
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null) {
                System.out.println("Connected to the database!");
            }
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database.");
            e.printStackTrace();
        }
    }
*/

}
