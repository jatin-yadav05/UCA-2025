import java.util.*;

public class InfixToPostfix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCases = Integer.parseInt(sc.nextLine().trim());

        for (int t = 0; t < testCases; t++) {
            if (sc.hasNextLine()) sc.nextLine();

            List<String> expression = new ArrayList<>();
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) break;
                expression.add(line);
            }

            String postfix = convertToPostfix(expression);
            System.out.println(postfix);

            if (t != testCases - 1) {
                System.out.println();
            }
        }
    }

    public static String convertToPostfix(List<String> expr) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (String token : expr) {
            char ch = token.charAt(0);

            if (Character.isDigit(ch)) {
                result.append(ch);
            } else if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop());
                }
                stack.pop();
            } else if (isOperator(ch)) {
                while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(ch)) {
                    result.append(stack.pop());
                }
                stack.push(ch);
            }
        }

        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        return result.toString();
    }

    public static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    public static int precedence(char ch) {
        if (ch == '*' || ch == '/') return 2;
        if (ch == '+' || ch == '-') return 1;
        return 0;
    }
}

