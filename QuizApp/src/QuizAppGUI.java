import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.ArrayList;

import java.util.List;

public class QuizAppGUI extends Application {
    private UserManager userManager = new UserManager();
    private QuestionManager questionManager = new QuestionManager();
    private QuizManager quizManager = new QuizManager();
    private User currentUser;
    private Stage primaryStage;
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Quiz Application");

        showMainMenu();
    }

    // Main Menu Scene
    private void showMainMenu() {
        VBox mainMenuLayout = new VBox(10);
        mainMenuLayout.setPadding(new Insets(20));
        
        Label welcomeLabel = new Label("Welcome to the Quiz Application!");
        Button signUpButton = new Button("Sign Up");
        Button logInButton = new Button("Log In");
        Button exitButton = new Button("Exit");
        
        signUpButton.setOnAction(e -> showSignUpScene());
        logInButton.setOnAction(e -> showLogInScene());
        exitButton.setOnAction(e -> primaryStage.close());

        mainMenuLayout.getChildren().addAll(welcomeLabel, signUpButton, logInButton, exitButton);

        Scene mainMenuScene = new Scene(mainMenuLayout, 300, 200);
        primaryStage.setScene(mainMenuScene);
        primaryStage.show();
    }

    // Sign-Up Scene
    private void showSignUpScene() {
        VBox signUpLayout = new VBox(10);
        signUpLayout.setPadding(new Insets(20));

        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        TextField roleField = new TextField();
        
        usernameField.setPromptText("Username");
        passwordField.setPromptText("Password");
        roleField.setPromptText("Role (admin/user)");

        Button signUpButton = new Button("Sign Up");
        signUpButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String role = roleField.getText();

            boolean success = userManager.signUp(username, password, role);
            if (success) {
                showAlert("Sign-Up", "Sign up successful!", Alert.AlertType.INFORMATION);
                showMainMenu();
            } else {
                showAlert("Sign-Up", "Sign up failed. Please try again.", Alert.AlertType.ERROR);
            }
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> showMainMenu());

        signUpLayout.getChildren().addAll(new Label("Sign Up"), usernameField, passwordField, roleField, signUpButton, backButton);

        Scene signUpScene = new Scene(signUpLayout, 300, 300);
        primaryStage.setScene(signUpScene);
    }

    // Log-In Scene
    private void showLogInScene() {
        VBox logInLayout = new VBox(10);
        logInLayout.setPadding(new Insets(20));

        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        
        usernameField.setPromptText("Username");
        passwordField.setPromptText("Password");

        Button logInButton = new Button("Log In");
        logInButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            currentUser = userManager.logIn(username, password);
            if (currentUser == null) {
                showAlert("Log-In", "Login failed. Please try again.", Alert.AlertType.ERROR);
            } else {
                showAlert("Log-In", "Login successful! Welcome, " + currentUser.getUsername(), Alert.AlertType.INFORMATION);
                if (currentUser.getRole().equalsIgnoreCase("admin")) {
                    showAdminMenu();
                } else {
                    showUserMenu();
                }
            }
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> showMainMenu());

        logInLayout.getChildren().addAll(new Label("Log In"), usernameField, passwordField, logInButton, backButton);

        Scene logInScene = new Scene(logInLayout, 300, 250);
        primaryStage.setScene(logInScene);
    }

    // Admin Menu Scene
    private void showAdminMenu() {
        VBox adminLayout = new VBox(10);
        adminLayout.setPadding(new Insets(20));

        Button addQuestionButton = new Button("Add Question");
        Button updateQuestionButton = new Button("Update Question");
        Button deleteQuestionButton = new Button("Delete Question");
        Button viewQuestionsButton = new Button("View All Questions");
        Button logOutButton = new Button("Log Out");

        addQuestionButton.setOnAction(e -> showAddQuestionScene());
        updateQuestionButton.setOnAction(e -> showUpdateQuestionScene());
        deleteQuestionButton.setOnAction(e -> showDeleteQuestionScene());
        viewQuestionsButton.setOnAction(e -> showAllQuestions());
        logOutButton.setOnAction(e -> {
            currentUser = null;
            showMainMenu();
        });

        adminLayout.getChildren().addAll(new Label("Admin Menu"), addQuestionButton, updateQuestionButton, deleteQuestionButton, viewQuestionsButton, logOutButton);

        Scene adminScene = new Scene(adminLayout, 300, 350);
        primaryStage.setScene(adminScene);
    }

    // Add Question Scene
    private void showAddQuestionScene() {
        VBox addQuestionLayout = new VBox(10);
        addQuestionLayout.setPadding(new Insets(20));

        TextField questionTextField = new TextField();
        TextField option1Field = new TextField();
        TextField option2Field = new TextField();
        TextField option3Field = new TextField();
        TextField option4Field = new TextField();
        TextField correctOptionField = new TextField();
        
        questionTextField.setPromptText("Question Text");
        option1Field.setPromptText("Option 1");
        option2Field.setPromptText("Option 2");
        option3Field.setPromptText("Option 3");
        option4Field.setPromptText("Option 4");
        correctOptionField.setPromptText("Correct Option (1-4)");

        Button addButton = new Button("Add Question");
        addButton.setOnAction(e -> {
            String questionText = questionTextField.getText();
            String option1 = option1Field.getText();
            String option2 = option2Field.getText();
            String option3 = option3Field.getText();
            String option4 = option4Field.getText();
            int correctOption = Integer.parseInt(correctOptionField.getText());

            boolean success = questionManager.addQuestion(questionText, option1, option2, option3, option4, correctOption, currentUser.getUserId());
            showAlert("Add Question", success ? "Question added successfully!" : "Failed to add question.", success ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);

            showAdminMenu();
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> showAdminMenu());

        addQuestionLayout.getChildren().addAll(new Label("Add New Question"), questionTextField, option1Field, option2Field, option3Field, option4Field, correctOptionField, addButton, backButton);

        Scene addQuestionScene = new Scene(addQuestionLayout, 350, 400);
        primaryStage.setScene(addQuestionScene);
    }

    // Update Question Scene
    private void showUpdateQuestionScene() {
        VBox updateQuestionLayout = new VBox(10);
        updateQuestionLayout.setPadding(new Insets(20));

        TextField questionIdField = new TextField();
        TextField questionTextField = new TextField();
        TextField option1Field = new TextField();
        TextField option2Field = new TextField();
        TextField option3Field = new TextField();
        TextField option4Field = new TextField();
        TextField correctOptionField = new TextField();
        
        questionIdField.setPromptText("Question ID");
        questionTextField.setPromptText("New Question Text");
        option1Field.setPromptText("New Option 1");
        option2Field.setPromptText("New Option 2");
        option3Field.setPromptText("New Option 3");
        option4Field.setPromptText("New Option 4");
        correctOptionField.setPromptText("Correct Option (1-4)");

        Button updateButton = new Button("Update Question");
        updateButton.setOnAction(e -> {
            int questionId = Integer.parseInt(questionIdField.getText());
            String questionText = questionTextField.getText();
            String option1 = option1Field.getText();
            String option2 = option2Field.getText();
            String option3 = option3Field.getText();
            String option4 = option4Field.getText();
            int correctOption = Integer.parseInt(correctOptionField.getText());

            boolean success = questionManager.updateQuestion(questionId, questionText, option1, option2, option3, option4, correctOption);
            showAlert("Update Question", success ? "Question updated successfully!" : "Failed to update question.", success ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);

            showAdminMenu();
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> showAdminMenu());

        updateQuestionLayout.getChildren().addAll(new Label("Update Question"), questionIdField, questionTextField, option1Field, option2Field, option3Field, option4Field, correctOptionField, updateButton, backButton);

        Scene updateQuestionScene = new Scene(updateQuestionLayout, 350, 450);
        primaryStage.setScene(updateQuestionScene);
    }

    // Delete Question Scene
    private void showDeleteQuestionScene() {
        VBox deleteQuestionLayout = new VBox(10);
        deleteQuestionLayout.setPadding(new Insets(20));

        TextField questionIdField = new TextField();
        questionIdField.setPromptText("Question ID");

        Button deleteButton = new Button("Delete Question");
        deleteButton.setOnAction(e -> {
            int questionId = Integer.parseInt(questionIdField.getText());
            boolean success = questionManager.deleteQuestion(questionId);
            showAlert("Delete Question", success ? "Question deleted successfully!" : "Failed to delete question.", success ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);

            showAdminMenu();
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> showAdminMenu());

        deleteQuestionLayout.getChildren().addAll(new Label("Delete Question"), questionIdField, deleteButton, backButton);

        Scene deleteQuestionScene = new Scene(deleteQuestionLayout, 300, 250);
        primaryStage.setScene(deleteQuestionScene);
    }

    // View All Questions (Admin) Scene
    private void showAllQuestions() {
    List<String> allQuestions = questionManager.getAllQuestions();

    VBox allQuestionsLayout = new VBox(10);
    allQuestionsLayout.setPadding(new Insets(20));

    // Add all questions as labels
    allQuestions.forEach(question -> allQuestionsLayout.getChildren().add(new Label(question)));

    Button backButton = new Button("Back");
    backButton.setOnAction(e -> showAdminMenu());
    allQuestionsLayout.getChildren().add(backButton);

    // Wrap the VBox layout in a ScrollPane to enable scrolling
    ScrollPane scrollPane = new ScrollPane(allQuestionsLayout);
    scrollPane.setFitToWidth(true);

    Scene allQuestionsScene = new Scene(scrollPane, 400, 400);
    primaryStage.setScene(allQuestionsScene);
}


    // User Menu (Quiz) Scene
  
private void showUserMenu() {
    VBox userLayout = new VBox(10);
    userLayout.setPadding(new Insets(20));

    Label label = new Label("Welcome to the Quiz!");

    // Add field for entering the number of questions
    Label numberOfQuestionsLabel = new Label("Enter number of questions:");
    TextField numberOfQuestionsField = new TextField();
    numberOfQuestionsField.setPromptText("e.g., 5");

    Button startQuizButton = new Button("Start Quiz");
    startQuizButton.setOnAction(e -> {
        try {
            int numQuestions = Integer.parseInt(numberOfQuestionsField.getText());
            if (numQuestions > 0) {
                showQuizScene(numQuestions);  // Pass number of questions to the quiz scene
            } else {
                showAlert("Invalid Input", "Please enter a valid positive number for questions.", Alert.AlertType.ERROR);
            }
        } catch (NumberFormatException ex) {
            showAlert("Invalid Input", "Please enter a valid number.", Alert.AlertType.ERROR);
        }
    });

    userLayout.getChildren().addAll(label, numberOfQuestionsLabel, numberOfQuestionsField, startQuizButton);

    Scene userScene = new Scene(userLayout, 300, 250);
    primaryStage.setScene(userScene);
}


// Quiz Scene (for User)

private void showQuizScene(int numQuestions) {
    VBox quizContentLayout = new VBox(10);
    quizContentLayout.setPadding(new Insets(20));

    List<Question> questions = quizManager.loadQuestions(numQuestions);
    List<Integer> userAnswers = new ArrayList<>();

    for (Question question : questions) {
        Label questionLabel = new Label(question.getQuestionText());
        RadioButton option1 = new RadioButton(question.getOption1());
        RadioButton option2 = new RadioButton(question.getOption2());
        RadioButton option3 = new RadioButton(question.getOption3());
        RadioButton option4 = new RadioButton(question.getOption4());

        ToggleGroup group = new ToggleGroup();
        option1.setToggleGroup(group);
        option2.setToggleGroup(group);
        option3.setToggleGroup(group);
        option4.setToggleGroup(group);

        group.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
            if (newToggle != null) {
                RadioButton selectedOption = (RadioButton) newToggle;
                userAnswers.add(group.getToggles().indexOf(newToggle) + 1);
            }
        });

        VBox options = new VBox(5, option1, option2, option3, option4);
        quizContentLayout.getChildren().addAll(questionLabel, options);
    }

    Button submitButton = new Button("Submit Quiz");
    submitButton.setOnAction(event -> {
        int score = 0;
        for (int i = 0; i < questions.size(); i++) {
            if (i < userAnswers.size() && userAnswers.get(i) == questions.get(i).getCorrectOption()) {
                score++;
            }
        }
        showAlert("Quiz Finished", "You scored " + score + " out of " + questions.size(), Alert.AlertType.INFORMATION);
    });
    quizContentLayout.getChildren().add(submitButton);

    // Add a ScrollPane
    ScrollPane scrollPane = new ScrollPane(quizContentLayout);
    scrollPane.setFitToWidth(true);

    Scene quizScene = new Scene(scrollPane, 400, 500);
    primaryStage.setScene(quizScene);
}


// Show Alerts for messages
private void showAlert(String title, String message, Alert.AlertType alertType) {
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}

}

