import java.util.Scanner;

public class QuizApp {
    private static UserManager userManager = new UserManager();
    private static QuestionManager questionManager = new QuestionManager();
    private static QuizManager quizManager = new QuizManager();
    private static User currentUser;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nWelcome to the Quiz Application!");
            System.out.println("1. Sign Up");
            System.out.println("2. Log In");
            System.out.println("3. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    signUp(scanner);
                    break;
                case 2:
                    logIn(scanner);
                    if (currentUser != null) {
                        if (currentUser.getRole().equalsIgnoreCase("admin")) {
                            adminMenu(scanner);
                        } else {
                            userMenu(scanner);
                        }
                    }
                    break;
                case 3:
                    System.out.println("Exiting the application...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void signUp(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter role (admin/user): ");
        String role = scanner.nextLine();

        boolean success = userManager.signUp(username, password, role);
        if (success) {
            System.out.println("Sign up successful!");
        } else {
            System.out.println("Sign up failed. Please try again.");
        }
    }

    private static void logIn(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        currentUser = userManager.logIn(username, password);
        if (currentUser == null) {
            System.out.println("Login failed. Please try again.");
        } else {
            System.out.println("Login successful! Welcome, " + currentUser.getUsername());
        }
    }

    private static void adminMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Add Question");
            System.out.println("2. Update Question");
            System.out.println("3. Delete Question");
            System.out.println("4. View All Questions");
            System.out.println("5. Log Out");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addQuestion(scanner);
                    break;
                case 2:
                    updateQuestion(scanner);
                    break;
                case 3:
                    deleteQuestion(scanner);
                    break;
                case 4:
                    viewAllQuestions();
                    break;
                case 5:
                    System.out.println("Logging out...");
                    currentUser = null;
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Start of the user menu
    
    private static void userMenu(Scanner scanner) {
    System.out.println("\nWelcome to the Quiz Section!");

    // Ask for number of questions for the quiz
    System.out.print("Enter the number of questions for the quiz: ");
    int numQuestions = getIntInput(scanner);

    // Ask for time limit
    System.out.print("Enter the time limit in seconds: ");
    int timeLimit = getIntInput(scanner);

    // Start the quiz
    quizManager.loadQuestions(numQuestions);
    quizManager.startQuiz(timeLimit);

    // End of quiz message and exit
    System.out.println("Quiz completed! Exiting application. Goodbye!");
    System.exit(0);
}

// Helper method to safely get an integer input
private static int getIntInput(Scanner scanner) {
    while (!scanner.hasNextInt()) {
        System.out.println("Invalid input. Please enter a number.");
        scanner.next(); // Consume the invalid input
    }
    int input = scanner.nextInt();
    scanner.nextLine(); // Consume newline character
    return input;
}


    /*
    //this method causing post quiz option error. so commenting out this portion of code
    private static void userMenu(Scanner scanner) {
        boolean keepGoing = true;

        while (keepGoing) {
            System.out.println("\nWelcome to the Quiz Section!");

            // Ask for number of questions for the quiz
            System.out.print("Enter the number of questions for the quiz: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid number for questions.");
                scanner.next(); // Consume the invalid input
            }
            int numQuestions = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character left by nextInt()

            // Ask for time limit
            System.out.print("Enter the time limit in seconds: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid number for time limit.");
                scanner.next(); // Consume the invalid input
            }
            int timeLimit = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            // Start the quiz
            quizManager.loadQuestions(numQuestions);
            quizManager.startQuiz(timeLimit);

            // Post-quiz options
            System.out.println("Quiz completed! What would you like to do next?");
            System.out.println("1. Take Another Quiz");
            System.out.println("2. Log Out");
            System.out.println("3. Exit Application");

            // Get user's choice for next steps
            System.out.print("Choose an option: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid option.");
                scanner.next(); // Consume the invalid input
            }
            int postQuizChoice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            // Handle the user's choice
            switch (postQuizChoice) {
                case 1:
                    // Take another quiz
                    break;
                case 2:
                    keepGoing = false;
                    currentUser = null;
                    System.out.println("Logging out...");
                    break;
                case 3:
                    keepGoing = false;
                    System.out.println("Exiting application. Goodbye!");
                    System.exit(0);  // Exit the program
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
*/
    // End of the user menu

