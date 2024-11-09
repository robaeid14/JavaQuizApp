public class User {
    private int userId;         // Unique identifier for each user
    private String username;     // Username of the user
    private String password;     // Password of the user
    private String role;         // Role of the user (admin or user)

    // Constructor
    public User(int userId, String username, String password, String role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getters
    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    // Additional methods if needed (e.g., toString for printing user info)
}
