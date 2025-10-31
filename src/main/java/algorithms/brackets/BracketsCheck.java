package algorithms.brackets;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class BracketsCheck {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите уравнение: ");
            String equation = scanner.nextLine();
            bracketsCheck(equation);
        }
    }

    private static void bracketsCheck(String equation) {
        Deque<String> stack = new ArrayDeque<>();
        String[] array = equation.split("");
        for (String s : array) {
            if (s.equals("(") ||
                    s.equals("[") ||
                    s.equals("{")) {
                stack.push(s);
            } else if (s.equals(")") ||
                    s.equals("]") ||
                    s.equals("}")) {
                if (!stack.isEmpty()) {
                    if ((stack.peek().equals("(") && s.equals(")")) ||
                            (stack.peek().equals("[") && s.equals("]")) ||
                            (stack.peek().equals("{") && s.equals("}"))) {
                        stack.pop();
                    } else {
                        System.out.println("Неверно расставлены скобки!!!");
                        return;
                    }
                } else {
                    System.out.println("Неверно расставлены скобки!!!");
                    return;
                }
            }
        }

        if (stack.isEmpty()) {
            System.out.println("Уравнение написано корректно!!!");
        } else {
            System.out.println("Неверно расставлены скобки!!!");
        }
    }
}