    private static void addQuestion(Scanner scanner) {
        System.out.print("Enter question text: ");
        String questionText = scanner.nextLine();
        System.out.print("Option 1: ");
        String option1 = scanner.nextLine();
        System.out.print("Option 2: ");
        String option2 = scanner.nextLine();
        System.out.print("Option 3: ");
        String option3 = scanner.nextLine();
        System.out.print("Option 4: ");
        String option4 = scanner.nextLine();
        System.out.print("Correct option (1-4): ");
        int correctOption = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        boolean success = questionManager.addQuestion(questionText, option1, option2, option3, option4, correctOption, currentUser.getUserId());
        System.out.println(success ? "Question added successfully!" : "Failed to add question.");
    }

    private static void updateQuestion(Scanner scanner) {
        System.out.print("Enter question ID to update: ");
        int questionId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter new question text: ");
        String questionText = scanner.nextLine();
        System.out.print("New Option 1: ");
        String option1 = scanner.nextLine();
        System.out.print("New Option 2: ");
        String option2 = scanner.nextLine();
        System.out.print("New Option 3: ");
        String option3 = scanner.nextLine();
        System.out.print("New Option 4: ");
        String option4 = scanner.nextLine();
        System.out.print("Correct option (1-4): ");
        int correctOption = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        boolean success = questionManager.updateQuestion(questionId, questionText, option1, option2, option3, option4, correctOption);
        System.out.println(success ? "Question updated successfully!" : "Failed to update question.");
    }

    private static void deleteQuestion(Scanner scanner) {
        System.out.print("Enter question ID to delete: ");
        int questionId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        boolean success = questionManager.deleteQuestion(questionId);
        System.out.println(success ? "Question deleted successfully!" : "Failed to delete question.");
    }

    private static void viewAllQuestions() {
        System.out.println("All Questions:");
        for (String question : questionManager.getAllQuestions()) {
            System.out.println(question);
        }
    }
}




