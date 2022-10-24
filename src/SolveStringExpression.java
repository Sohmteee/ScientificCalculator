import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings("SuspiciousListRemoveInLoop")
public class SolveStringExpression {
    public static void main(String[] args) {
        System.out.print(
                "Enter the expression you want to calculate and ensure there's a space between every number and operator"
                        +
                        "\n(For example: 9 + 3.4 - 7 * 10 / 6): ");
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                String expression = sc.nextLine();
                System.out.println("Answer: " + formatter(String.valueOf(evaluateString(expression))));
                sc.close();
            } catch (Exception e) {
                System.out.println("Invalid input!\nEnsure there's a space between every number and operator");
                System.out.print("Enter the expression: ");
                String expression = sc.nextLine();
                System.out.println("Answer: " + formatter(String.valueOf(evaluateString(expression))));
            }
        }

    }

    public static double evaluateString(String expression) {
        List<String> expressionArray, // arraylist to store expression in list form
                operators = new ArrayList<>(), // stores the operators in the expression
                signs = Arrays.asList("+", "-", "*", "/"); // a predefined list of operators from which reference would
                                                           // be made later
        List<Object> operands = new ArrayList<>();

        // convert the expression to a list
        expressionArray = new ArrayList<>(Arrays.asList(expression.split(" ")));

        // assign elements of the operators and operands lists
        for (String s : expressionArray) {
            // check if the string is an operator
            if (signs.contains(s))
                operators.add(s);

            // check if the string is a numeric value
            if (s.matches("-?\\d+(\\.\\d+)?"))
                operands.add(s);
        }

        // display the separated operands and operators
        if (!operands.isEmpty() && !operators.isEmpty())
            System.out.println("\nExpression: " + expression +
                    "\nOperands: " + operands +
                    "\nOperators: " + operators + "\n");

        // Pardon the very long comment below :)

        /*
         * 2 - 3 is the same as 2 + (-3)
         * So this for loop checks if there's any "-" operation in the operation list
         * if it finds one, it changes it to a "+" and then signs the next number to
         * make it negative
         *
         * For example:
         * if the expression is 9 + 3 - 7 - 10 - 6
         * the operands will be [9, 3, 7, 10, 6] and the operators, [+, -, -, -]
         * this for loop changes the operand to [9, 3, -7, -10, -6] and the operators to
         * [+, +, +, +]
         *
         * Without this for loop, the evaluateString() method will solve the above
         * expression totally differently from what we'd expect
         * it'll first add 9 and 3 to give 12 (= 12 - 7 - 10 - 6)
         * then subtract 7 from 12 to give 5 (= 5 - 10 - 6)
         * subtract 6 from 10 to give 4 (= 5 - 4)
         * and finally subtract 4 from 5 to give 1
         *
         * But the correct answer is actually -11 :)
         */

        for (String s : operators) {
            if (s.equals("-")) {
                operands.set(operators.indexOf(s) + 1,
                        -Double.parseDouble(String.valueOf(operands.get(operators.indexOf(s) + 1))));
                operators.set(operators.indexOf(s), "+");
            }
        }

        while (operands.size() > 1) {
            if (operators.contains("/") | operators.contains("*")) {
                for (int i = 0; i < operators.size(); i++) {
                    if (operators.get(i).equals("/")) {
                        operands.set(i, Double.parseDouble(String.valueOf(operands.get(i)))
                                / Double.parseDouble(String.valueOf(operands.get(i + 1))));
                        operands.remove(i + 1);
                        operators.remove(i);
                        /*
                         * System.out.println(operands);
                         * System.out.println(operators);
                         * System.out.println();
                         */
                    }
                }
                for (int i = 0; i < operators.size(); i++) {
                    if (operators.get(i).equals("*")) {
                        operands.set(i, Double.parseDouble(String.valueOf(operands.get(i)))
                                * Double.parseDouble(String.valueOf(operands.get(i + 1))));
                        operands.remove(i + 1);
                        operators.remove(i);
                    }
                }
            }

            if (operators.contains("+") | operators.contains("-")) {
                for (int i = 0; i < operators.size(); i++) {
                    if (operators.get(i).equals("+")) {
                        operands.set(i, Double.parseDouble(String.valueOf(operands.get(i)))
                                + Double.parseDouble(String.valueOf(operands.get(i + 1))));
                        operands.remove(i + 1);
                        operators.remove(i);
                    }
                }
                for (int i = 0; i < operators.size(); i++) {
                    if (operators.get(i).equals("-")) {
                        operands.set(i, Double.parseDouble(String.valueOf(operands.get(i)))
                                - Double.parseDouble(String.valueOf(operands.get(i + 1))));
                        operands.remove(i + 1);
                        operators.remove(i);
                    }
                }
            }
        }

        // the operands list should contain just one element at this point(the answer)
        // and that is what is returned
        return Double.parseDouble(String.valueOf(operands.get(0)));
    }

    public static String formatter(String number) {
        double amount = Double.parseDouble(number);
        DecimalFormat formatter = new DecimalFormat("#,###.##########");
        return formatter.format(amount);
    }
}
