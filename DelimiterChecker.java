
import java.util.Scanner;
import java.util.Stack;

public class DelimiterChecker {

    public static boolean isOpening(char ch) {
        return ch == '(' || ch == '[' || ch == '{';
    }

    public static boolean isClosing(char ch) {
        return ch == ')' || ch == ']' || ch == '}';
    }

    public static boolean isMatching(char open, char close) {
        return (open == '(' && close == ')')
            || (open == '[' && close == ']')
            || (open == '{' && close == '}');
    }

    public static boolean checkDelimiters(String input) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            // Ignore characters that are not delimiters.
            if (!isOpening(ch) && !isClosing(ch)) {
                continue;
            }

            System.out.println("\nRead: " + ch);

            if (isOpening(ch)) {
                stack.push(ch);
                System.out.println("Operation: PUSH " + ch);
                System.out.println("Stack: " + stack);
            } else {
                if (stack.isEmpty()) {
                    System.out.println(
                        "Invalid: Closing delimiter has no matching opening delimiter."
                    );
                    System.out.println("Stack: " + stack);
                    return false;
                }

                char top = stack.peek();
                System.out.println("Operation: PEEK -> " + top);

                if (!isMatching(top, ch)) {
                    System.out.println("Invalid: Mismatched delimiters.");
                    System.out.println("Expected closing delimiter for "
                        + top + ", but found " + ch + ".");
                    System.out.println("Stack: " + stack);
                    return false;
                }

                stack.pop();
                System.out.println("Operation: POP " + top);
                System.out.println("Stack: " + stack);
            }
        }

        if (!stack.isEmpty()) {
            System.out.println(
                "\nInvalid: Unmatched opening delimiter remains."
            );
            System.out.println("Remaining stack: " + stack);
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a delimiter sequence: ");
        String input = scanner.nextLine();

        boolean valid = checkDelimiters(input);

        System.out.println("\nResult: "
            + (valid ? "Valid" : "Invalid"));

        scanner.close();
    }
}
