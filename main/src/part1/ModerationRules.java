package part1;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.BiPredicate;
import java.util.function.UnaryOperator;

public class ModerationRules {

    // Prohibited Keywords Checker
    // This method returns a Predicate<String> that checks if a post contains any of the prohibited keywords specified in the list.
    public static Predicate<String> prohibitedKeywordsChecker(List<String> prohibitedKeywords) {
        return post -> {
            // Iterate through each prohibited keyword
            for (String keyword : prohibitedKeywords) {
                if (post.contains(keyword)) {
                    return true; // Post contains a prohibited keyword
                }
            }
            return false; // Post does not contain any prohibited keyword
        };
    }

    // User Reputation Checker
    // This method returns a BiPredicate<String, Integer> that evaluates whether a post from a user with a given reputation score should be flagged.

    public static BiPredicate<String, Integer> userReputationChecker(int lowReputationThreshold) {
        return (post, userReputationScore) -> userReputationScore < lowReputationThreshold;
    }


    // Content Redaction Operator
    public static UnaryOperator<String> contentRedactionOperator(String[] prohibitedKeywords) {
        return content -> {
            // Iterate through each prohibited keyword
            for (String keyword : prohibitedKeywords) {
                // Replace the keyword with asterisks (redaction)
                content = content.replaceAll("(?i)" + keyword, "****"); // Case-insensitive replacement
                //The "****" is used as a placeholder or mask to obscure or censor sensitive or inappropriate words or phrases in the text.
            }
            return content;
        };
    }

    // Content Scoring Operator
    public static BiPredicate<Integer, Integer> contentScoringOperator(int highThreshold, int lowThreshold) {
        return (score1, score2) -> {
            // Combine scores from different evaluations and make a final decision
            if (score1 >= highThreshold || score2 >= highThreshold) {
                return true; // Approve post
            } else if (score1 >= lowThreshold || score2 >= lowThreshold) {
                return true; // Flag post for moderation
            } else {
                return false; // Delete post
            }
        };
    }

}
