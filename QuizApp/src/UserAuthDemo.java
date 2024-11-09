//need to be debugged. so commenting down this
/*
import java.util.Scanner;

public class UserAuthDemo {
    private static UserManager userManager = new UserManager();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Register\n2. Login");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (choice == 1) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();

            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            System.out.print("Enter role (user/admin): ");
            String role = scanner.nextLine();

            boolean success = userManager.signUp(username, password, role);
            if (success) {
                System.out.println("Registration successful!");
            } else {
                System.out.println("Registration failed.");
            }

        } else if (choice == 2) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();

            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            String role = userManager.logIn(username, password);
            if (role != null) {
                System.out.println("Login successful! Role: " + role);
            } else {
                System.out.println("Login failed. Incorrect credentials.");
            }
        }

        scanner.close();
    }
}
*/