import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
/*Código generado*/

public class InfixToPostfix {
    static int precedence(char c) {
        switch(c) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

    static String infixToPostfix(String expression) {
        StringBuilder result = new StringBuilder();
        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (Character.isLetterOrDigit(c)) {
                result.append(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop());
                }
                if (!stack.isEmpty() && stack.peek() != '(') {
                    return "Invalid expression";
                } else {
                    stack.pop();
                }
            } else {
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    if (stack.peek() == '(') {
                        return "Invalid expression";
                    }
                    result.append(stack.pop());
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            if (stack.peek() == '(') {
                return "Invalid expression";
            }
            result.append(stack.pop());
        }

        return result.toString();
    }
    public String error_de_espacios(String exsp){
        String expressionWithoutSpaces = exsp.replaceAll("\\s", "");
        return expressionWithoutSpaces;
    }

    public ArrayList traslate (ArrayList<String> ar){
        ArrayList array_de_PosFix = new ArrayList<>();
        for (String expression:ar) {
            String error= error_de_espacios(expression);
            String postfix = infixToPostfix(error);
            array_de_PosFix.add(postfix);
        }
        return array_de_PosFix;
    }
}
