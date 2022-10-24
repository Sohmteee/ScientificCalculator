public class Test {
    public static void main(String[] args) {

        /*Date date = new Date();
        System.out.printf("%tT%n", date);
        System.out.printf("%1$tA, %1$tB %1$tY %n", date);*/

        /*String number = "123456.01";
        float floatNumber = Float.parseFloat(number);
        String string = String.format("%,f", floatNumber);
        System.out.println(string);*/

        Operator add = new Operator("+");

        String equation = "2 + 3 - 5 - ";

        System.out.println(changeOperator(equation, add));

    }

    public static String changeOperator(String equation, Operator operator) {
        char[] equationChar = equation.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < equationChar.length - 3; i++) {
            stringBuilder.append(equationChar[i]);
        }

        stringBuilder.append(" ").append(operator.operatorSign()).append(" ");

        equation = String.valueOf(stringBuilder);

        return  equation;
    }
}
