import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Filetest {
    public static void main(String[] args) {
        Filetest fileTest = new Filetest();
        var filename = fileTest.getClass().getClassLoader().getResource("test.txt");

        if (filename == null || filename.getPath() == null) {
            System.err.println("file not found");
            System.exit(1);
        }

        readFile(filename.getPath());
    }

    private static void readFile(String filename) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            bufferedReader.lines()
                    .filter(l -> !l.trim().equals(""))
                    .forEach(line -> System.out.println(processLine(line)));

        } catch (IOException e) {
            System.err.println("File Not Found");
            System.exit(1);
        }
    }

    private static double processLine(String line) {
        Pattern operatorPattern = Pattern.compile("[\\+\\-\\*\\/]");
        Pattern numbersPattern = Pattern.compile("[0-9\\.0-9]+");

        Matcher operatorMather = operatorPattern.matcher(line);
        Matcher numberMather = numbersPattern.matcher(line);
        List<Integer> numbers = new ArrayList<>();
        while (numberMather.find()) {
            numbers.add(Integer.parseInt(numberMather.group()));
        }

        int i = 0;
        double result = 0;
        while (operatorMather.find()) {
            if (i == 0) {
                result = calculate(operatorMather.group(), numbers.get(i), numbers.get(++i));
            } else {
                result = calculate(operatorMather.group(), result, numbers.get(++i));
            }
        }

        return result;
    }


    private static double calculate(String operator, double num1, double num2) {
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