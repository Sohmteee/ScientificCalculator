import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Functions extends Vars {

    public static void setup() {
        btn2nd.setEnabled(false);
        btnPi.setEnabled(false);
        btn_e.setEnabled(false);
        btnSquareOf_x.setEnabled(false);
        btnInverseOf_x.setEnabled(false);
        btnAbsoluteOf_x.setEnabled(false);
        btnExponent.setEnabled(false);
        btnMod.setEnabled(false);
        btnRootOf_x.setEnabled(false);
        btnOpenParenthesis.setEnabled(false);
        btnCloseParenthesis.setEnabled(false);
        btn_nFactorial.setEnabled(false);
        btn_xToPower_y.setEnabled(false);
        btn10ToPower_x.setEnabled(false);
        btn_log.setEnabled(false);
        btnNaturalLog.setEnabled(false);
        btnDot.setEnabled(false);

    }

    /*If the input isn't 0, it sets the input to 0
     * but if it was already 0 and the output isn't null, it sets it to null
     * So if you want to clear the whole display, you'll have to press the "C" button twice*/
    public static void clear() {
        btnC.setText("C");
        if (!txtInput.getText().equals("0")) {
            if (shownAnswer) txtOutput.setText(null);
            txtInput.setText("0");
            number = "0";
        } else if (txtInput.getText().equals("0") & txtOutput.getText() != null) {
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

    public static void operator(Operator operator) {

        /*if shownAnswer is true (if the answer has been displayed after pressing the equals button)
        * the upper part of the display is cleared and the answer is set to whatever number that was inputted
        */
        if (shownAnswer) {
            txtOutput.setText("");
            answer = Double.parseDouble(txtInput.getText());
            shownAnswer = false;
        }
        //if the answer hasn't been shown yet, the whole expression is solved using BOD-MAS
        else answer = evaluateString(txtOutput.getText() + number);

        //the output is updated with the operation that was entered
        if (add.equals(operator))
            txtOutput.setText(txtOutput.getText() + number + " + ");
        else if (subtract.equals(operator))
            txtOutput.setText(txtOutput.getText() + number + " - ");
        else if (multiply.equals(operator))
            txtOutput.setText(txtOutput.getText() + number + " \u00D7 ");
        else if (divide.equals(operator))
            txtOutput.setText(txtOutput.getText() + number + " \u00F7 ");
        else if (equals.equals(operator)) {
            txtOutput.setText(txtOutput.getText() + number + " = ");
            shownAnswer = true;

            //and the answer is displayed as the input
            txtInput.setText(formatter(String.valueOf(answer)));
        }

        txtInput.setText(formatter(String.valueOf(answer)));
    }

    public static double evaluateString(String expression) {
        List<String> expressionArray = new ArrayList<>(), //arraylist to store expression in list form
                operators = new ArrayList<>(), //stores the operators in the expression
                signs = Arrays.asList("+", "-", "*", "/"); //a predefined list of operators from which reference would be made later
        List<Object> operands = new ArrayList<>();

        //convert the expression to a list
        for (String s : expression.split(" ")) {
            if (s.equals("\u00D7")) s = "*";
            if (s.equals("\u00F7")) s = "/";
            expressionArray.add(s);
        }

        //assign elements of the operators and operands lists
        for (String s : expressionArray) {
            //check if the string is an operator
            if (signs.contains(s)) operators.add(s);

            //check if the string is a numeric value
            if (s.matches("-?\\d+(\\.\\d+)?")) operands.add(s);

        }

        //Pardon the very long comment below :)

        /* 2 - 3 is the same as 2 + (-3)
        *  So this for loop checks if there's any "-" operation in the operation list
        *  if it finds one, it changes it to a "+" and then signs the next number to make it negative
        *
        *  For example:
        *  if the expression is 9 + 3 - 7 - 10 - 6
        *  the operands will be [9, 3, 7, 10, 6] and the operators, [+, -, -, -]
        *  this for loop changes the operand to [9, 3, -7, -10, -6] and the operators to [+, +, +, +]
        *
        *  Without this for loop, the evaluateString() method will solve the above expression totally differently from what we'd expect
        *  it'll first add 9 and 3 to give 12 (= 12 - 7 - 10 - 6)
        *  then subtract 7 from 12 to give 5 (= 5 - 10 - 6)
        *  subtract 6 from 10 to give 4 (= 5 - 4)
        *  and finally subtract 4 from 5 to give 1
        *
        *  But the correct answer is actually -11 :) */

        for (String s : operators) {
            if (s.equals("-")) {
                operands.set(operators.indexOf(s) + 1, -Double.parseDouble(String.valueOf(operands.get(operators.indexOf(s) + 1))));
                operators.set(operators.indexOf(s), "+");
            }
        }

        while (operands.size() > 1) {
            if (operators.contains("/") | operators.contains("*")) {
                for (int i = 0; i < operators.size(); i++) {
                    if (operators.get(i).equals("/")) {
                        operands.set(i, Double.parseDouble(String.valueOf(operands.get(i))) / Double.parseDouble(String.valueOf(operands.get(i + 1))));
                        operands.remove(i + 1);
                        operators.remove(i);
                        /*System.out.println(operands);
                        System.out.println(operators);
                        System.out.println();*/
                    }
                }
                for (int i = 0; i < operators.size(); i++) {
                    if (operators.get(i).equals("*")) {
                        operands.set(i, Double.parseDouble(String.valueOf(operands.get(i))) * Double.parseDouble(String.valueOf(operands.get(i + 1))));
                        operands.remove(i + 1);
                        operators.remove(i);
                    }
                }
            }

            if (operators.contains("+") | operators.contains("-")) {
                for (int i = 0; i < operators.size(); i++) {
                    if (operators.get(i).equals("+")) {
                        operands.set(i, Double.parseDouble(String.valueOf(operands.get(i))) + Double.parseDouble(String.valueOf(operands.get(i + 1))));
                        operands.remove(i + 1);
                        operators.remove(i);
                    }
                }
                for (int i = 0; i < operators.size(); i++) {
                    if (operators.get(i).equals("-")) {
                        operands.set(i, Double.parseDouble(String.valueOf(operands.get(i))) - Double.parseDouble(String.valueOf(operands.get(i + 1))));
                        operands.remove(i + 1);
                        operators.remove(i);
                    }
                }
            }
        }

        //the operands list should contain just one element at this point(the answer) and that is what is returned
        return Double.parseDouble(String.valueOf(operands.get(0)));

    }

    public static String formatter(String number) {
        double amount = Double.parseDouble(number);
        DecimalFormat formatter = new DecimalFormat("#,###.##########");
        return formatter.format(amount);
    }

    public static boolean isTooLong(long l, int i) {
        return (String.valueOf(l + i).length() > 15);
    }

}
