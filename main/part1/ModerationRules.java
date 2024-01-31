package part1;
import java.util.function.Predicate;
import java.util.function.BiPredicate;

public class ModerationRules {
    public static void main(String[] args) {
        // Predicate to check for prohibited keywords
        Predicate<String> containsProhibitedKeywords = content ->
                content.matches(".*(badword1|badword2|badword3).*");

        // BiPredicate to evaluate low reputation posts
        BiPredicate<String, Integer> isLowReputationPost = (content, reputation) ->
                reputation < 50 && content.length() > 20; // Example: Post must be longer than 20 characters for evaluation

        // Example usage
        String postContent = "This is a clean post.";
        int userReputation = 45;

        if (containsProhibitedKeywords.test(postContent)) {
            System.out.println("Post contains prohibited keywords.");
        } else if (isLowReputationPost.test(postContent, userReputation)) {
            System.out.println("Post flagged for low user reputation.");
        } else {
            System.out.println("Post approved.");
        }
    }
}
