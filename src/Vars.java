import javax.swing.*;

public class Vars {
    public static JTextField txtInput, txtOutput;
    public static JButton btn2nd, btnPi, btn_e, btnC, btnDel,
            btnSquareOf_x, btnInverseOf_x, btnAbsoluteOf_x, btnExponent, btnMod,
            btnRootOf_x, btnOpenParenthesis, btnCloseParenthesis, btn_nFactorial, btnDivision,
            btn_xToPower_y, btnSeven, btnEight, btnNine, btnMultiplication,
            btn10ToPower_x, btnFour, btnFive, btnSix, btnSubtraction,
            btn_log, btnOne, btnTwo, btnThree, btnAddition,
            btnNaturalLog, btnPlusMinus, btnZero, btnDot, btnEquals;

    public static Operator add = new Operator(), subtract = new Operator(),
            multiply = new Operator(), divide = new Operator(), equals = new Operator();
    public static boolean clearInput = true, isDouble = false,
            isDecimal = false, shownAnswer = false;
    public static double answer = 0, nextNumber = 0;
    public static int  numberOfOpenParentheses = 0, numberOfClosedParentheses = 0;
    public static String number = "0";
}
