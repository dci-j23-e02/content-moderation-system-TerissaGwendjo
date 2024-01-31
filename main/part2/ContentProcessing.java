package part2;
import java.util.function.UnaryOperator;
import java.util.function.BinaryOperator;

public class ContentProcessing {
    public static void main(String[] args) {
        // UnaryOperator to redact prohibited keywords
        UnaryOperator<String> redactProhibitedKeywords = content ->
                content.replaceAll("(badword1|badword2|badword3)", "****");

        // BinaryOperator to combine scores and make a final decision
        BinaryOperator<Integer> combineScores = (score1, score2) -> {
            int finalScore = score1 + score2;
            if (finalScore < -100) {
                System.out.println("Post deleted due to low score.");
            } else if (finalScore < 0) {
                System.out.println("Post flagged for review.");
            } else {
                System.out.println("Post approved.");
            }
            return finalScore;
        };

        // Example usage
        String postContent = "This post contains badword1.";
        int scoreForKeywords = -50; // Score based on detected prohibited keywords
        int userReputationScore = 45; // User reputation score
        int finalScore = combineScores.apply(scoreForKeywords, userReputationScore);

        postContent = redactProhibitedKeywords.apply(postContent);
        System.out.println("Processed Post: " + postContent);
        System.out.println("Final Score: " + finalScore);
        // Based on the final score, decide whether to approve, flag, or delete the post
    }
}
