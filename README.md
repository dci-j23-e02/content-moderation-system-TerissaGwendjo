# Assignment: Developing a Flexible Content Moderation System

## Assignment Overview

In this assignment, you will build a content moderation system for a social media platform or forum. This system will allow administrators to define custom rules for automatically moderating posts or comments based on content, user reputation, and other factors. You will use Java's functional programming features, specifically `Predicate`, `UnaryOperator`, and `BinaryOperator`, to implement the moderation rules and content processing logic.

### Part 1: Defining Moderation Rules with Predicates

#### Objective

Use `Predicate` and `BiPredicate` to define complex moderation rules that evaluate whether a post should be flagged, deleted, or approved. These rules could check for prohibited keywords, assess user reputation scores, or evaluate other criteria.

#### Task Breakdown

1. **Prohibited Keywords Checker**:
   - Implement a `Predicate<String>` that checks if a post contains any prohibited keywords.

2. **User Reputation Checker**:
   - Implement a `BiPredicate<String, Integer>` that evaluates a post based on its content and the user's reputation score. For example, it could flag posts from users with low reputation scores.

#### Code Snippet Example

```java
import java.util.function.Predicate;
import java.util.function.BiPredicate;

public class ModerationRules {
    public static void main(String[] args) {
        Predicate<String> containsProhibitedKeywords = content -> content.matches(".*(badword1|badword2).*");
        BiPredicate<String, Integer> isLowReputationPost = (content, reputation) -> reputation < 50;

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
```

### Part 2: Automatically Modifying Content with Operators

#### Objective

Implement `UnaryOperator` and `BinaryOperator` to automatically modify or redact content that violates certain rules, such as obscuring profanity or removing personal information.

#### Task Breakdown

1. **Content Redaction**:
   - Create a `UnaryOperator<String>` that replaces prohibited keywords in a post's content with a redaction marker (e.g., "****").

2. **Content Scoring and Decision Making**:
   - Use a `BinaryOperator<Integer>` to combine scores from different rule evaluations and make a final decision on whether to approve, flag, or delete a post.

#### Code Snippet Example

```java
import java.util.function.UnaryOperator;
import java.util.function.BinaryOperator;

public class ContentProcessing {
    public static void main(String[] args) {
        UnaryOperator<String> redactProhibitedKeywords = content -> content.replaceAll("(badword1|badword2)", "****");
        BinaryOperator<Integer> combineScores = Integer::sum;

        // Example usage
        String postContent = "This post contains badword1.";
        int scoreForKeywords = -50;
        int userReputationScore = 45;
        int finalScore = combineScores.apply(scoreForKeywords, userReputationScore);

        postContent = redactProhibitedKeywords.apply(postContent);
        System.out.println("Processed Post: " + postContent);
        System.out.println("Final Score: " + finalScore);
        // Based on the final score, decide whether to approve, flag, or delete the post
    }
}
```

### Submission Guidelines

- Ensure your code is well-commented, explaining the logic behind each moderation rule and content processing operation.
- Test your moderation system with various scenarios to demonstrate its effectiveness in filtering and processing content based on the defined rules.
- Submit your source code, clearly indicating both parts of the assignment.

This assignment challenges you to apply functional programming concepts to a real-world problem, enhancing your ability to design and implement complex systems in Java. Good luck!
