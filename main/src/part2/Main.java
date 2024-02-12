package part2;

import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

import static part2.ContentProcessing.contentRedactionOperator;
import static part2.ContentProcessing.contentScoringOperator;

public class Main {

    public static void main(String[] args) {
        // Example usage:
        // we demonstrate how to use these operators to redact content and make a decision based on the scores obtained from the different moderation rules.
        String[] prohibitedKeywords = {"profanity", "personal info", "inappropriate"};
        UnaryOperator<String> redactionOperator = contentRedactionOperator(prohibitedKeywords);

        BinaryOperator<Integer> scoringOperator = contentScoringOperator();

        // Example post content and rule scores
        String postContent = "This is an inappropriate post with profanity.";

        int keywordScore = 80; // Score based on keyword analysis
        int userScore = 60; // Score based on user reputation

        // Apply content redaction
        String redactedContent = redactionOperator.apply(postContent);
        System.out.println("Redacted Content: " + redactedContent);

        // Combine scores and make a decision
        int decision = scoringOperator.apply(keywordScore, userScore);
        switch (decision) {
            case 1:
                System.out.println("Post approved.");
                break;
            case 2:
                System.out.println("Post flagged for moderation.");
                break;
            case 3:
                System.out.println("Post deleted.");
                break;
            default:
                System.out.println("Invalid decision.");
        }
    }

}
