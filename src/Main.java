import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main extends Functions {

    private JFrame frmSohmteesCalculator;

    /**
     * Create the application.
     */
    public Main() {
        initialize();
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Main window = new Main();
                window.frmSohmteesCalculator.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmSohmteesCalculator = new JFrame();
        frmSohmteesCalculator.setResizable(false);
        frmSohmteesCalculator.setBackground(SystemColor.controlHighlight);
        frmSohmteesCalculator.setTitle("Sohmtee's Calculator");
        frmSohmteesCalculator.getContentPane().setBackground(SystemColor.controlHighlight);
        frmSohmteesCalculator.setBounds(100, 100, 305, 467);
        frmSohmteesCalculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmSohmteesCalculator.getContentPane().setLayout(null);

        //display
        txtOutput = new JTextField();
        txtOutput.setRequestFocusEnabled(false);
        txtOutput.setEditable(false);
        txtOutput.setHorizontalAlignment(SwingConstants.RIGHT);
        txtOutput.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
        txtOutput.setColumns(10);
        txtOutput.setBorder(null);
        txtOutput.setBackground(SystemColor.controlHighlight);
        txtOutput.setBounds(3, 46, 279, 27);
        frmSohmteesCalculator.getContentPane().add(txtOutput);

        txtInput = new JTextField();
        txtInput.setEditable(false);
        txtInput.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        txtInput.setHorizontalAlignment(SwingConstants.RIGHT);
        txtInput.setText("0");
        txtInput.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 25));
        txtInput.setBorder(null);
        txtInput.setBackground(SystemColor.controlHighlight);
        txtInput.setBounds(3, 74, 279, 47);
        frmSohmteesCalculator.getContentPane().add(txtInput);
        txtInput.setColumns(10);


        //first row
        btn2nd = new JButton("2nd");
        btn2nd.setRequestFocusEnabled(false);
        btn2nd.addActionListener(e -> {
        });
        btn2nd.setBorder(null);
        btn2nd.setBackground(SystemColor.menu);
        btn2nd.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
        btn2nd.setBounds(3, 126, 54, 40);
        frmSohmteesCalculator.getContentPane().add(btn2nd);

        btnPi = new JButton("\u03C0");
        btnPi.setRequestFocusEnabled(false);
        btnPi.addActionListener(e -> {
        });
        btnPi.setBorder(null);
        btnPi.setBackground(SystemColor.menu);
        btnPi.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
        btnPi.setBounds(60, 126, 54, 40);
        frmSohmteesCalculator.getContentPane().add(btnPi);

        btn_e = new JButton("e");
        btn_e.setRequestFocusEnabled(false);
        btn_e.addActionListener(e -> {
        });
        btn_e.setBorder(null);
        btn_e.setBackground(SystemColor.menu);
        btn_e.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
        btn_e.setBounds(117, 126, 54, 40);
        frmSohmteesCalculator.getContentPane().add(btn_e);

        btnC = new JButton("C");
        btnC.setRequestFocusEnabled(false);
        btnC.addActionListener(e -> clear());
        btnC.setBorder(null);
        btnC.setBackground(SystemColor.menu);
        btnC.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
        btnC.setBounds(174, 126, 54, 40);
        frmSohmteesCalculator.getContentPane().add(btnC);

        btnDel = new JButton("del");
        btnDel.setRequestFocusEnabled(false);
        btnDel.addActionListener(e -> {
            //the \b method wasn't working, so I had to use this method instead
            if (number.length() > 0 & !number.equals("0")) {
                List<Character> newInput = new ArrayList<>();

                //convert the number to a char list and remove the last element
                for (char c : number.toCharArray()) newInput.add(c);
                newInput.remove(newInput.size() - 1);

                //convert the char list to a char array so that it can then be converted to a string
                char[] newInputString = new char[newInput.size()];

                for (int i = 0; i < newInput.size(); i++) {
                    newInputString[i] = newInput.get(i);
                }

                /*if the string is empty, number is set to "0"
                * otherwise, number is set to the new string
                * and then the number is displayed as the new input*/
                number = new String(newInputString).isEmpty() ? "0" : new String(newInputString);
                txtInput.setText(formatter(number));

            }
        });
        btnDel.setBorder(null);
        btnDel.setBackground(SystemColor.menu);
        btnDel.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
        btnDel.setBounds(231, 126, 54, 40);
        frmSohmteesCalculator.getContentPane().add(btnDel);


        //second row
        btnSquareOf_x = new JButton("<html>x<sup> 2</sup></html>");
        btnSquareOf_x.setRequestFocusEnabled(false);
        btnSquareOf_x.addActionListener(e -> {
        });
        btnSquareOf_x.setBorder(null);
        btnSquareOf_x.setBackground(SystemColor.menu);
        btnSquareOf_x.setFont(new Font("Serif", Font.ITALIC, 15));
        btnSquareOf_x.setBounds(3, 169, 54, 40);
        frmSohmteesCalculator.getContentPane().add(btnSquareOf_x);

        btnInverseOf_x = new JButton("<html>x<sup> -1</sup></html>");
        btnInverseOf_x.setRequestFocusEnabled(false);
        btnInverseOf_x.addActionListener(e -> {
        });
        btnInverseOf_x.setBorder(null);
        btnInverseOf_x.setBackground(SystemColor.menu);
        btnInverseOf_x.setFont(new Font("Serif", Font.ITALIC, 15));
        btnInverseOf_x.setBounds(60, 169, 54, 40);
        frmSohmteesCalculator.getContentPane().add(btnInverseOf_x);

        btnAbsoluteOf_x = new JButton("|x|");
        btnAbsoluteOf_x.setRequestFocusEnabled(false);
        btnAbsoluteOf_x.addActionListener(e -> {
        });
        btnAbsoluteOf_x.setBorder(null);
        btnAbsoluteOf_x.setBackground(SystemColor.menu);
        btnAbsoluteOf_x.setFont(new Font("Serif", Font.ITALIC, 15));
        btnAbsoluteOf_x.setBounds(117, 169, 54, 40);
        frmSohmteesCalculator.getContentPane().add(btnAbsoluteOf_x);

        btnExponent = new JButton("exp");
        btnExponent.setRequestFocusEnabled(false);
        btnExponent.addActionListener(e -> {
        });
        btnExponent.setBorder(null);
        btnExponent.setBackground(SystemColor.menu);
        btnExponent.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
        btnExponent.setBounds(174, 169, 54, 40);
        frmSohmteesCalculator.getContentPane().add(btnExponent);

        btnMod = new JButton("mod");
        btnMod.setRequestFocusEnabled(false);
        btnMod.addActionListener(e -> {
        });
        btnMod.setBorder(null);
        btnMod.setBackground(SystemColor.menu);
        btnMod.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
        btnMod.setBounds(231, 169, 54, 40);
        frmSohmteesCalculator.getContentPane().add(btnMod);


        //third row
        btnRootOf_x = new JButton("\u221Ax");
        btnRootOf_x.setRequestFocusEnabled(false);
        btnRootOf_x.addActionListener(e -> {
        });
        btnRootOf_x.setBorder(null);
        btnRootOf_x.setBackground(SystemColor.menu);
        btnRootOf_x.setFont(new Font("Serif", Font.ITALIC, 15));
        btnRootOf_x.setBounds(3, 212, 54, 40);
        frmSohmteesCalculator.getContentPane().add(btnRootOf_x);

        btnOpenParenthesis = new JButton("(");
        btnOpenParenthesis.setRequestFocusEnabled(false);
        btnOpenParenthesis.addActionListener(e -> {
            txtOutput.setText(txtOutput.getText() + "(");
            numberOfOpenParentheses++;
            btnOpenParenthesis.setText("<html>(<sup> " + numberOfOpenParentheses + "</sup></html>");
        });
        btnOpenParenthesis.setBorder(null);
        btnOpenParenthesis.setBackground(SystemColor.menu);
        btnOpenParenthesis.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
        btnOpenParenthesis.setBounds(60, 212, 54, 40);
        frmSohmteesCalculator.getContentPane().add(btnOpenParenthesis);

        btnCloseParenthesis = new JButton(")");
        btnCloseParenthesis.setRequestFocusEnabled(false);
        btnCloseParenthesis.addActionListener(e -> {
            if (numberOfOpenParentheses > numberOfClosedParentheses) {
                txtOutput.setText(txtOutput.getText() + ")");
                numberOfOpenParentheses--;
                if (numberOfOpenParentheses != 0)
                    btnOpenParenthesis.setText("<html>(<sup> " + numberOfOpenParentheses + "</sup></html>");
                else
                    btnOpenParenthesis.setText("(");

            }
        });
        btnCloseParenthesis.setBorder(null);
        btnCloseParenthesis.setBackground(SystemColor.menu);
        btnCloseParenthesis.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
        btnCloseParenthesis.setBounds(117, 212, 54, 40);
        frmSohmteesCalculator.getContentPane().add(btnCloseParenthesis);

        btn_nFactorial = new JButton("n!");
        btn_nFactorial.setRequestFocusEnabled(false);
        btn_nFactorial.addActionListener(e -> {
        });
        btn_nFactorial.setBorder(null);
        btn_nFactorial.setBackground(SystemColor.menu);
        btn_nFactorial.setFont(new Font("Serif", Font.ITALIC, 15));
        btn_nFactorial.setBounds(174, 212, 54, 40);
        frmSohmteesCalculator.getContentPane().add(btn_nFactorial);

        btnDivision = new JButton("\u00F7");
        btnDivision.setRequestFocusEnabled(false);
        btnDivision.addActionListener(e -> {
            if (!clearInput) {
                operator(divide);
                resetBooleans();
            }
        });
        btnDivision.setBorder(null);
        btnDivision.setBackground(SystemColor.menu);
        btnDivision.setFont(new Font("Segoe UI Light", Font.PLAIN, 25));
        btnDivision.setBounds(231, 212, 54, 40);
        frmSohmteesCalculator.getContentPane().add(btnDivision);


        //fourth row
        btn_xToPower_y = new JButton("<html>x<sup> y</sup></html>");
        btn_xToPower_y.setRequestFocusEnabled(false);
        btn_xToPower_y.addActionListener(e -> {
        });
        btn_xToPower_y.setBorder(null);
        btn_xToPower_y.setBackground(SystemColor.menu);
        btn_xToPower_y.setFont(new Font("Serif", Font.ITALIC, 15));
        btn_xToPower_y.setBounds(3, 255, 54, 40);
        frmSohmteesCalculator.getContentPane().add(btn_xToPower_y);

        btnSeven = new JButton("7");
        btnSeven.setRequestFocusEnabled(false);
        btnSeven.addActionListener(e -> {
            btnC.setText("CE");
            if (clearInput) {
                number = "7";
                clearInput = false;
            } else {
                if (!isTooLong(Long.parseLong(number), 7)) {
                    number += "7";
                }
            }
            txtInput.setText(formatter(number));

        });
        btnSeven.setBorder(null);
        btnSeven.setBackground(Color.WHITE);
        btnSeven.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
        btnSeven.setBounds(60, 255, 54, 40);
        frmSohmteesCalculator.getContentPane().add(btnSeven);

        btnEight = new JButton("8");
        btnEight.setRequestFocusEnabled(false);
        btnEight.addActionListener(e -> {
            btnC.setText("CE");
            if (clearInput) {
                number = "8";
                clearInput = false;
            } else {
                if (!isTooLong(Long.parseLong(number), 8)) {
                    number += "8";
                }
            }
            txtInput.setText(formatter(number));

        });
        btnEight.setBorder(null);
        btnEight.setBackground(Color.WHITE);
        btnEight.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
        btnEight.setBounds(117, 255, 54, 40);
        frmSohmteesCalculator.getContentPane().add(btnEight);

        btnNine = new JButton("9");
        btnNine.setRequestFocusEnabled(false);
        btnNine.addActionListener(e -> {
            btnC.setText("CE");
            if (clearInput) {
                number = "9";
                clearInput = false;
            } else {
                if (!isTooLong(Long.parseLong(number), 9)) {
                    number += "9";
                }
            }
            txtInput.setText(formatter(number));

        });
        btnNine.setBorder(null);
        btnNine.setBackground(Color.WHITE);
        btnNine.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
        btnNine.setBounds(174, 255, 54, 40);
        frmSohmteesCalculator.getContentPane().add(btnNine);

        btnMultiplication = new JButton("\u00D7");
        btnMultiplication.setRequestFocusEnabled(false);
        btnMultiplication.addActionListener(e -> {
            if (!clearInput) {
                operator(multiply);
                resetBooleans();
            }
        });
        btnMultiplication.setBorder(null);
        btnMultiplication.setBackground(SystemColor.menu);
        btnMultiplication.setFont(new Font("Segoe UI Light", Font.PLAIN, 25));
        btnMultiplication.setBounds(231, 255, 54, 40);
        frmSohmteesCalculator.getContentPane().add(btnMultiplication);


        //fifth row
        btn10ToPower_x = new JButton("<html>10<sup> x</sup></html>");
        btn10ToPower_x.setRequestFocusEnabled(false);
        btn10ToPower_x.addActionListener(e -> {
        });
        btn10ToPower_x.setBorder(null);
        btn10ToPower_x.setBackground(SystemColor.menu);
        btn10ToPower_x.setFont(new Font("Serif", Font.ITALIC, 15));
        btn10ToPower_x.setBounds(3, 298, 54, 40);
        frmSohmteesCalculator.getContentPane().add(btn10ToPower_x);

        btnFour = new JButton("4");
        btnFour.setRequestFocusEnabled(false);
        btnFour.addActionListener(e -> {
            btnC.setText("CE");
            if (clearInput) {
                number = "4";
                clearInput = false;
            } else {
                if (!isTooLong(Long.parseLong(number), 4)) {
                    number += "4";
                }
            }
            txtInput.setText(formatter(number));

        });
        btnFour.setBorder(null);
        btnFour.setBackground(Color.WHITE);
        btnFour.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
        btnFour.setBounds(60, 298, 54, 40);
        frmSohmteesCalculator.getContentPane().add(btnFour);

        btnFive = new JButton("5");
        btnFive.setRequestFocusEnabled(false);
        btnFive.addActionListener(e -> {
            btnC.setText("CE");
            if (clearInput) {
                number = "5";
                clearInput = false;
            } else {
                if (!isTooLong(Long.parseLong(number), 5)) {
                    number += "5";
                }
            }
            txtInput.setText(formatter(number));

        });
        btnFive.setBorder(null);
        btnFive.setBackground(Color.WHITE);
        btnFive.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
        btnFive.setBounds(117, 298, 54, 40);
        frmSohmteesCalculator.getContentPane().add(btnFive);

        btnSix = new JButton("6");
        btnSix.setRequestFocusEnabled(false);
        btnSix.addActionListener(e -> {
            btnC.setText("CE");
            if (clearInput) {
                number = "6";
                clearInput = false;
            } else {
                if (!isTooLong(Long.parseLong(number), 6)) {
                    number += "6";
                }
            }
            txtInput.setText(formatter(number));

        });
        btnSix.setBorder(null);
        btnSix.setBackground(Color.WHITE);
        btnSix.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
        btnSix.setBounds(174, 298, 54, 40);
        frmSohmteesCalculator.getContentPane().add(btnSix);

        btnSubtraction = new JButton("\u2013");
        btnSubtraction.setRequestFocusEnabled(false);
        btnSubtraction.addActionListener(e -> {
            if (!clearInput) {
                operator(subtract);
                resetBooleans();
            }
        });
        btnSubtraction.setBorder(null);
        btnSubtraction.setBackground(SystemColor.menu);
        btnSubtraction.setFont(new Font("Segoe UI Light", Font.PLAIN, 25));
        btnSubtraction.setBounds(231, 298, 54, 40);
        frmSohmteesCalculator.getContentPane().add(btnSubtraction);


        //sixth row
        btn_log = new JButton("log");
        btn_log.setRequestFocusEnabled(false);
        btn_log.addActionListener(e -> {
        });
        btn_log.setBorder(null);
        btn_log.setBackground(SystemColor.menu);
        btn_log.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
        btn_log.setBounds(3, 341, 54, 40);
        frmSohmteesCalculator.getContentPane().add(btn_log);

        btnOne = new JButton("1");
        btnOne.setRequestFocusEnabled(false);
        btnOne.addActionListener(e -> {
            btnC.setText("CE");
            if (clearInput) {
                number = "1";
                clearInput = false;
            } else {
                if (!isTooLong(Long.parseLong(number), 1)) {
                    number += "1";
                }
            }
            txtInput.setText(formatter(number));

        });
        btnOne.setBorder(null);
        btnOne.setBackground(Color.WHITE);
        btnOne.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
        btnOne.setBounds(60, 341, 54, 40);
        frmSohmteesCalculator.getContentPane().add(btnOne);

        btnTwo = new JButton("2");
        btnTwo.setRequestFocusEnabled(false);
        btnTwo.addActionListener(e -> {
            btnC.setText("CE");
            if (clearInput) {
                number = "2";
                clearInput = false;
            } else {
                if (!isTooLong(Long.parseLong(number), 2)) {
                    number += "2";
                }
            }
            txtInput.setText(formatter(number));

        });
        btnTwo.setBorder(null);
        btnTwo.setBackground(Color.WHITE);
        btnTwo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
        btnTwo.setBounds(117, 341, 54, 40);
        frmSohmteesCalculator.getContentPane().add(btnTwo);

        btnThree = new JButton("3");
        btnThree.setRequestFocusEnabled(false);
        btnThree.addActionListener(e -> {
            btnC.setText("CE");
            if (clearInput) {
                number = "3";
                clearInput = false;
            } else {
                if (!isTooLong(Long.parseLong(number), 3)) {
                    number += 3;
                }
            }
            txtInput.setText(formatter(number));

        });
        btnThree.setBorder(null);
        btnThree.setBackground(Color.WHITE);
        btnThree.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
        btnThree.setBounds(174, 341, 54, 40);
        frmSohmteesCalculator.getContentPane().add(btnThree);

        btnAddition = new JButton("+");
        btnAddition.setRequestFocusEnabled(false);
        btnAddition.addActionListener(e -> {
            if (!clearInput) {
                operator(add);
                resetBooleans();
            }
        });
        btnAddition.setBorder(null);
        btnAddition.setBackground(SystemColor.menu);
        btnAddition.setFont(new Font("Segoe UI Light", Font.PLAIN, 25));
        btnAddition.setBounds(231, 341, 54, 40);
        frmSohmteesCalculator.getContentPane().add(btnAddition);


        //seventh(last) row
        btnNaturalLog = new JButton("ln");
        btnNaturalLog.setRequestFocusEnabled(false);
        btnNaturalLog.addActionListener(e -> {
        });
        btnNaturalLog.setBorder(null);
        btnNaturalLog.setBackground(SystemColor.menu);
        btnNaturalLog.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
        btnNaturalLog.setBounds(3, 384, 54, 40);
        frmSohmteesCalculator.getContentPane().add(btnNaturalLog);

        btnPlusMinus = new JButton("\u00B1");
        btnPlusMinus.setRequestFocusEnabled(false);
        btnPlusMinus.addActionListener(e -> {

            if (!txtInput.getText().equals("0") & (!txtInput.getText().equals("0."))) {
                double num = Double.parseDouble(txtInput.getText());
                if (num != (int) num) isDouble = true;

                int intN = 0;
                double doubleN = 0d;

                if (isDouble) {
                    doubleN = num;
                    doubleN *= -1;
                } else {
                    intN = (int) num;
                    intN *= -1;
                }

                if (isDouble) txtInput.setText(String.valueOf(doubleN));
                else txtInput.setText(String.valueOf(intN));
            }

        });
        btnPlusMinus.setBorder(null);
        btnPlusMinus.setBackground(Color.WHITE);
        btnPlusMinus.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
        btnPlusMinus.setBounds(60, 384, 54, 40);
        frmSohmteesCalculator.getContentPane().add(btnPlusMinus);

        btnZero = new JButton("0");
        btnZero.setRequestFocusEnabled(false);
        btnZero.addActionListener(e -> {
            if (!clearInput) {
                btnC.setText("CE");
                if (!isTooLong(Long.parseLong(number), 0)) {
                    number += "0";
                    txtInput.setText(formatter(number));
                }
            }
        });
        btnZero.setBorder(null);
        btnZero.setBackground(Color.WHITE);
        btnZero.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
        btnZero.setBounds(117, 384, 54, 40);
        frmSohmteesCalculator.getContentPane().add(btnZero);

        btnDot = new JButton(".");
        btnDot.setRequestFocusEnabled(false);
        btnDot.addActionListener(e -> {
            if (!isDecimal) {
                btnC.setText("CE");
                number += ".";
                txtInput.setText(formatter(number));
                isDecimal = true;
                clearInput = false;
            }
        });
        btnDot.setBorder(null);
        btnDot.setBackground(Color.WHITE);
        btnDot.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
        btnDot.setBounds(174, 384, 54, 40);
        frmSohmteesCalculator.getContentPane().add(btnDot);

        btnEquals = new JButton("=");
        btnEquals.setRequestFocusEnabled(false);
        btnEquals.addActionListener(e -> {
            if (!clearInput) {
                operator(equals);
                resetBooleans();
                answer = 0;
                nextNumber = 0;
            }
        });
        btnEquals.setBorder(null);
        btnEquals.setBackground(SystemColor.activeCaption);
        btnEquals.setFont(new Font("Segoe UI Light", Font.BOLD, 25));
        btnEquals.setBounds(231, 384, 54, 40);
        frmSohmteesCalculator.getContentPane().add(btnEquals);

    }

}
