import java.util.Scanner;

public class QuizDemo {
    private static QuizManager quizManager = new QuizManager();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of questions for the quiz: ");
        int numQuestions = scanner.nextInt();

        System.out.print("Enter the time limit in seconds: ");
        int timeLimit = scanner.nextInt();

        quizManager.loadQuestions(numQuestions);
        quizManager.startQuiz(timeLimit);

        scanner.close();
    }
}

