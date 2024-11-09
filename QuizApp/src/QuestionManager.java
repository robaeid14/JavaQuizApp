import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QuestionManager {

    // Method to add a new question to the database
    public boolean addQuestion(String questionText, String option1, String option2, String option3, String option4, int correctOption, int createdBy) {
        String sql = "INSERT INTO questions (question_text, option_1, option_2, option_3, option_4, correct_option, created_by) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, questionText);
            stmt.setString(2, option1);
            stmt.setString(3, option2);
            stmt.setString(4, option3);
            stmt.setString(5, option4);
            stmt.setInt(6, correctOption);
            stmt.setInt(7, createdBy);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to update an existing question
    public boolean updateQuestion(int questionId, String questionText, String option1, String option2, String option3, String option4, int correctOption) {
        String sql = "UPDATE questions SET question_text = ?, option_1 = ?, option_2 = ?, option_3 = ?, option_4 = ?, correct_option = ? WHERE question_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, questionText);
            stmt.setString(2, option1);
            stmt.setString(3, option2);
            stmt.setString(4, option3);
            stmt.setString(5, option4);
            stmt.setInt(6, correctOption);
            stmt.setInt(7, questionId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to delete a question
    public boolean deleteQuestion(int questionId) {
        String sql = "DELETE FROM questions WHERE question_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, questionId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to retrieve all questions (for viewing purposes)
    public List<String> getAllQuestions() {
        String sql = "SELECT * FROM questions";
        List<String> questionsList = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String question = rs.getInt("question_id") + ": " + rs.getString("question_text");
                questionsList.add(question);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return questionsList;
    }
}
