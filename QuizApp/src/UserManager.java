import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserManager {

    // Method for registering a new user
    public boolean signUp(String username, String password, String role) {
        String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password); // Ideally, hash passwords here
            stmt.setString(3, role);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Return true if registration was successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method for logging in a user
    public User logIn(String username, String password) {
        String sql = "SELECT user_id, username, password, role FROM users WHERE username = ? AND password = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int userId = rs.getInt("user_id");
                String userRole = rs.getString("role");

                // Return a User object with full details if login is successful
                return new User(userId, username, password, userRole);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if login fails
    }
}
