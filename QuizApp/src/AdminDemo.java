import java.util.Scanner;

public class AdminDemo {
    private static QuestionManager questionManager = new QuestionManager();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Add Question");
            System.out.println("2. Update Question");
            System.out.println("3. Delete Question");
            System.out.println("4. View All Questions");
            System.out.println("5. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
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
                    int createdBy = 1; // Assume admin user ID is 1 for this example

                    boolean added = questionManager.addQuestion(questionText, option1, option2, option3, option4, correctOption, createdBy);
                    System.out.println(added ? "Question added successfully!" : "Failed to add question.");
                    break;

                case 2:
                    System.out.print("Enter question ID to update: ");
                    int questionId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter new question text: ");
                    questionText = scanner.nextLine();
                    System.out.print("New Option 1: ");
                    option1 = scanner.nextLine();
                    System.out.print("New Option 2: ");
                    option2 = scanner.nextLine();
                    System.out.print("New Option 3: ");
                    option3 = scanner.nextLine();
                    System.out.print("New Option 4: ");
                    option4 = scanner.nextLine();
                    System.out.print("Correct option (1-4): ");
                    correctOption = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    boolean updated = questionManager.updateQuestion(questionId, questionText, option1, option2, option3, option4, correctOption);
                    System.out.println(updated ? "Question updated successfully!" : "Failed to update question.");
                    break;

                case 3:
                    System.out.print("Enter question ID to delete: ");
                    questionId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    boolean deleted = questionManager.deleteQuestion(questionId);
                    System.out.println(deleted ? "Question deleted successfully!" : "Failed to delete question.");
                    break;

                case 4:
                    System.out.println("All Questions:");
                    for (String question : questionManager.getAllQuestions()) {
                        System.out.println(question);
                    }
                    break;

                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
