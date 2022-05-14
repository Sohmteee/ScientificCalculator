import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("SuspiciousListRemoveInLoop")
public class Functions extends Vars {

    /*If the input isn't 0, it sets the input to 0
     * but if it was already 0 and the output isn't null, it sets it to null
     * So if you want to clear the whole display, you'll have to press the "C" button twice*/
    public static void clear() {
        if (!txtInput.getText().equals("0")) txtInput.setText("0");
        else if (txtInput.getText().equals("0") & txtOutput.getText() != null) {
            txtOutput.setText(null);
        }
        resetBooleans();
    }

    //this resets all the booleans to their "default" values
    public static void resetBooleans() {
        clearInput = true;
        isDouble = false;
        isDecimal = false;
    }

    /*this method is called whenever an operator like +, -, ×, ÷, or = is pressed.
     * it also uses the evaluateString method to solve the expression using the BOD-MAS rule*/
    public static void operator(Operator operator) {

        //if shownAnswer is true,
        if (shownAnswer) {
            txtOutput.setText("");
            answer = Double.parseDouble(txtInput.getText());
            shownAnswer = false;
        }
        else answer = evaluateString(txtOutput.getText() + txtInput.getText());


        if (add.equals(operator))
            txtOutput.setText(txtOutput.getText() + txtInput.getText() + " + ");
        else if (subtract.equals(operator))
            txtOutput.setText(txtOutput.getText() + txtInput.getText() + " - ");
        else if (multiply.equals(operator))
            txtOutput.setText(txtOutput.getText() + txtInput.getText() + " \u00D7 ");
        else if (divide.equals(operator))
            txtOutput.setText(txtOutput.getText() + txtInput.getText() + " \u00F7 ");
        else if (equals.equals(operator)) {
            txtOutput.setText(txtOutput.getText() + txtInput.getText() + " = ");

            shownAnswer = true;

            if (answer != (long) answer) isDouble = true;

            long longAnswer = 0;
            double doubleAnswer = 0d;

            if (isDouble) {
                doubleAnswer = answer;
            } else {
                longAnswer = (long) answer;
            }
            if (isDouble) txtInput.setText(String.valueOf(doubleAnswer));
            else txtInput.setText(String.valueOf(longAnswer));

        }

        if (answer != (long) answer) isDouble = true;

        long longAnswer = 0;
        double doubleAnswer = 0d;

        if (isDouble) {
            doubleAnswer = answer;
        } else {
            longAnswer = (long) answer;
        }
        if (isDouble) txtInput.setText(String.valueOf(doubleAnswer));
        else txtInput.setText(String.valueOf(longAnswer));


    }

    public static double evaluateString(String expression) {
        List<Character> expressionArray = new ArrayList<>(), //arraylist to store expression in list form
                operators = new ArrayList<>(), //stores the operators in the expression
                signs = Arrays.asList('+', '-', '*', '/'), //a predefined list of operators from which reference would be made later
                numbers = Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.'); //a predefined list of digits
        List<Object> operands = new ArrayList<>();
        StringBuilder n = new StringBuilder();

        //convert the expression to a list
        for (char c : expression.toCharArray()) {
            if (c == '\u00D7') c = '*';
            if (c == '\u00F7') c = '/';
            expressionArray.add(c);
        }
        expressionArray.add(' ');

        //bool to check if the current char in the list is a digit
        boolean previousIsInt = false;

        //assign elements of the operators and operands lists
        for (char c : expressionArray) {
            if (signs.contains(c)) operators.add(c);

            /*if the current char is a digit, it sets previousIsInt to true and assigns n as the char
            if the next number is also a digit and previousIsInt is true, it adds the digit to n after multiplying n by 10
            so if the first digit was 3 and the second was 4, it multiplies 3 by 10 and adds 4, making it 34
            it continues to do this until the next char isn't a digit, then it stores the final value of n as the next element of the operands list
            */
            if (numbers.contains(c)) {
                if (!previousIsInt & n == null) {
                    n = new StringBuilder(String.valueOf(c));
                    previousIsInt = true;
                } else
                    n.append(c);

            } else {
                if (n != null)
                    operands.add(Double.parseDouble(String.valueOf(n)));

                n = null;
                previousIsInt = false;
            }

        }

        //Pardon the very long comment below :)

        /*2 - 3 is the same as 2 + (-3)
        * So this for loop checks if there's any "-" operation in the operation list
        * if it finds one, it changes it to a "+" and then signs the next number to make it negative
        *
        * For example:
        * if the expression is 9 + 3 - 7 - 10 - 6
        * the operands will be [9, 3, 7, 10, 6] and the operators, [+, -, -, -]
        * this for loop changes the operand to [9, 3, -7, -10, -6] and the operators to [+, +, +, +]
        *
        * Without this for loop, the evaluateString() method will solve the above expression totally differently from what we'd expect
        * it'll first add 9 and 3 to give 12 (= 12 - 7 - 10 - 6)
        * then subtract 7 from 12 to give 5 (= 5 - 10 - 6)
        * subtract 6 from 10 to give 4 (= 5 - 4)
        * and finally subtract 4 from 5 to give 1
        *
        * But the correct answer is actually -11 :) */

        for (char c : operators) {
            if (c == '-') {
                operands.set(operators.indexOf(c) + 1, -Double.parseDouble(String.valueOf(operands.get(operators.indexOf(c) + 1))));
                operators.set(operators.indexOf(c), '+');
            }
        }

        //In this while loop, the expression is solved using the BOD-MAS rule
        while (operands.size() > 1) {
            if (operators.contains('/') | operators.contains('*')) {
                for (int i = 0; i < operators.size(); i++) {
                    if (operators.get(i) == '/') {
                        operands.set(i, (double) operands.get(i) / (double) operands.get(i + 1));
                        operands.remove(i + 1);
                        operators.remove(i);
                    }
                }
                for (int i = 0; i < operators.size(); i++) {
                    if (operators.get(i) == '*') {
                        operands.set(i, (double) operands.get(i) * (double) operands.get(i + 1));
                        operands.remove(i + 1);
                        operators.remove(i);
                    }
                }
            }

            if (operators.contains('+') | operators.contains('-')) {
                for (int i = 0; i < operators.size(); i++) {
                    if (operators.get(i) == '+') {
                        operands.set(i, (double) operands.get(i) + (double) operands.get(i + 1));
                        operands.remove(i + 1);
                        operators.remove(i);
                    }
                }
                for (int i = 0; i < operators.size(); i++) {
                    if (operators.get(i) == '-') {
                        operands.set(i, (double) operands.get(i) - (double) operands.get(i + 1));
                        operands.remove(i + 1);
                        operators.remove(i);
                    }
                }
            }
        }

        //the operands list should contain just one element at this point(the answer) and that is what is returned
        return (double) operands.get(0);

    }

}
