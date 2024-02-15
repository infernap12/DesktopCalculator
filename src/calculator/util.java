package calculator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class util {
    public static final Logger logger = Logger.getLogger(util.class.getSimpleName());
    static final FileHandler fh;

    static {
        try {
            fh = new FileHandler("C:/javaLog.log");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logger.addHandler(fh);
    }

    static final Pattern operator = Pattern.compile("[+\\-×÷^]");
    static final Pattern number = Pattern.compile("\\d+(\\.\\d+)*");

    public static String convertToPostfix(String input) {
        logger.info("Expression no touch:" + input);
        if (!input.contains("√")) {
            input = input.replaceAll("\\(-", "(0-");
        }
        logger.info("Expression:" + input);
        ArrayList<String> expression = convertToArrayList(input);
        StringBuilder postfix = new StringBuilder();
        Stack<String> stack = new Stack<>();
        for (String string : expression) {
            if (number.matcher(string).matches()) {
                postfix.append(string).append(" ");
            } else if (string.matches("\\(")) {
                stack.push(string);
            } else if (string.matches("\\)")) {
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    postfix.append(stack.pop()).append(" ");
                }
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (isOperator(string.charAt(0)) || string.charAt(0) == '√') {
                while (!stack.isEmpty() && precedence(string.charAt(0)) <= precedence(stack.peek().charAt(0))) {
                    postfix.append(stack.pop()).append(' ');
                }
                stack.push(string);
            }
        }
        while (!stack.isEmpty()) {
            postfix.append(stack.pop()).append(' ');
        }

        logger.info("Postfix notation:" + postfix.toString().trim());
        return postfix.toString().trim();
    }

    private static ArrayList<String> convertToArrayList(String input) {
        Pattern element = Pattern.compile(number.pattern() + "|" + operator.pattern() + "|\\(|\\)|√");
        Matcher matcher = element.matcher(input);
        ArrayList<String> output = new ArrayList<>();
        List<MatchResult> mrL = matcher.results().toList();
        for (MatchResult matchResult : mrL) {
            output.add(matchResult.group());
        }
        return output;
    }

    private static int precedence(char ch) {
        switch (ch) {
            case '+', '-' -> {
                return 1;
            }
            case '×', '÷' -> {
                return 2;
            }
            case '^', '√' -> {
                return 3;
            }

            default -> {
                return -1;
            }
        }
    }

    private static boolean isOperator(char ch) {
        return operator.matcher("" + ch).matches();
    }

    /**
     * @param input a postfix notation expression
     * @return the double value result
     */
    public static String solve(String input) {
        ArrayList<String> expression = Arrays.stream(input.split("\\s")).collect(Collectors.toCollection(ArrayList::new));
        Stack<String> stack = new Stack<>();
        double a;
        double b;
        for (String string : expression) {
            if (number.matcher(string).matches()) {
                stack.push(string);
            } else if (isOperator(string.charAt(0))) {
                b = Double.parseDouble(stack.pop());
                a = Double.parseDouble(stack.pop());
                stack.push(String.valueOf(calculate(a, b, string.charAt(0))));
            } else if (string.charAt(0) == '√') {
                a = Double.parseDouble(stack.pop());
                stack.push(String.valueOf(Math.sqrt(a)));
            }
        }
        String output;
        double result = Double.parseDouble(stack.pop());
        if (result % 1 == 0) {
            output = String.valueOf((int) result);
        } else {
            output = String.valueOf(result);
        }
        System.out.println("Calculated result:" + output);
        return output;
    }


    private static double calculate(double a, double b, char ch) {
        switch (ch) {
            case '÷' -> {
                return a / b;
            }
            case '×' -> {
                return a * b;
            }
            case '+' -> {
                return a + b;
            }
            case '-' -> {
                return a - b;
            }
            case '^' -> {
                return Math.pow(a, b);
            }
            default -> {
                return 0;
            }
        }
    }
}