/*
import java.util.Scanner;

public class QuizApp {
    private static UserManager userManager = new UserManager();
    private static QuestionManager questionManager = new QuestionManager();
    private static QuizManager quizManager = new QuizManager();
    private static User currentUser;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nWelcome to the Quiz Application!");
            System.out.println("1. Sign Up");
            System.out.println("2. Log In");
            System.out.println("3. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    signUp(scanner);
                    break;
                case 2:
                    logIn(scanner);
                    if (currentUser != null) {
                        if (currentUser.getRole().equalsIgnoreCase("admin")) {
                            adminMenu(scanner);
                        } else {
                            userMenu(scanner);
                        }
                    }
                    break;
                case 3:
                    System.out.println("Exiting the application...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void signUp(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter role (admin/user): ");
        String role = scanner.nextLine();

        boolean success = userManager.signUp(username, password, role);
        if (success) {
            System.out.println("Sign up successful!");
        } else {
            System.out.println("Sign up failed. Please try again.");
        }
    }

    private static void logIn(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        currentUser = userManager.logIn(username, password);
        if (currentUser == null) {
            System.out.println("Login failed. Please try again.");
        } else {
            System.out.println("Login successful! Welcome, " + currentUser.getUsername());
        }
    }

    private static void adminMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Add Question");
            System.out.println("2. Update Question");
            System.out.println("3. Delete Question");
            System.out.println("4. View All Questions");
            System.out.println("5. Log Out");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addQuestion(scanner);
                    break;
                case 2:
                    updateQuestion(scanner);
                    break;
                case 3:
                    deleteQuestion(scanner);
                    break;
                case 4:
                    viewAllQuestions();
                    break;
                case 5:
                    System.out.println("Logging out...");
                    currentUser = null;
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
//start of the user menu
    private static void userMenu(Scanner scanner) {
    boolean keepGoing = true;
    
    while (keepGoing) {
        System.out.println("\nWelcome to the Quiz Section!");

        // Ask for number of questions for the quiz
        System.out.print("Enter the number of questions for the quiz: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid number for questions.");
            scanner.next(); // Consume the invalid input
        }
        int numQuestions = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character left by nextInt()

        // Ask for time limit
        System.out.print("Enter the time limit in seconds: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid number for time limit.");
            scanner.next(); // Consume the invalid input
        }
        int timeLimit = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        // Start the quiz
        quizManager.loadQuestions(numQuestions);
        quizManager.startQuiz(timeLimit);

        // Post-quiz options
        System.out.println("Quiz completed! What would you like to do next?");
        System.out.println("1. Take Another Quiz");
        System.out.println("2. Log Out");
        System.out.println("3. Exit Application");

        // Get user's choice for next steps
        System.out.print("Choose an option: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid option.");
            scanner.next(); // Consume the invalid input
        }
        int postQuizChoice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        // Handle the user's choice
        switch (postQuizChoice) {
            case 1:
                // Take another quiz
                break;
            case 2:
                keepGoing = false;
                currentUser = null;
                System.out.println("Logging out...");
                break;
            case 3:
                keepGoing = false;
                System.out.println("Exiting application. Goodbye!");
                System.exit(0);  // Exit the program
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }
}


//end of the user menu
    private static void addQuestion(Scanner scanner) {
        System.out.print("Enter question text: ");
        String questionText = scanner.nextLine();
        System.out.print("Option 1: ");
        String option1 = scanner.nextLine();
        System.out.print("Option 2: ");
        String option2 = scanner.nextLine();
        System.out.print("Option 3: ");
        String option3 = scanner.nextLine();
        System.out.print("Option 4: ");
        String option4 = scanner.nextLine();
        System.out.print("Correct option (1-4): ");
        int correctOption = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        boolean success = questionManager.addQuestion(questionText, option1, option2, option3, option4, correctOption, currentUser.getUserId());
        System.out.println(success ? "Question added successfully!" : "Failed to add question.");
    }

    private static void updateQuestion(Scanner scanner) {
        System.out.print("Enter question ID to update: ");
        int questionId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter new question text: ");
        String questionText = scanner.nextLine();
        System.out.print("New Option 1: ");
        String option1 = scanner.nextLine();
        System.out.print("New Option 2: ");
        String option2 = scanner.nextLine();
        System.out.print("New Option 3: ");
        String option3 = scanner.nextLine();
        System.out.print("New Option 4: ");
        String option4 = scanner.nextLine();
        System.out.print("Correct option (1-4): ");
        int correctOption = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        boolean success = questionManager.updateQuestion(questionId, questionText, option1, option2, option3, option4, correctOption);
        System.out.println(success ? "Question updated successfully!" : "Failed to update question.");
    }

    private static void deleteQuestion(Scanner scanner) {
        System.out.print("Enter question ID to delete: ");
        int questionId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        boolean success = questionManager.deleteQuestion(questionId);
        System.out.println(success ? "Question deleted successfully!" : "Failed to delete question.");
    }

    private static void viewAllQuestions() {
        System.out.println("All Questions:");
        for (String question : questionManager.getAllQuestions()) {
            System.out.println(question);
        }
    }
}


//this code working fine except post quiz activity
/*

import java.util.Scanner;

public class QuizApp {
    private static UserManager userManager = new UserManager();
    private static QuestionManager questionManager = new QuestionManager();
    private static QuizManager quizManager = new QuizManager();
    private static User currentUser;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nWelcome to the Quiz Application!");
            System.out.println("1. Sign Up");
            System.out.println("2. Log In");
            System.out.println("3. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    signUp(scanner);
                    break;
                case 2:
                    logIn(scanner);
                    if (currentUser != null) {
                        if (currentUser.getRole().equalsIgnoreCase("admin")) {
                            adminMenu(scanner);
                        } else {
                            userMenu(scanner);
                        }
                    }
                    break;
                case 3:
                    System.out.println("Exiting the application...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Sign up method
    private static void signUp(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter role (admin/user): ");
        String role = scanner.nextLine();

        boolean success = userManager.signUp(username, password, role);
        if (success) {
            System.out.println("Sign up successful!");
        } else {
            System.out.println("Sign up failed. Please try again.");
        }
    }

    // Log in method
    private static void logIn(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        currentUser = userManager.logIn(username, password);
        if (currentUser == null) {
            System.out.println("Login failed. Please try again.");
        } else {
            System.out.println("Login successful! Welcome, " + currentUser.getUsername());
        }
    }

    // Admin menu for managing quiz questions
    private static void adminMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Add Question");
            System.out.println("2. Update Question");
            System.out.println("3. Delete Question");
            System.out.println("4. View All Questions");
            System.out.println("5. Log Out");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addQuestion(scanner);
                    break;
                case 2:
                    updateQuestion(scanner);
                    break;
                case 3:
                    deleteQuestion(scanner);
                    break;
                case 4:
                    viewAllQuestions();
                    break;
                case 5:
                    System.out.println("Logging out...");
                    currentUser = null;
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // User menu for taking the quiz
    private static void userMenu(Scanner scanner) {
        System.out.println("\nWelcome to the Quiz Section!");

        System.out.print("Enter the number of questions for the quiz: ");
        int numQuestions = scanner.nextInt();
        System.out.print("Enter the time limit in seconds: ");
        int timeLimit = scanner.nextInt();

        quizManager.loadQuestions(numQuestions);
        quizManager.startQuiz(timeLimit);

        System.out.println("Logging out...");
        currentUser = null;
    }

    // Admin functions for question management
    private static void addQuestion(Scanner scanner) {
        System.out.print("Enter question text: ");
        String questionText = scanner.nextLine();
        System.out.print("Option 1: ");
        String option1 = scanner.nextLine();
        System.out.print("Option 2: ");
        String option2 = scanner.nextLine();
        System.out.print("Option 3: ");
        String option3 = scanner.nextLine();
        System.out.print("Option 4: ");
        String option4 = scanner.nextLine();
        System.out.print("Correct option (1-4): ");
        int correctOption = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        boolean success = questionManager.addQuestion(questionText, option1, option2, option3, option4, correctOption, currentUser.getUserId());
        System.out.println(success ? "Question added successfully!" : "Failed to add question.");
    }

    private static void updateQuestion(Scanner scanner) {
        System.out.print("Enter question ID to update: ");
        int questionId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter new question text: ");
        String questionText = scanner.nextLine();
        System.out.print("New Option 1: ");
        String option1 = scanner.nextLine();
        System.out.print("New Option 2: ");
        String option2 = scanner.nextLine();
        System.out.print("New Option 3: ");
        String option3 = scanner.nextLine();
        System.out.print("New Option 4: ");
        String option4 = scanner.nextLine();
        System.out.print("Correct option (1-4): ");
        int correctOption = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        boolean success = questionManager.updateQuestion(questionId, questionText, option1, option2, option3, option4, correctOption);
        System.out.println(success ? "Question updated successfully!" : "Failed to update question.");
    }

    private static void deleteQuestion(Scanner scanner) {
        System.out.print("Enter question ID to delete: ");
        int questionId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        boolean success = questionManager.deleteQuestion(questionId);
        System.out.println(success ? "Question deleted successfully!" : "Failed to delete question.");
    }

    private static void viewAllQuestions() {
        System.out.println("All Questions:");
        for (String question : questionManager.getAllQuestions()) {
            System.out.println(question);
        }
    }
}
*/