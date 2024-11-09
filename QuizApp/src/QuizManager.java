import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuizManager {
    private List<Question> questions = new ArrayList<>();
    private int score = 0;

    // Load a specified number of random questions from the database
    public List<Question> loadQuestions(int numQuestions) {
    List<Question> loadedQuestions = new ArrayList<>();  // Create a new list to store questions
    String sql = "SELECT * FROM questions ORDER BY RAND() LIMIT ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, numQuestions);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("question_id");
            String questionText = rs.getString("question_text");
            String option1 = rs.getString("option_1");
            String option2 = rs.getString("option_2");
            String option3 = rs.getString("option_3");
            String option4 = rs.getString("option_4");
            int correctOption = rs.getInt("correct_option");

            // Add the loaded question to the list
            loadedQuestions.add(new Question(id, questionText, option1, option2, option3, option4, correctOption));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return loadedQuestions;  // Return the list of loaded questions
}

    // Method to start the quiz, display questions, and handle answers

    public void startQuiz(int timeLimit) {
        Scanner scanner = new Scanner(System.in);
        long endTime = System.currentTimeMillis() + timeLimit * 1000; // Convert seconds to milliseconds

        for (Question question : questions) {
            if (System.currentTimeMillis() > endTime) {
                System.out.println("Time's up!");
                break;
            }

            System.out.println("\n" + question.getQuestionText());
            System.out.println("1. " + question.getOption1());
            System.out.println("2. " + question.getOption2());
            System.out.println("3. " + question.getOption3());
            System.out.println("4. " + question.getOption4());

            System.out.print("Your answer (1-4): ");
            int userAnswer = scanner.nextInt();

            if (userAnswer == question.getCorrectOption()) {
                score++;
            }
        }

        System.out.println("Quiz ended!");
        System.out.println("Your score: " + score + " out of " + questions.size());

        scanner.close();
    }

}
