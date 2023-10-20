package main.service;

public class Service {
    public static double calculate(String operator, double num1, double num2) {
        try {
            return switch (operator) {
                case "+" -> num1 + num2;
                case "-" -> num1 - num2;
                case "*" -> num1 * num2;
                case "/" -> num1 / num2;
                default -> {
                    System.err.println("Operator is invalid");
                    throw new NumberFormatException();
                }
            };
        } catch (NumberFormatException e) {
            System.err.println("Invalid line data value");
            System.exit(1);
        } catch (StringIndexOutOfBoundsException e) {
            System.err.println("Invalid line data length");
            System.exit(1);
        } catch (ArithmeticException e) {
            System.err.println("Divide by zero");
            System.exit(1);
        }

        return 0;
    }
}
