package part1;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

import static part1.ModerationRules.*;

public class Main {

    public static void main(String[] args) {
        // Example usage

        //  creates a List of String objects named prohibitedKeywords.
        List<String> prohibitedKeywords = Arrays.asList("spam", "illegal", "inappropriate");

        //Predicate<String> named keywordChecker is declared and initialized.
        //The prohibitedKeywordsChecker method is called with prohibitedKeywords as an argument.
        Predicate<String> keywordChecker = prohibitedKeywordsChecker(prohibitedKeywords);

        //This line declares and initializes a BiPredicate<String, Integer> named reputationChecker
        //The userReputationChecker method is called with an argument 100, which represents the low reputation threshold.
        BiPredicate<String, Integer> reputationChecker = userReputationChecker(100);

        //declares and initializes a String array named redactedKeywords.
        String[] redactedKeywords = {"profanity", "personal info", "inappropriate"};
        UnaryOperator<String> redactionOperator = contentRedactionOperator(redactedKeywords); // Declaration of redaction operator

        //declares and initializes a BiPredicate<Integer, Integer> named scoringOperator
        //The contentScoringOperator method is called with 100 and 50 as arguments, representing high and low scoring thresholds.
        BiPredicate<Integer, Integer> scoringOperator = contentScoringOperator(100, 50);

        // Example post and user reputation score
        String post = "This is a spam post";
        int userReputationScore = 90;

        // Check if the post should be flagged or deleted based on moderation rules
        if (keywordChecker.test(post)) {
            System.out.println("Post contains prohibited keywords. Flagged for moderation.");
        } else if (reputationChecker.test(post, userReputationScore)) {
            System.out.println("User has low reputation. Post flagged for moderation.");
        } else {
            System.out.println("Post approved.");
        }

        // Apply content redaction
        String redactedContent = redactionOperator.apply(post);
        System.out.println("Redacted Content: " + redactedContent);

        // Combine scores and make a decision
        boolean decision = scoringOperator.test(80, 60);
        if (decision) {
            System.out.println("Post approved.");
        } else {
            System.out.println("Post flagged for moderation or deleted.");
        }
        // Will return post approved since we are passing 80 and 60 as scores. The decision whether to approve the post
        // or flag it for moderation is determined by the scoringOperator predicate which calls contentScoringOperator
        // The contentScoringOperator method is designed to return true if either of the scores is greater than or equal
        // to the high threshold (100) or if either of the scores is greater than or equal to the low threshold (50).

    }

}
