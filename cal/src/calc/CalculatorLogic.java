package calc;

public class CalculatorLogic {
    private double num1;
    private double num2;
    private char operator;

    public void setNum1(double num1) {
        this.num1 = num1;
    }

    public void setOperator(char operator) {
        this.operator = operator;
    }

    public double calculate(double num2) {
        this.num2 = num2;
        return switch (operator) {
            case '+' -> num1 + num2;
            case '-' -> num1 - num2;
            case '*' -> num1 * num2;
            case '/' -> num2 != 0 ? num1 / num2 : 0;
            default -> 0;
        };
    }
}
